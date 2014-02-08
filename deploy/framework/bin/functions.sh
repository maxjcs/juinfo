#!/bin/bash
################################################################
#@Authro jianghang.loujh@alibaba-inc.com
#@Date 2009-07-22
#@Description httpd/jetty/jboss functions
#@version 2010-11-11
################################################################

if [ -f /etc/sysconfig/init ]; then
    . /etc/sysconfig/init
else
  SETCOLOR_SUCCESS=
  SETCOLOR_FAILURE=
  SETCOLOR_WARNING=
  SETCOLOR_NORMAL=
fi

success () {
    if [ "$BOOTUP" = "color" ]; then
        $SETCOLOR_SUCCESS
        if [ -z "$*" ]; then
            echo "ok"
        else
            echo -e "$*"
        fi
        $SETCOLOR_NORMAL
    else
        if [ -z "$*" ]; then
            echo "ok"
        else
            echo -e "$*"
        fi
    fi
    return
}

failed () {
    if [ "$BOOTUP" = "color" ]; then
        $SETCOLOR_FAILURE
        if [ -z "$*" ]; then
            echo "failed"
        else
            echo -e "$*"
        fi
        $SETCOLOR_NORMAL
    else
        if [ -z "$*" ]; then
            echo "failed"
        else
            echo -e "$*"
        fi
    fi
    return 1
}

warning () {
    if [ "$BOOTUP" = "color" ]; then
        $SETCOLOR_WARNING
        if [ -z "$*" ]; then
            echo "warning"
        else
            echo -e "$*"
        fi
        $SETCOLOR_NORMAL
    else
        if [ -z "$*" ]; then
            echo "warning"
        else
            echo -e "$*"
        fi
    fi
    return
}

exit_root () {
    if [ `id -u` = 0 ]; then
    	failed "ERROR! root (the superuser) can't run this script."
    	exit 1
    fi
}

remove_ipcs () {
    pid=$*
    who=`whoami`
    if [ -z $pid ]; then
        warning "Warning: Removed share memory and semaphore--gived pid is NULL"
        return
    fi
    
    shmids=`ipcs -mp |grep -P "\d+[ ]+$who[ ]+$pid" |awk '{print $1}'`
    for id in $shmids ; do
        ipcrm -m $id
    done

    for shsemid in `ipcs -s |egrep "${who}"|awk '{print $2}'`; do 
        if [[ `ipcs -s -i  ${shsemid}|wc -l`  -eq 10 ]] && [[ `ipcs -s -i ${shsemid}|awk 'NR==9{print $NF}'` = "${pid}" ]] ;then
            ipcrm -s ${shsemid}
        fi;
    done
}

get_pid() {	
	STR=$1 ##指定查询字符串
    if $cygwin; then
        JAVA_CMD="$JAVA_HOME\bin\java"
        JAVA_CMD=`cygpath --path --unix $JAVA_CMD`
        JAVA_PID=`ps |grep $JAVA_CMD |awk '{print $1}'`
    else
        STR=`ps -C java -f --width 1000 | grep "$STR"`
        if [ ! -z "$STR" ]; then
            JAVA_PID=`ps -C java -f --width 1000|grep "$STR"|grep -v grep|awk '{print $2}'`
        fi
    fi
    echo $JAVA_PID;
}

check_monitor_ok() {
	##检查monitor
	CHECK_STARTUP_URL="http://127.0.0.1:${APP_PORT}/monitor/ok.htm"
	STARTUP_SUCCESS_MSG="ok"
	
	if  $production_mode ; then 
		## 如果是生产模式,必须是check ok
		while [ true ]; do
			COUNT=`curl -m 3 -s $CHECK_STARTUP_URL | grep -ic "$STARTUP_SUCCESS_MSG"`
	        if [ $COUNT -ge 1 ]; then                 
	             break
	        fi
		    sleep 3
	    done
		success "Oook!"
	else ## 测试/开发环境,30次重试机会
		LOOPS=0
		while [ $LOOPS -lt 30 ]; do
			COUNT=`curl -m 3 -s $CHECK_STARTUP_URL | wc -l`
	        if [ $COUNT -ge 1 ]; then                 
	             break
	        fi
	        let LOOPS=LOOPS+1
		    sleep 3
	    done
	    COUNT=`curl -m 3 -s $CHECK_STARTUP_URL | grep -ic "$STARTUP_SUCCESS_MSG" `
	    if [ $COUNT -lt 1 ] ; then
	    	warning "check [$CHECK_STARTUP_URL] is failed"
	    else
			success "Oook!"
	    fi
	fi
}
# ==========================================================================
# 						start/stop  (jetty,jboss,httpd)
# ==========================================================================

prepare_env() {
	cd `dirname $0`
	BASE_BIN_DIR=`pwd`
	export  LANG=zh_CN.GB18030
	# public env
	. $BASE_BIN_DIR/base_env.sh
	
	# private env
	if [ -f $BASE_BIN_DIR/env.sh ]; then
		. $BASE_BIN_DIR/env.sh
	fi
	
	HOST_NAME=`hostname`
	LOG_DIR=$OUTPUT_HOME/logs
	##以前的日志保存
	TIMESTAMP=`date +%Y_%m_%d_%H_%M`
	KILLWS_LOG=$LOG_DIR/apache/killws.log
	if [ ! -d "$LOG_DIR/apache" ] ; then
    	mkdir -p "$LOG_DIR/apache"
	fi
	if [ -f $KILLWS_LOG ]; then
   		mv -f $KILLWS_LOG $KILLWS_LOG.$TIMESTAMP
   		find $LOG_DIR/apache -maxdepth 1 -type f -iname "killws.log*" -mtime +7 | xargs rm -f
	fi
	
	##hummock 日志目录创建
	HUMMOCK_LOGDIR="$LOG_DIR/hummock"
	if [ ! -d $HUMMOCK_LOGDIR ]; then
	   mkdir -p $HUMMOCK_LOGDIR
	fi
	
	export BASE_BIN_DIR HOST_NAME LOG_DIR TIMESTAMP
}

start_jetty () {
	JETTY_LOG_DIR="$LOG_DIR/jetty"
	JETTY_CHECK_LOG="$JETTY_LOG_DIR/jetty_stdout.log"
	#jetty server
	if [ ! -d "$JETTY_LOG_DIR" ] ; then
    	mkdir -p "$JETTY_LOG_DIR"
	fi
	if [ -f $JETTY_CHECK_LOG ]; then
	    mv -f $JETTY_CHECK_LOG $JETTY_CHECK_LOG.$TIMESTAMP
	    find $JETTY_LOG_DIR -maxdepth 1 -type f -iname "jetty_stdout.log*" -mtime +7 | xargs rm -f
	fi
	
	##check if started before
	STR=''
	if $cygwin; then
	    JAVA_CMD="$JAVA_HOME\bin\java"
	    JAVA_CMD=`cygpath --path --unix $JAVA_CMD`
	    STR=`ps | grep "$JAVA_CMD"`		
	else
	    STR=`ps -C java -f --width 1000 | grep "$JETTY_SERVER_HOME"`
	fi
	if [ ! -z "$STR" ]; then
	        echo "Jetty server already started"
	        exit;
	fi
	
	## start jetty 
	echo -e "$HOST_NAME: starting jetty ...\c"
	$BASE_BIN_DIR/jettyctl.sh start 1>$JETTY_CHECK_LOG 2>$JETTY_CHECK_LOG &
	
	## check monitor
	check_monitor_ok
}

stop_jetty() {
	JETTY_JAVA_PID=`get_pid "$JETTY_SERVER_HOME"`
    if [ ! -z "$JETTY_JAVA_PID" ] ; then
	    echo -e "$HOST_NAME: stopping jetty ... "
	    $BASE_BIN_DIR/jettyctl.sh stop >> $KILLWS_LOG 2>&1
	    ##check if stop failed ,do kill -9
	    JETTY_JAVA_PID=`get_pid "$JETTY_SERVER_HOME"`
	    if [ ! -z "$JETTY_JAVA_PID" ] ; then
	        echo -e "kill java process $JETTY_JAVA_PID ..."
	        /bin/kill -9 $JETTY_JAVA_PID >> $KILLWS_LOG 2>&1
	    fi
	    success "Oook!"
	else
	   warning "$HOST_NAME: jetty not running, who care?"
	fi
}

start_httpd() {
	##检查的日志文件
	APACHE_LOG_DIR="$LOG_DIR/apache"
	APACHE_LOG="$APACHE_LOG_DIR/apache_stdout.log"
	if [ ! -d "$APACHE_LOG_DIR" ] ; then
    	mkdir -p "$APACHE_LOG_DIR"
	fi
	if [ -f $APACHE_LOG ]; then
    	mv -f $APACHE_LOG $APACHE_LOG.$TIMESTAMP
    	find $APACHE_LOG_DIR -maxdepth 1 -type f -iname "apache_stdout.log*" -mtime +7 | xargs rm -f
	fi
	
	##启动apache
	if ! $cygwin; then
	    echo -e "$HOST_NAME: starting httpd ..."
	    chmod +x $BASE_BIN_DIR/httpd
	    $BASE_BIN_DIR/httpd restart > $APACHE_LOG 2>&1 &
	    success "Oook!"
	    echo -e "$HOSTNAME: reloadws_alone done!"
	else
	    echo -e "$HOSTNAME: Cygwin mode, skip start apache!"
	fi
}

stop_httpd() {
	APACHE_PID_FILE_OLD=$OUTPUT_HOME/logs/httpd.pid
	APACHE_PID_FILE=$OUTPUT_HOME/logs/apache/httpd.pid
	
	if [ -f $APACHE_PID_FILE ] ; then
		httpd_pid=`cat $APACHE_PID_FILE`
	    echo -e "$HOST_NAME: stopping httpd ...\c"
	    chmod +x $BASE_BIN_DIR/httpd
	    $BASE_BIN_DIR/httpd stop >> $KILLWS_LOG 2>&1
	    sleep 5
	    killall -9 httpd >> $KILLWS_LOG 2>&1
	    killall -9 hummockclient >> $KILLWS_LOG 2>&1
	    ## 删除共享内存
	    remove_ipcs $httpd_pid
	    success "Oook!"
	elif [ -f $APACHE_PID_FILE_OLD ] ; then ##多找一次老的pid文件，到时候可以直接删除
		httpd_pid=`cat $APACHE_PID_FILE_OLD`
	    echo -e "$HOST_NAME: stopping httpd ...\c"
	    chmod +x $BASE_BIN_DIR/httpd
	    $BASE_BIN_DIR/httpd stop >> $KILLWS_LOG 2>&1
	    sleep 5
	    killall -9 httpd >> $KILLWS_LOG 2>&1
	    killall -9 hummockclient >> $KILLWS_LOG 2>&1
	    ## 删除共享内存
	    remove_ipcs $httpd_pid
	    success "Oook!"
	else
	    warning "$HOST_NAME: httpd not running, who care?"
	fi
}

start_jboss() {
	##检查的日志文件
	JBOSS_LOG_DIR="$LOG_DIR/jboss"
	JBOSS_CHECK_LOG="$JBOSS_LOG_DIR/jboss_stdout.log"
	##检查monitor
	CHECK_STARTUP_URL="http://127.0.0.1:${APP_PORT}/monitor/ok.htm"
	STARTUP_SUCCESS_MSG="ok"
	##防止JBOSS存在控制台暴漏漏洞、删除这两个文件
	JMX_CONSOLE_WAR="$JBOSS_SERVER_HOME/deploy/jmx-console.war"
	WEB_CONSOLE_WAR="$JBOSS_SERVER_HOME/deploy/management/console-mgr.sar/web-console.war"
	
	## 日志备份
	if [ ! -d "$JBOSS_LOG_DIR" ] ; then
    	mkdir -p "$JBOSS_LOG_DIR"
	fi
	if [ -f $JBOSS_CHECK_LOG ]; then
    	mv -f $JBOSS_CHECK_LOG $JBOSS_CHECK_LOG.$TIMESTAMP
    	find $JBOSS_LOG_DIR -maxdepth 1 -type f -iname "jboss_stdout.log*" -mtime +7 | xargs rm -f
	fi
	if [ -d "$JMX_CONSOLE_WAR" ]; then
	    rm  -rf  $JMX_CONSOLE_WAR
	fi
	if [ -d "$WEB_CONSOLE_WAR" ]; then
	    rm  -rf  $WEB_CONSOLE_WAR
	fi
	
	##check if started before
	STR=`ps -C java -f --width 1000 | grep "$JBOSS_SERVER_DIR"`
	
	if [ ! -z "$STR" ]; then
	        echo "Jboss server already started"
	        exit;
	fi	
	## start jboss 
	echo -e "$HOST_NAME: starting jboss ...\c"
	$BASE_BIN_DIR/jbossctl.sh 1>$JBOSS_CHECK_LOG 2>$JBOSS_CHECK_LOG &
	
	## check monitor
	check_monitor_ok
}

stop_jboss() {
    JBOSS_JAVA_PID=`get_pid "$JBOSS_SERVER_HOME"`
    if [ ! -z "$JBOSS_JAVA_PID" ] ; then
        $JBOSS_HOME/bin/shutdown.sh --server=localhost:$NAMING_PORT -S >> $KILLWS_LOG 2>&1
        JBOSS_JAVA_PID=`get_pid "$JBOSS_SERVER_HOME"`
        if [ ! -z "$JBOSS_JAVA_PID" ] ; then
            echo -e "kill java process $JBOSS_JAVA_PID ..."      
            /bin/kill -9 $JBOSS_JAVA_PID >> $KILLWS_LOG 2>&1
        fi
    else
         echo "no java process!"
    fi
}
