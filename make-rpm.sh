#!/bin/bash

cd `dirname $0`
BASE=`pwd`
ant -DappName=dataopen -DversionFormat=yyyyMMdd -Dbasedir=$BASE -f $BASE/bundle/rpm/framework/build.xml
