package com.wu1g.proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wu1g.proxy.socks.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 代理服务 启动类
 */
public class SocketServer {
	private static transient Logger		log				= LogManager.getLogger( SocketServer.class );
	/** socket服务器 实例 */
	protected ServerSocket		server;
	/** 线程池 */
	protected ExecutorService	executor;
	/** 默认代理端口 */
	protected static int		listener_port	= 12306;

	/**
	 * 初始化
	 * 
	 * @param port
	 *            监听端口
	 */
	public SocketServer(int port) {
		// 线程池初始化
		executor = Executors.newCachedThreadPool();
		try {
			// 服务器初始化
			server = new ServerSocket( port );
		} catch (IOException e) {
			log.error( "代理服务器[" + port + "]初始化失败!", e );
		}
	}

	/** 为每个请求创建一个处理对象 */
	public void accept() {
		while (true) {
			try {
				executor.execute( new RequestHandler( server.accept() ) );
			} catch (IOException e) {
				log.error( "创建请求处理对象异常!", e );
			}
		}
	}

	/**
	 * run
	 */
	public static void main(String[] args) {
		if (args != null && args.length>0) {
			listener_port = Integer.parseInt( args[ 0 ] );
		}
		log.info( "..代理服务启动 监听端口:" + listener_port );
		SocketServer proxy = new SocketServer( listener_port );
		proxy.accept();
	}

}
