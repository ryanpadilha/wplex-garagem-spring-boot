#!/bin/bash
# PostgreSQL database restore

start_date="`date +%Y-%m-%d_%H:%M:%S`"

DIR="/opt/backup/postgresql"
LOGFILE="$DIR/pg-restore.log"
ERROR=0;

PG_HOST="localhost"
PG_USER="postgres"
PG_PORT="5492"
PG_DATABASES_TO=$1
PG_FILENAME=$2
PG_PASSWD=$3

if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]; then
    echo ">>> restore database arguments[db-to::filename::password] not found, exiting...";
    exit
fi

echo ">>> restore database $PG_DATABASES_TO"
PGPASSWORD=$PG_PASSWD pg_restore -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_DATABASES_TO -v -1 $PG_FILENAME 2> $LOGFILE

if [ "$?" -ne 0 ]; then
    echo "ERROR to restore database dump :: '$PG_DATABASES_TO'";
    ERROR=1;
fi

echo "PG restore process start at $start_date"
echo "PG restore process end   at `date +%Y-%m-%d_%H:%M:%S`"