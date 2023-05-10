#!/bin/sh

echo "Pooling the config server"
defaultPort=9091
defaultTimeout=300

if [[ ! -z $CONFIG_SERVER_URI ]]; then
  if [[ -z $CONFIG_SERVER_PORT ]]; then 
    echo "Config server port is not set. Using default port"
    $CONFIG_SERVER_PORT=$defaultPort
    $CONFIG_SERVER_TIMEOUT=$defaultTimeout
  fi
  sh ./app/wait-for-it.sh -u $CONFIG_SERVER_URI -p $CONFIG_SERVER_PORT -e /actuator -t $CONFIG_SERVER_TIMEOUT -s
else 
  echo "Config server not provided."
fi

retval=$?
echo $retval

if [ $retval -eq 0 ]; then
  echo "Starting application"
  java $JAVA_OPTS -cp @/app/jib-classpath-file $ENTRYPOINT
  started=$?
  if [[ $started == 0 ]]; then
    echo "Application started"
  else 
    echo "Application failed to start."
  fi
fi