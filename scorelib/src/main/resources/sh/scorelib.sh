#!/bin/bash

LIBDIR=`dirname $0`/../lib
VARDIR=`dirname $0`/../var
JAR=$LIBDIR/scorelib-1.0-SNAPSHOT.jar

if [ "$1" = "start" ]; then
	java -jar $JAR &
	echo $! > $VARDIR/scorelib.pid
	disown
elif [ "$1" = "stop" ]; then
	1
else 
	echo "usage: $0 [start|stop]"
fi
