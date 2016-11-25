/*	
 * 类描述待补充
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2016年9月10日      wxg         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2016 Test1 System. - All Rights Reserved.		
 *	
 */
package com.wu1g.pano.utils.cmdprocessor;

import java.net.URL;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 类描述待补充。
 * <dl>
 * [功能概要]
 * <dt>1
 * 
 * @author wxg
 */
public class KrPanoUtil {
	private static final transient Logger	log			= LoggerFactory.getLogger( KrPanoUtil.class );

	private static ThreadPoolExecutor		executor	= new ThreadPoolExecutor( 5, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>() );

	private static String shellCommand = null;
	static{
		try {
			String os = System.getProperty( "os.name" );
			String config=" -askforxmloverwrite=false -xml=false -html=false ";
			if (os.toLowerCase().startsWith( "win" )) {
				shellCommand = "/home/krpano/win/krpanotools64.exe  makepano -shiro=templates/vtour-multires.shiro"+config;
			}else{
				shellCommand="/home/krpano/linux/krpanotools makepano -shiro=templates/vtour-multires.shiro"+config;
			}
		} catch (Exception e) {
			log.error("参数路径初始化失败!",e );
		}
	}
	public static void runShell(final String fileUrl) {
		final String finalShellCommand = shellCommand;
		executor.execute( new Thread() {
			public void run() {
				try {
					long stime = System.currentTimeMillis();
					log.info( fileUrl + "============begin=============" + finalShellCommand );
					final Process p = Runtime.getRuntime().exec( finalShellCommand + " " + fileUrl );
					// WATCHDOG
					Watchdog wd = null;
					final Long timeout = 20 * 60 * 1000L;// 20分钟
					log.info( String.format( "timeout is '%s' -> start watchdog", timeout ) );
					wd = new Watchdog( timeout );
					wd.addListener( new WatchdogListener() {
						@Override
						public void timeout() {
							log.error( "TIMEOUT -> kill process" );
							p.destroy();
						}
					} );
					if (log.isDebugEnabled()) {
						// CREATE STREAM PUMPS
						final StreamPump out = new StreamPump( "IN", p.getInputStream(), System.out, wd );
						final StreamPump err = new StreamPump( "ERR", p.getErrorStream(), System.out, wd );

						// WAIT FOR TERMINATION
						p.waitFor();
						log.debug( "Process finished" );
						out.join();
						log.debug( "Outthread finished" );
						err.join();
						log.debug( "Errthread finished" );
					}
					log.info( fileUrl + "============end=============用时:" + (System.currentTimeMillis() - stime) );
				} catch (Exception e) {
					log.error("全景图生成异常!"+fileUrl, e );
				}
			}
		} );
	}
	public static void main(String[] args) throws Exception {
//		runShell( "D:/home/krpano/3.JPG" );
		URL url=Thread.currentThread().getContextClassLoader().getResource( "" );
		System.out.println( url.getPath() );
		
		
	}
}
