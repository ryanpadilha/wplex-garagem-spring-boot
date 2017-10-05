if [ -z $1 ]; then
  ENV="dev";
else
  ENV=$1
fi

java -Xms256m -Xmx512m -Dlogging.config=../config/logback.xml -Dspring.config.location=../config/application-$ENV.properties -jar ../lib/wplex-garagem.jar