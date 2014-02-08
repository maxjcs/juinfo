@echo off
@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT"  setlocal

set ENV_PATH=.\
if "%OS%" == "Windows_NT" set ENV_PATH=%~dp0%
call %ENV_PATH%\base_env.bat

if exist %ENV_PATH%\env.bat call %ENV_PATH%\env.bat

REM copy file to jetty server.

RMDIR "%JETTY_SERVER_HOME%" /S /Q
MKDIR "%JETTY_SERVER_HOME%"
MKDIR "%JETTY_SERVER_HOME%\logs"
MKDIR "%JETTY_SERVER_HOME%\tmp"
MKDIR "%JETTY_SERVER_HOME%\webapps"

REM create output/logs/jetty for jetty_stdout.log
MKDIR "%OUTPUT_HOME%/logs"
MKDIR "%OUTPUT_HOME%/logs/jetty"

REM jetty args
set JETTY_PROS= -Djetty.logs="%JETTY_SERVER_HOME%\logs" -Djetty.home="%JETTY_HOME%" -Djava.io.tmpdir="%JETTY_SERVER_HOME%\tmp"
set JETTY_ARGS= --ini="%JETTY_SERVER_HOME%\conf\start.ini"

for %%a in ("%DEPLOY_HOME%\..\*.war") do (
	if exist "%JETTY_SERVER_HOME%\webapps\root.war" (
		DEL /F /S /Q "%JETTY_SERVER_HOME%\webapps\root.war"
		echo "remove duplicate root.war"
	)
	COPY "%%a" "%JETTY_SERVER_HOME%\webapps\root.war" /Y
)
XCOPY  "%DEPLOY_HOME%\conf\jetty" "%JETTY_SERVER_HOME%" /Y /S /Q

REM cls screen 
rem CLS

"%JAVA_HOME%\bin\java" %JAVA_OPTS% %JETTY_PROS% -jar %JETTY_HOME%\start.jar %JETTY_ARGS%