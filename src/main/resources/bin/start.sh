#!/bin/bash
#
# Start Java Application on AWS environment

if [ -z $1 ]; then
  ENV="dev";
else
  ENV=$1
fi

APPAWSPATH="/var/wplex/apps/wplex-garagem"

echo "starting wplex-garagem application..."

BUILD_ID=dontKillMe nohup nice java -Xms256m -Xmx512m -Dlogging.config=$APPAWSPATH/config/logback-spring.xml -Dspring.config.location=$APPAWSPATH/config/application-$ENV.properties -jar $APPAWSPATH/lib/wplex-garagem.jar &
echo $! > /var/run/wplex-garagem.pid

$SHELL
