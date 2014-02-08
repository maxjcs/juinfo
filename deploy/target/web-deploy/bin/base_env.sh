#=================================================

# 1,JAVA_HOME			eg:/usr/alibaba/java
# 2,HTTPD_HOME			eg:/usr/alibaba/httpd
# 3,JETTY_HOME     		eg:/usr/alibaba/jetty
# 4,JETTY_SERVER_HOME	        eg:/home/admin/web-deploy/jetty_server
# 5,OUTPUT_HOME			eg:/home/admin/output
# 6,DEPLOY_HOME			eg:/home/admin/web-deploy
#==================================================
JAVA_HOME=D:/Java/jdk1.7
HTTPD_HOME=/usr/alibaba/httpd
JETTY_HOME=I:/jetty7.2.2
JETTY_SERVER_HOME=D:/workspace/juinfo/deploy/target/web-deploy/jetty_server
OUTPUT_HOME=/opt/webapp/juinfo/output
DEPLOY_HOME=D:/workspace/juinfo/deploy/target/web-deploy
PRODUCTION=false

APACHE_PORT=80
APP_PORT=7001
DEBUG_PORT=7009

APP_NAME=juinfo
UNIX_USER=admin

JAVA_OPTS_EXT=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dapplication.codeset=GBK -Djava.util.logging.config.file=$DEPLOY_HOME/conf/general/logging.properties"
DATABASE_OPTS=" -Ddatabase.codeset=ISO-8859-1 -Ddatabase.logging=false "
URI_ENCODING=" -Dorg.eclipse.jetty.util.URI.charset=GBK "
TIGER_JMX_OPT=" -Dcom.sun.management.config.file=$DEPLOY_HOME/conf/jmx/jmx_monitor_management.properties "
JAVA_DEBUG_OPT=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=7009,server=y,suspend=n "
	    JAVA_MEM_OPTS=" -DappName=$APP_NAME -Xms64m -Xmx1024m -XX:MaxPermSize=128m "
	JAVA_OPTS=" $JAVA_MEM_OPTS $DATABASE_OPTS $JAVA_OPTS_EXT $TIGER_JMX_OPT $URI_ENCODING $JAVA_DEBUG_OPT "
    production_mode=false;

cygwin=false;
case "`uname`" in
    CYGWIN*)
        cygwin=true
        ;;
esac

if ! $cygwin ; then
	chmod 600 $DEPLOY_HOME/conf/jmx/*.properties
fi

export APACHE_PORT APP_PORT DEPLOY_HOME JAVA_OPTS JAVA_HOME HTTPD_HOME JETTY_HOME JETTY_SERVER_HOME OUTPUT_HOME APP_NAME UNIX_USER production_mode cygwin
