#!/bin/sh
BASE=${opensql_task_work}/WORLDS-INF 
OUTPUT_PATH=${opensql_task_work}
JAVA_HOME=/usr/alibaba/java

BASE_LIB="$BASE/lib"
LOCALCLASSPATH=`echo $BASE_LIB/*.jar | tr ' ' ':'`
export CLASSPATH=.:$LOCALCLASSPATH:$BASE/classes

function exit_root () {
    echo
    echo "ERROR! root (the superuser) can't run this script."
    echo
    exit 1
}

if [ `id -u` = 0 ]
then
    exit_root
fi

