#!/bin/bash

. `dirname $0`/functions.sh

## root�����ж�
exit_root

##��ʼ��
prepare_env
## ����Ӧ������
JBOSS_FLAG=$OUTPUT_HOME/.jbossflag
if [ -f $JBOSS_FLAG ]; then
	start_jboss
else 
	start_jetty
fi
## ����apache
start_httpd
