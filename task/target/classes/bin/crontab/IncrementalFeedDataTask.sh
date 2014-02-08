#!/bin/bash

. ${opensql_task_work}/bin/base_fun.sh
OUTPUT_FILE=$OUTPUT_PATH/IncrementalFeedDataTask.log
if ! ps -ef |grep 'java' |grep "FullFeedDataTask"
then
	echo "`date`:run --------------------------------" >> $OUTPUT_FILE
	COMMAND=" com.alibaba.china.opensql.task.tool.IncrementalFeedDataTask  "
	$JAVA_HOME/bin/java -Dreceiver=IncrementalFeedDataTask -Dtaskname=FullFeedDataTask -Xms512m -Xmx512m -cp $CLASSPATH -Dapplication.codeset=GBK -Ddatabase.codeset=ISO-8859-1 $COMMAND
fi