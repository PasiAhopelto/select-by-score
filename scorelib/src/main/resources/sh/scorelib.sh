#!/bin/bash

JAR=scorelib-1.0-SNAPSHOT-jar-with-dependencies.jar

if [ "$1" = "start" ]; then
	java -jar $JAR
elif [ "$1" = "stop" ]; then
else 
	echo "usage: $0 [start|stop]"
fi
