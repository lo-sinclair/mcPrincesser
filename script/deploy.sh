#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp target/mcPrincesser2-1.0-SNAPSHOT.jar t1:/home/mc/

#echo 'Restart server...'
##
#ssh t1 << EOF
#pgrep -f 'mcprincesser.Application' | xargs kill -9
#nohup java -jar /home/mc/MCPrincesser2-1.0-SNAPSHOT.jar > log.txt &
#EOF

echo 'bye!'