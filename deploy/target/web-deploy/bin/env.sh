JBOSS_HOME=${juinfo_jbosshome}
JBOSS_SERVER_HOME=${juinfo_jbossserverhome}

JBOSS_SERVER_DIR=" -Djboss.server.home.dir=$JBOSS_SERVER_HOME -Djboss.server.home.url=file:$JBOSS_SERVER_HOME "
export NAMING_PORT=7003

export JBOSS_HOME JBOSS_SERVER_HOME JBOSS_SERVER_DIR
