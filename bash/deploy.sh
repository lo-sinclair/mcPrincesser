#!/usr/bin/env bash

#mvn clean package

echo 'Copy files...'

#scp target/MCPrincesser2-1.0-SNAPSHOT.jar t1:/home/mc/

#echo 'Restart server...'
#
#ssh t1 << EOF
#pgrep java | xargs kill -9
#nohup java -jar /home/mc/target/sweater-0.0.1-SNAPSHOT.jar > log.txt &
#EOF

echo 'bye!'