package com.wu1g.proxy.socks;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wu1g.bean.robot.BaseSiteBean;
import com.wu1g.common.IdUtil;
import com.wu1g.thread.robot.RobotThread;

/**
 * 处理客户端的请求通过代理发送到远程服务器,接收远程服务器返回信息返回给请求客户端
 */
public class SSLRequestHandler implements Runnable {
	private static transient Logger		log				= LogManager.getLogger( SSLRequestHandler.class );
	/** 客户端socket对象 */
	protected Socket					clientSocket;
	/** 客户端的输入流 */
	protected DataInputStream			clientInputStream;
	/** 客户端输出流 */
	protected OutputStream				clientOutputStream;
	/** 存放客户端请求的报文头 */
	protected HashMap<String, String>	clientHeader;

	/** 远端 socket对象 */
	protected Socket					remoteSocket;
	/** 远端输入流 */
	protected InputStream				remoteInputStream;
	/** 远端返回的具体信息流 */
	protected OutputStream				remoteOutputStream;
	/** 存放远端返回的报文头 */
	protected HashMap<String, String>	remoteHeader;

	/** 客户端请求类型 ("GET" or "POST") */
	protected String					requestType;
	/** 客户端请求完整url字符串 */
	protected String					linkURL;
	/** 解析linkURL的URL对象 */
	protected URL						netURL;
	/** 解析URL的URI (e.g. /index.html) */
	protected String					uri;
	/** 客户端请求的版本 (e.g. HTTP/1.1) */
	protected String					httpVersion;
	/** 行结束字符 */
	protected static final String		endOfLine		= "\r\n";
	/** 解析url获取文件名称 */
	protected String					fileName;
	/** 本地保存文件夹根目录 */
	protected static final String		localFileDir	= "d:/xxhtml";

	/** 存储已访问host标记 */
	private static Map<String, String>	hostMap			= new HashMap<String, String>();

	/**
	 * 创建一个客户端请求处理实例
	 * 
	 * @param clientSocket
	 */
	public SSLRequestHandler(Socket clientSocket) {
		clientHeader = new HashMap<String, String>();
		remoteHeader = new HashMap<String, String>();
		this.clientSocket = clientSocket;
	}

	/**
	 * 创建实例时，打开client/remote数据流，执行3个任务:<br>
	 * 1) 获取客户端请求<br>
	 * 2) 转发请求到远程服务器<br>
	 * 3) 获取远程数据返给客户端<br>
	 * 最后关闭client/remote各种数据流.<br>
	 * 
	 * @see Runnable#run()
	 */
	public void run() {
		try {
			clientInputStream = new DataInputStream( clientSocket.getInputStream() );
			clientOutputStream = clientSocket.getOutputStream();

			// 1.从客户端获取请求
			clientToProxy();
			 log.info( "===begin=====url=============" + linkURL );
			// 2. 转发请求到远程服务器
			proxyToRemote();
			// 3. 获取远程数据返给客户端
			remoteToClient();
			 log.info( "===end=======url=============" + linkURL );
			// log.info( "\n\n" );

			if (linkURL != null
					// && hostMap.get( getDomain( linkURL ) )==null
					&& linkURL.indexOf( "443" ) == -1 && remoteHeader.get( "content-type" ) != null && remoteHeader.get( "content-type" ).contains( "text/html" )) {
				BaseSiteBean siteBean = new BaseSiteBean();
				siteBean.setId( IdUtil.createUUID( 32 ) );
				siteBean.setUrl( linkURL );
				siteBean.setType( "1" );
				siteBean.setWeb_depth( "5" );
				Thread t = new Thread( new RobotThread( "0", 0, siteBean, null, clientHeader, null, null ) );
				t.start();
				//
				hostMap.put( getDomain( linkURL ), "1" );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			try {
//				if (clientSocket != null) {
//					clientSocket.shutdownInput();
//				}
//			} catch (IOException e) {
//			}
//			try {
//				if (clientSocket != null) {
//					clientSocket.shutdownOutput();
//				}
//			} catch (IOException e) {
//			}
			try {
				if (remoteOutputStream != null) {
					remoteOutputStream.close();
				}
			} catch (IOException e) {
			}
			try {
				if (remoteInputStream != null) {
					remoteInputStream.close();
				}
			} catch (IOException e) {
			}
			try {
				if (remoteSocket != null) {
					remoteSocket.close();
				}
			} catch (IOException e) {
			}

			try {
				if (clientOutputStream != null) {
					clientOutputStream.close();
				}
			} catch (IOException e) {
			}
			try {
				if (clientInputStream != null) {
					clientInputStream.close();
				}
			} catch (IOException e) {
			}
			try {
				if (clientSocket != null) {
					clientSocket.close();
				}
			} catch (IOException e) {
			}
			clientHeader = null;
			remoteHeader = null;
		}
	}

	/**
	 * 接收并预处理客户端的请求头信息,用于重定向到远程服务器
	 */
	private void clientToProxy() {
		String line, key, value;
		StringTokenizer tokens;
		try {
			// BufferedReader clientInputReader = new BufferedReader(new InputStreamReader(clientInputStream));
			// HTTP Command
			if ((line = clientInputStream.readLine()) != null) {
				// log.info( "==line=1=="+line );
				tokens = new StringTokenizer( line );
				requestType = tokens.nextToken();
				linkURL = tokens.nextToken();
				httpVersion = tokens.nextToken();
				// log.info( "====requestType=====" + requestType + "=============" + linkURL );
			}
			// Header Info
			while ((line = clientInputStream.readLine()) != null) {
				// check for empty line
				if (line.trim().length() == 0) {
					break;
				}
				// log.info( "==line=2=="+line );
				// tokenize every header as key and value pair
				tokens = new StringTokenizer( line );
				key = tokens.nextToken( ":" );
				value = line.replaceAll( key, "" ).replace( ": ", "" );
				clientHeader.put( key.toLowerCase(), value );
			}
			// log.info( "===clientHeader.get( host )===" + clientHeader.get( "host" ) );
			stripUnwantedHeaders();
			getUri();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		} catch (SocketException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 解析处理远端信息报文头
	 */
	private void analyzeRemoteHeader(BufferedReader bufferedReader) {
		String line, key, value;
		StringTokenizer tokens;
		try {
			// HTTP Command
			if ((line = bufferedReader.readLine()) != null) {
				tokens = new StringTokenizer( line );
			}
			// Header Info
			while ((line = bufferedReader.readLine()) != null) {
				// check for empty line
				if (line.trim().length() == 0) {
					break;
				}
				// tokenize every header as key and value pair
				tokens = new StringTokenizer( line );
				key = tokens.nextToken( ":" );
				value = line.replaceAll( key, "" ).replace( ": ", "" );
				// log.info( "remoteHeader==" + key.toLowerCase() + ":" + value );
				remoteHeader.put( key.toLowerCase(), value );
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		} catch (SocketException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sending pre-processed client request to remote server
	 * 
	 */
	private void proxyToRemote() throws SocketException {

		// for (java.util.Map.Entry<String, String> entry : header.entrySet()) {
		// //log.info( "============================" + entry.getKey() + ":" + entry.getValue() );
		// }

		try {
			if (clientHeader.get( "host" ) == null) {
				return;
			}
			// if (!requestType.startsWith( "GET" ) && !requestType.startsWith( "POST" )){
			// return;
			// }
			String request = null;
//			if (requestType.startsWith( "CONNECT" )) {
				try {
					// 获得请求主机和端口
					netURL = new URL( "https://" + linkURL );
					SSLContext sslContext = SSLContext.getInstance( "SSL" );
					sslContext.init( null, new TrustManager[] { truseAllManager }, new SecureRandom() );
					// 从上述SSLContext对象中得到SSLSocketFactory对象
					SSLSocketFactory ssf = sslContext.getSocketFactory();
					// 从工厂获得Socket连接
					remoteSocket = (SSLSocket)ssf.createSocket( netURL.getHost(), netURL.getPort() == -1 ? 443 : netURL.getPort() );
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				// request = "CONNECT " + linkURL + " HTTP/1.0";
				request = "CONNECT " + ("".equals( netURL.getPath() ) ? "/" : netURL.getPath()) + " HTTP/1.0";
//			} else {
//				// 获得请求主机和端口
//				netURL = new URL( linkURL );
//				remoteSocket = new Socket( clientHeader.get( "host" ), netURL.getPort() == -1 ? 80 : netURL.getPort() );
//				if ("OPTIONS".equals( requestType )) {
//					requestType = "GET";
//				}
//				request = requestType + " " + netURL.getPath() + " HTTP/1.0";// 要解决服务器不返回Transfer-Encoding:chunked，在客户端请求的时候可以使用http 1.0的协议。
//			}
			// // 添加请求日志信息
			// //log.info( netURL.getProtocol() );
			// //log.info( netURL.getHost() );
			// //log.info( netURL.getPort() );
			// //log.info( "====0===" + netURL.getPath() );
			remoteOutputStream = remoteSocket.getOutputStream();
			// make sure streams are still open
			checkRemoteStreams();
			checkClientStreams();

			// make request from client to remote server
			remoteOutputStream.write( request.getBytes() );
			remoteOutputStream.write( endOfLine.getBytes() );
			// log.info( "request=================" + request );

			// send hostname
			String command = "host: " + clientHeader.get( "host" );
			remoteOutputStream.write( command.getBytes() );
			remoteOutputStream.write( endOfLine.getBytes() );
			// log.info( command );

			// send rest of the headers
			for (String key : clientHeader.keySet()) {
				if (!"host".equals( key )) {
					command = key + ": " + clientHeader.get( key );
					remoteOutputStream.write( command.getBytes() );
					remoteOutputStream.write( endOfLine.getBytes() );
					// //log.info( command );
					// log.info( "==header=" + linkURL + "==command==" + command );
				}
			}

			remoteOutputStream.write( endOfLine.getBytes() );
			remoteOutputStream.flush();

			// send client request data if its a POST request
			if (requestType.startsWith( "POST" )) {

				int contentLength = Integer.parseInt( clientHeader.get( "content-length" ) );
				for (int i = 0; i < contentLength; i++) {
					remoteOutputStream.write( clientInputStream.read() );
				}
			}
			// complete remote server request
			remoteOutputStream.write( endOfLine.getBytes() );
			remoteOutputStream.flush();

		} catch (Exception e) {
			log.error( "..代理访问=" + linkURL + "异常!", e );
			return;
		}
	}

	/**
	 * Sending buffered remote server response back to client with minor header processing
	 * 
	 */
	@SuppressWarnings("deprecation")
	private void remoteToClient() {
		String line;
		try {
			// If socket is closed, return
			if (remoteSocket == null) {
				return;
			}
			clientOutputStream = clientSocket.getOutputStream();
			// 远端返回头信息流 //此处不能是 BufferedReader 不然无法读取后续信息
			DataInputStream remoteOutHeader = new DataInputStream( (remoteSocket.getInputStream()) );
			// 远端返回头信息流 数据克隆
			ByteArrayOutputStream remoteOutHeaderClone = new ByteArrayOutputStream();
			// 远端返回的具体数据流 数据克隆
			ByteArrayOutputStream remoteInputStreamClone = new ByteArrayOutputStream();
			// 获取远端信息返给请求端
			while ((line = remoteOutHeader.readLine()) != null) {
				if (line.trim().length() == 0) {
					break;
				}
				// check for proxy-connection: keep-alive
				if (line.toLowerCase().startsWith( "proxy" )) {
					continue;
				}
				if (line.contains( "keep-alive" )) {
					continue;
				}

				// write remote response to client
				// log.info( "==remote header ====" + line );
				clientOutputStream.write( line.getBytes() );
				clientOutputStream.write( endOfLine.getBytes() );
				// copy
				remoteOutHeaderClone.write( line.getBytes() );
				remoteOutHeaderClone.write( endOfLine.getBytes() );
			}
			// complete remote header response
			clientOutputStream.write( endOfLine.getBytes() );
			clientOutputStream.flush();
			// copy
			remoteOutHeaderClone.write( endOfLine.getBytes() );
			// remoteOutHeaderClone.flush();
			// --分析获取远程服务器返回的头信息-
			analyzeRemoteHeader( new BufferedReader( new InputStreamReader( new ByteArrayInputStream( remoteOutHeaderClone.toByteArray() ) ) ) );

			// 判断是否需要跳转
			String location = remoteHeader.get( "location" );
			if (location != null) {
				log.error( "需要跳转地址===" + location );
			}

			// get remote response body
			remoteInputStream = remoteSocket.getInputStream();
			byte[] buffer = new byte[2048];

			// -------------------------begin----------------------------------------------------------
			String filePath = netURL.getPath();
			// //log.info( "==1===" + filePath );
			fileName = filePath.substring( filePath.lastIndexOf( "/" ) + 1, filePath.length() );
			// //log.info( "==2===" + fileName );

			// 已读取流长度
			long content_read_length = 0;
			// 已知流总长度
			long content_total_length = -1;
			if (remoteHeader.get( "content-length" ) != null) {
				content_total_length = Long.parseLong( remoteHeader.get( "content-length" ) );
			}
			// buffer remote response then write it back to client
			for (int i; (i = remoteInputStream.read( buffer )) != -1;) {
				// 转发远端信息到客户请求端
				clientOutputStream.write( buffer, 0, i );
				clientOutputStream.flush();
				// 复制远端信息
				remoteInputStreamClone.write( buffer, 0, i );
				// log.info( "==" + fileName + "==已知流总长度=" + content_total_length + "=已完成流长度==" + (content_read_length += i) + "==本次读取长度" + i );
				// -------------------------end
			}
			// --分析获取远程服务器返回的具体信息-
			// analyzeRemoteInputStream( remoteInputStreamClone );
			// -------------------------end----------------------------------------------------------
		} catch (Exception e) {
			log.error( "..代理获取信息写回客户端=" + linkURL + "异常!", e );
			return;
		}
	}

	/**
	 * 从客户端去掉不需要的请求
	 * 
	 */
	private void stripUnwantedHeaders() {

		if (clientHeader.containsKey( "user-agent" )) {
			clientHeader.remove( "user-agent" );
		}
		// if (clientHeader.containsKey( "referer" )) {
		// clientHeader.remove( "referer" );
		// }
		if (clientHeader.containsKey( "proxy-connection" )) {
			clientHeader.remove( "proxy-connection" );
		}
		if (clientHeader.containsKey( "connection" ) && clientHeader.get( "connection" ).equalsIgnoreCase( "keep-alive" )) {
			clientHeader.remove( "connection" );
		}
	}

	/**
	 * 检查客户端输入和输出流，如果关闭 重新连接
	 * 
	 */
	private void checkClientStreams() {

		try {
			if (clientSocket.isOutputShutdown()) {
				clientOutputStream = clientSocket.getOutputStream();
			}
			if (clientSocket.isInputShutdown()) {
				clientInputStream = new DataInputStream( clientSocket.getInputStream() );
			}
		} catch (Exception e) {
			log.error( "..检查客户端输入和输出流=" + linkURL + "异常!", e );
			return;
		}
	}

	/**
	 * 检查远端输入和输出流，如果关闭，重新连接
	 * 
	 */
	private void checkRemoteStreams() {

		try {
			if (remoteSocket.isOutputShutdown()) {
				remoteOutputStream = remoteSocket.getOutputStream();
			}
			if (remoteSocket.isInputShutdown()) {
				remoteInputStream = new DataInputStream( remoteSocket.getInputStream() );
			}
		} catch (Exception e) {
			log.error( "..检查远端输入和输出流=" + linkURL + "异常!", e );
			return;
		}
	}

	/**
	 * 解析URI
	 * 
	 */
	private void getUri() {

		if (clientHeader.containsKey( "host" )) {
			int temp = linkURL.indexOf( clientHeader.get( "host" ) );
			temp += clientHeader.get( "host" ).length();

			if (temp < 0) {
				// prevent index out of bound, use entire url instead
				uri = linkURL;
			} else {
				// get uri from part of the url
				uri = linkURL.substring( temp );
			}
		}
	}

	/**
	 * 解析处理远端信息报文头
	 * 
	 * @param remoteInputStream
	 */
	private void analyzeRemoteInputStream(ByteArrayOutputStream stream) {
		writeFile( stream );
		printHtml( stream );
	}

	/**
	 * 解析处理远端信息报文头
	 * 
	 * @param remoteInputStream
	 */
	private void printHtml(ByteArrayOutputStream stream) {
		BufferedReader reader = null;
		// 获取头信息 用于判断流是否压缩
		String content_encoding = remoteHeader.get( "content-encoding" );
		if (remoteHeader.get( "content-type" ) != null && remoteHeader.get( "content-type" ).contains( "text/html" )) {
			try {
				// 打印内容
				StringBuffer htmlContent = new StringBuffer();
				if (content_encoding != null && content_encoding.indexOf( "gzip" ) != -1) {
					// log.info( "解压缩读取!" + fileName );
					try {
						reader = new BufferedReader( new InputStreamReader( new GZIPInputStream( new ByteArrayInputStream( stream.toByteArray() ) ), "UTF-8" ) );
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// log.info( "直接读取!" + fileName );
					reader = new BufferedReader( new InputStreamReader( new ByteArrayInputStream( stream.toByteArray() ), "UTF-8" ) );
				}
				String line;
				while ((line = reader.readLine()) != null) {
					htmlContent.append( line + "\n" );
				}
				// log.info( htmlContent );
			} catch (Exception e) {
				log.error( "..解析处理远端信息报文头=" + linkURL + "异常!", e );
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException e) {
				}
			}
		} else {
			// log.info( "不是html内容..不打印.." );
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param inputStream
	 */
	public void writeFile(ByteArrayOutputStream stream) {
		// log.info( "====准备复制文件====" + linkURL );
		if (netURL == null || netURL.getPath() == null | stream == null) {
			log.error( "================为空============" + linkURL );
		}
		InputStream inputStream = null;
		// 获取头信息 用于判断流是否压缩
		String content_encoding = remoteHeader.get( "content-encoding" );
		// 写入文件
		if (content_encoding != null && content_encoding.indexOf( "gzip" ) != -1) {
			// log.info( "===解压复制==" + fileName );
			try {
				inputStream = (new GZIPInputStream( new ByteArrayInputStream( stream.toByteArray() ) ));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// log.info( "===直接复制==" + fileName + "===已有流大小==" + stream.toByteArray().length );
			inputStream = (new ByteArrayInputStream( stream.toByteArray() ));
		}
		String filePath = localFileDir + netURL.getPath();
		// log.info( "==3===" + filePath );
		File fileDirectory = new File( filePath.substring( 0, filePath.lastIndexOf( "/" ) ) );
		FileOutputStream fos = null;
		try {
			// 如果文件夹不存在,则创建
			if (!fileDirectory.exists()) {
				fileDirectory.mkdirs();
			}
			String content_type = remoteHeader.get( "content-type" );
			if ("psb".equals( fileName )) {
				filePath += IdUtil.createUUID( 32 );
			}

			if (fileName.indexOf( "." ) == -1 && content_type != null) {
				filePath += "." + content_type.substring( content_type.indexOf( "/" ) + 1, content_type.length() );
			}

			File file = new File( filePath );
			if (file.exists()) {
				log.error( "文件已存在" + filePath );
				return;
			}
			fos = new FileOutputStream( file );
			byte[] b = new byte[1024];
			for (int i; (i = inputStream.read( b )) != -1;) {
				fos.write( b, 0, i );
				fos.flush();
			}
		} catch (Exception e) {
			log.error( "..远端内容写入文件=" + linkURL + "异常!", e );
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
			}
		}
		// log.info( "Done" );
	}

	/**
	 * 重写验证方法，取消检测ssl
	 */
	private static TrustManager truseAllManager = new X509TrustManager() {
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

//	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
//		public boolean verify(String hostname, SSLSession session) {
//			// 直接Pass，全部信任
//			return true;
//		}
//	}

	/** 根据url解析获取主域 */
	public String getDomain(String url) {
		try {
			String host = new URL( url ).getHost().toLowerCase();// 此处获取值转换为小写
			Pattern pattern = Pattern.compile( "[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn||\\.xin|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)" );
			Matcher matcher = pattern.matcher( host );
			while (matcher.find()) {
				return matcher.group();
			}
		} catch (MalformedURLException e) {
		}
		return "";
	}
}
