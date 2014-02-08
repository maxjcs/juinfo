#!/bin/bash

# 该脚本加入crontab中, 每个月运行一次

. ${opensql_task_work}/bin/base_fun.sh
OUTPUT_FILE=$OUTPUT_PATH/ilibTask.log
if ! ps -ef |grep 'java' |grep "IlibFollowTaskStarter"
then
	echo "`date`:IlibFollowTaskStarter run --------------------------------" >> $OUTPUT_FILE
	COMMAND=" com.alibaba.china.opensql.task.tool.IlibFollowTaskStarter  "
	$JAVA_HOME/bin/java -Dreceiver=IlibFollowTaskStarter -Dtaskname=IlibFollowTaskStarter -Xms512m -Xmx512m -cp $CLASSPATH -Dapplication.codeset=GBK -Ddatabase.codeset=ISO-8859-1 $COMMAND
fi

