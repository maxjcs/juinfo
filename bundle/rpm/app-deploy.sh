#!/bin/bash

function deploy_template(){
  deploy $TARGET/web-deploy.jar  $WEBAPP_HOME
  chmod 755 $WEBAPP_HOME/bin/*
}

function deploy_war(){
  if [ $PRODUCTION == true ]; then
    mkdir -p $OUTPUT_HOME
    cp $TARGET/dataopen.bundle.war-1.0-SNAPSHOT.war $OUTPUT_HOME/web.war
  else
  	mkdir -p $WEBAPP_HOME 
    cp $TARGET/dataopen.bundle.war-1.0-SNAPSHOT.war $WEBAPP_HOME/..
  fi
}
