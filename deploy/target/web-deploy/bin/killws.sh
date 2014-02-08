#!/bin/bash

. `dirname $0`/functions.sh

##初始化
prepare_env
## 关闭apache
stop_httpd
## 关闭应用容器
JBOSS_FLAG=$OUTPUT_HOME/.jbossflag
if [ -f $JBOSS_FLAG ]; then
	stop_jboss
else 
	stop_jetty
fi