@echo off
@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT"  setlocal

set ENV_PATH=.\
if "%OS%" == "Windows_NT" set ENV_PATH=%~dp0%
call %ENV_PATH%\base_env.bat

if exist %ENV_PATH%\env.bat call %ENV_PATH%\env.bat

if exist %JBOSS_SERVER_HOME%\conf\jboss-service.xml goto nextstep
	XCOPY  "%JBOSS_HOME%\server\default\*.*"  "%JBOSS_SERVER_HOME%\*.*" /e /r
:nextstep

REM copy file to jboss server home.
COPY  "%DEPLOY_HOME%\conf\jboss\conf\props\console-users.properties"  "%JBOSS_SERVER_HOME%\conf\props\console-users.properties" /Y
COPY  "%DEPLOY_HOME%\conf\jboss\conf\props\console-roles.properties"  "%JBOSS_SERVER_HOME%\conf\props\console-roles.properties" /Y
COPY  "%DEPLOY_HOME%\conf\jboss\conf\jboss-service.xml"  "%JBOSS_SERVER_HOME%\conf\jboss-service.xml" /Y
COPY  "%DEPLOY_HOME%\conf\jboss\conf\login-config.xml"  "%JBOSS_SERVER_HOME%\conf\login-config.xml" /Y
COPY  "%DEPLOY_HOME%\conf\jboss\conf\web.xml"  "%JBOSS_SERVER_HOME%\deploy\jbossweb-tomcat55.sar\conf\web.xml" /Y
COPY  "%DEPLOY_HOME%\conf\jboss\deploy\tomcat-jboss-service.xml" "%JBOSS_SERVER_HOME%\deploy\jbossweb-tomcat55.sar\META-INF\jboss-service.xml" /Y
COPY  "%DEPLOY_HOME%\conf\jboss\deploy\tomcat-server.xml" "%JBOSS_SERVER_HOME%\deploy\jbossweb-tomcat55.sar\server.xml" /Y
for %%a in ("%DEPLOY_HOME%\..\*.war") do (
	if exist "%JBOSS_SERVER_HOME%\deploy\web.war" (
		DEL /F /S /Q "%JBOSS_SERVER_HOME%\deploy\web.war"
		echo "remove duplicate web.war"
	)
	COPY "%%a" "%JBOSS_SERVER_HOME%\deploy\web.war" /Y
) 

rem ÔÝ²»ÐèÒª¿½±´
rem COPY  "%DEPLOY_HOME%\conf\jboss\deploy\oracle-db-ds.xml" "%JBOSS_SERVER_HOME%\deploy\oracle-db-ds.xml" /Y
rem COPY  "%DEPLOY_HOME%\conf\jboss\deploy\webx-jms-destinations-service.xml" "%JBOSS_SERVER_HOME%\deploy\webx-jms-destinations-service.xml" /Y
rem COPY  "%DEPLOY_HOME%\conf\jboss\deploy\webx-jms-ds.xml" "%JBOSS_SERVER_HOME%\deploy\webx-jms-ds.xml" /Y
REM cls screen 
rem CLS

set JAVA_OPTS= %JAVA_OPTS% %JBOSS_SERVER_DIR%

call %JBOSS_HOME%\bin\run.bat