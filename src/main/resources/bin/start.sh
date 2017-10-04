ENV=$1
if [ $ENV == '' ]
then
$ENV = "dev";
fi

java -Xms256m -Xmx512m -Dlogging.config=../config/logback.xml -Dspring.config.location=../config/application-$ENV.properties -jar ../lib/wplex-garagem.jar