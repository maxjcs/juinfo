#!/bin/bash

# �ýű�����crontab��, ÿ��������һ��

. ${opensql_task_work}/bin/base_fun.sh
OUTPUT_FILE=$OUTPUT_PATH/TaskExecuteTask.log
if ! ps -ef |grep 'java' |grep "TaskExecuteTask"
then
	echo "`date`:run --------------------------------" >> $OUTPUT_FILE
	COMMAND=" com.alibaba.china.opensql.task.tool.TaskExecuteTask  "
	$JAVA_HOME/bin/java -Dreceiver=TaskExecuteTask -Dtaskname=TaskExecuteTask -Xms512m -Xmx512m -cp $CLASSPATH -Dapplication.codeset=GBK -Ddatabase.codeset=ISO-8859-1 $COMMAND
fi

