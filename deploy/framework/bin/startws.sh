#!/bin/bash

. `dirname $0`/functions.sh

## root启动判断
exit_root

##初始化
prepare_env
## 启动应用容器
JBOSS_FLAG=$OUTPUT_HOME/.jbossflag
if [ -f $JBOSS_FLAG ]; then
	start_jboss
else 
	start_jetty
fi
## 启动apache
start_httpd
