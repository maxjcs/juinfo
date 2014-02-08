SET JAVA_HOME=D:/Java/jdk1.7
set DEPLOY_HOME=D:/workspace/juinfo/deploy/target/web-deploy
set OUTPUT_HOME=/opt/webapp/juinfo/output
set JETTY_HOME=I:/jetty7.2.2
set JETTY_SERVER_HOME=D:/workspace/juinfo/deploy/target/web-deploy/jetty_server
set PRODUCTION=false
set LOG_DIR=%OUTPUT_HOME%\logs

set JAVA_MEM_OPTS= -Xms128m -Xmx512m -XX:PermSize=128m
set JAVA_OPTS_EXT= -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dapplication.codeset=GBK -Dmonitor.enable=true -Dmonitor.exclog.enable=true -Dmonitor.biz.interval=120 -Dmonitor.debug=false -Djava.util.logging.config.file="%DEPLOY_HOME%\conf\general\logging.properties"
set DATABASE_OPTS= -Ddatabase.codeset=ISO-8859-1 -Ddatabase.logging=false
set URI_ENCODE= -Dorg.eclipse.jetty.util.URI.charset=GBK
set JAVA_DEBUG_OPT= -server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=7009,server=y,suspend=n
set JAVA_OPTS= %JAVA_MEM_OPTS% %DATABASE_OPTS% %JAVA_OPTS_EXT% %JAVA_DEBUG_OPT% %URI_ENCODE%