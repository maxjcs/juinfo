#!/bin/bash

. `dirname $0`/functions.sh

##��ʼ��
prepare_env
## �ر�apache
stop_httpd
## �ر�Ӧ������
JBOSS_FLAG=$OUTPUT_HOME/.jbossflag
if [ -f $JBOSS_FLAG ]; then
	stop_jboss
else 
	stop_jetty
fi