#!/bin/bash
#
# Stop Java Application on AWS environment

PID=$(cat /var/run/wplex-garagem.pid)
kill -9 $PID
