#!/bin/sh

set -e

export CATALINA_HOME=/usr/share/tomcat9
export CATALINA_BASE=/var/lib/tomcat9
export CATALINA_TMPDIR=/tmp
export JAVA_OPTS=-Djava.awt.headless=true

/bin/sh /usr/libexec/tomcat9/tomcat-start.sh
