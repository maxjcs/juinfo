#!/bin/bash

# �ýű�����crontab��, ÿ��������һ��

. ${opensql_task_work}/bin/base_fun.sh
OUTPUT_FILE=$OUTPUT_PATH/BieeExternalTask.log
if ! ps -ef |grep 'java' |grep "BieeExternalTask"
then
	echo "`date`:run --------------------------------" >> $OUTPUT_FILE
	COMMAND=" com.alibaba.china.opensql.task.tool.BieeExternalTask  "
	$JAVA_HOME/bin/java -Dreceiver=BieeExternalTask -Dtaskname=BieeExternalTask -Xms512m -Xmx512m -cp $CLASSPATH -Dapplication.codeset=GBK -Ddatabase.codeset=ISO-8859-1 $COMMAND
fi

