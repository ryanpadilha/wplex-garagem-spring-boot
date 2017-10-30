#!/bin/bash
#
# Stop Java Application on AWS environment

echo "stop wplex-garagem application..."

PID=$(cat /var/run/wplex-garagem.pid)
kill -9 $PID
