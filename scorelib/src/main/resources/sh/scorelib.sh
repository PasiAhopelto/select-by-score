#!/bin/bash

LIBDIR=`dirname $0`/../lib
JAR=$LIBDIR/scorelib-1.0-SNAPSHOT.jar

if [ "$1" = "start" ]; then
	java -jar $JAR &
	disown
elif [ "$1" = "stop" ]; then
	1
else 
	echo "usage: $0 [start|stop]"
fi
