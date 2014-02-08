#!/bin/bash

BASE_BIN_DIR=`dirname $0`
# public env
. $BASE_BIN_DIR/base_env.sh

# private env
if [ -f $BASE_BIN_DIR/env.sh ]; then
	. $BASE_BIN_DIR/env.sh
fi

##copy server home
if [ ! -d "$JBOSS_SERVER_HOME" ]; then
    rm -f $JBOSS_SERVER_HOME
    mkdir -p $JBOSS_SERVER_HOME
    cp -rf $JBOSS_HOME/server/default/. $JBOSS_SERVER_HOME/.
else
    rm -rf $JBOSS_SERVER_HOME/tmp
    rm -rf $JBOSS_SERVER_HOME/work
    rm -rf $JBOSS_SERVER_HOME/log
fi

# cp file to jboss server home.
cp  "$DEPLOY_HOME/conf/jboss/conf/props/console-users.properties"  "$JBOSS_SERVER_HOME/conf/props/console-users.properties"
cp  "$DEPLOY_HOME/conf/jboss/conf/props/console-roles.properties"  "$JBOSS_SERVER_HOME/conf/props/console-roles.properties" 
cp  "$DEPLOY_HOME/conf/jboss/conf/jboss-service.xml"  "$JBOSS_SERVER_HOME/conf/jboss-service.xml" 
cp  "$DEPLOY_HOME/conf/jboss/conf/login-config.xml"  "$JBOSS_SERVER_HOME/conf/login-config.xml" 
cp  "$DEPLOY_HOME/conf/jboss/conf/web.xml"  "$JBOSS_SERVER_HOME/deploy/jbossweb-tomcat55.sar/conf/web.xml" 
cp  "$DEPLOY_HOME/conf/jboss/deploy/tomcat-jboss-service.xml" "$JBOSS_SERVER_HOME/deploy/jbossweb-tomcat55.sar/META-INF/jboss-service.xml" 
cp  "$DEPLOY_HOME/conf/jboss/deploy/tomcat-server.xml" "$JBOSS_SERVER_HOME/deploy/jbossweb-tomcat55.sar/server.xml"
## 暂不需要拷贝
#cp  "$DEPLOY_HOME/conf/jboss/deploy/jmx_console_jboss_web.xml" "$JBOSS_SERVER_HOME/deploy/jmx-console.war/WEB-INF/jboss-web.xml" 
#cp  "$DEPLOY_HOME/conf/jboss/deploy/jmx_console_web.xml" "$JBOSS_SERVER_HOME/deploy/jmx-console.war/WEB-INF/web.xml" 
#cp  "$DEPLOY_HOME/conf/jboss/deploy/oracle-db-ds.xml" "$JBOSS_SERVER_HOME/deploy/oracle-db-ds.xml" 
#cp  "$DEPLOY_HOME/conf/jboss/deploy/web_console_jboss_web.xml" "$JBOSS_SERVER_HOME/deploy/management/console-mgr.sar/web-console.war/WEB-INF/jboss-web.xml" 
#cp  "$DEPLOY_HOME/conf/jboss/deploy/web_console_web.xml" "$JBOSS_SERVER_HOME/deploy/management/console-mgr.sar/web-console.war/WEB-INF/web.xml" 
#cp  "$DEPLOY_HOME/conf/jboss/deploy/webx-jms-destinations-service.xml" "$JBOSS_SERVER_HOME/deploy/webx-jms-destinations-service.xml" 
#cp  "$DEPLOY_HOME/conf/jboss/deploy/webx-jms-ds.xml" "$JBOSS_SERVER_HOME/deploy/webx-jms-ds.xml"
rm -f "$JBOSS_SERVER_HOME/deploy/ctu-jms-ds.xml"
rm -f "$JBOSS_SERVER_HOME/deploy/ldap-ds.xml"
if [ -d "$JBOSS_SERVER_HOME/deploy/jms" ];then
    cp  "$DEPLOY_HOME/conf/jboss/deploy/uil2-service.xml" "$JBOSS_SERVER_HOME/deploy/jms/uil2-service.xml"
fi
rm -rf  "$JBOSS_SERVER_HOME/deploy/web.ear" 

if  $production_mode ; then	
	rm -rf  "$JBOSS_SERVER_HOME/deploy/web.war" 
	ln -s  "$OUTPUT_HOME/web.war"  "$JBOSS_SERVER_HOME/deploy/web.war" 
else
	## 开发环境/测试环境
	cp  $DEPLOY_HOME/../*.war  "$JBOSS_SERVER_HOME/deploy/web.war" 
fi

JAVA_OPTS="$JAVA_OPTS $JBOSS_SERVER_DIR"
$JBOSS_HOME/bin/run.sh