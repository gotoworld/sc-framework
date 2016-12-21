@echo off
rem /bin/sh
rem 
rem  startup - this script start and stop appserver daemin
rem 
rem  chkconfig: - 85 15
rem  description: appserver is an socket server for mjcq game server.
rem  processname: java
rem  config: null
rem  pidfile: null
rem  lockfile: null

rem app configuration
rem prompt LANG="en_US.UTF-8"

set ServerPort=8080

rem 注意：按照实际部署修改应用路径
::set  _JAVA=D:\IDE\Java\jre1.8.0_111\bin\java
set  _JAVA=D:\IDE\Java\32\jdk1.8.0_111\bin\java
::set _JAVA=%java%
set _APP=%~dp0

set MAINCLASS=com.wu1g.AppApplication

set _JARS=.;%_APP%config;%_APP%lib-app;%_APP%lib-common;


set _PARAMS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8080,suspend=n -server -Xverify:none -server -Xmx400m -Xms200m -Xss256k -XX:SurvivorRatio=18 -XX:+UseConcMarkSweepGC -XX:ParallelGCThreads=8 -XX:+DisableExplicitGC -XX:MaxTenuringThreshold=7 -XX:MaxGCPauseMillis=100 -XX:CMSInitiatingOccupancyFraction=80 -XX:-PrintGC -XX:-PrintGCDetails -Xloggc:%_APP%gc.log

rem  script configuration
set _TIMEOUT=3
set _LOG=log_start.log


if "%1%"=="" (
  echo "start|stop|restart"
  goto end
) else (
  goto  %1%
  goto end
)

:restart (
   goto stop
   goto wait_for
   goto start
)

:start (
    if not exist %_APP% (
		echo "%_APP%不存在"
		pause
	)

    echo "Server is starting..."
	
	::echo %_JAVA% %_PARAMS% -Djava.ext.dirs=%_JARS% %MAINCLASS%
	
    cd %_APP% 
    
	%_JAVA% %_PARAMS% -Djava.ext.dirs=%_JARS% %MAINCLASS% > %_LOG% 2>&1 &
	::	2>&1 &

    echo "Server is started..."
	goto end
)

:wait_for (
    echo -n "Waitting server stop..."
    i=0
    while test %%i -ne %_TIMEOUT%; do
        echo -n '.'
        i=`expr %%i + 1`
        sleep 1
    done
)

:stop (
    echo "Server is stoping..."
	:: taskkill /f /t /im %_APP%
    echo "Server is stoped..."
	::goto end
	if "%1%"=="stop" (
		exit
	)
)

:end
