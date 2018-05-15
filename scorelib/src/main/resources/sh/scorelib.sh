#!/bin/bash

DATADIR=`dirname $0`/../lib
LIBDIR=`dirname $0`/../lib
VARDIR=`dirname $0`/../var
JAR=$LIBDIR/scorelib-1.0-SNAPSHOT.jar

if [ "$1" = "start" ]; then
	java -Dendpoints.shutdown.enabled=true -Dendpoints.shutdown.sensitive=false -jar $JAR -classpath=$DATADIR &
	echo $! > $VARDIR/scorelib.pid
	disown
	curl --retry 15 --retry-delay 2 --retry-connrefused -s --show-error http://localhost:8080/actuator/health
elif [ "$1" = "stop" ]; then
	curl -X POST http://localhost:8080/shutdown
	rm -f $VARDIR/scorelib.pid
else 
	echo "usage: $0 [start|stop]"
fi
