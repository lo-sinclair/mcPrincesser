#!/usr/bin/env bash

getdir1() {
  p=($(jps -v | awk '{print $1}'))
  pwdx ${p} | awk '{print $2}'
  #echo ${p[1]}
}

getdir(){
  p=($(pgrep -f mcserver.jar))
  pwdx ${p} | awk '{print $2}'
}

$1