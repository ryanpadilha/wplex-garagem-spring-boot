#!/bin/bash
# PostgreSQL database dump

# troubleshooting for
# pg_dump: server version: 9.6.2; pg_dump version: 9.5.6
# pg_dump: aborting because of server version mismatch
#
# commands to verify version mismatch on linux environment
# psql --version
# pg_dump --version
# which pg_dump
# ls -la /usr/bin/pg_dump
# dpkg -l | grep postgres

# Ubuntu Xenial 16.04 :: install the correct postgresql-client-version
#
# sudo add-apt-repository "deb http://apt.postgresql.org/pub/repos/apt/ xenial-pgdg main"
# wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
# sudo apt-get update
# sudo apt-get install postgresql-9.6

start_date="`date +%Y-%m-%d_%H:%M:%S`"

DIR="/opt/backup/postgresql"
ERRORLOG="$DIR/pg-error.log"
ERROR=0;

PG_HOST="18.231.79.248"
PG_USER="postgres"
PG_PASS=$1
PG_PORT="5492"
PG_DATABASES="db_2"

PG_CONNECTION="postgresql://$PG_USER:$PG_PASS@$PG_HOST:$PG_PORT/$PG_DATABASES"

if [ -z "$1" ]; then
        echo ">>> dump database password argument not found, exiting...";
        exit
fi

if [ ! -d $DIR ]; then
    mkdir -p $DIR
    chown -R postgres -R $DIR
fi

echo ">>> dump database $PG_DATABASES"
pg_dump $PG_CONNECTION -Fc -v -f $DIR/db-$PG_DATABASES.bkp 2> $ERRORLOG
# pg_dump -d $PG_DATABASES -h $PG_HOST -p $PG_PORT -U $PG_USER -Fc -v -f $DIR/db-$PG_DATABASES.bkp 2> $ERRORLOG

if [ "$?" -ne 0 ]; then
    echo "ERROR to create database dump :: '$PG_DATABASES'";
    ERROR=1;
fi

echo ">>> compact database dump :: $PG_DATABASES"
tar -cvzf $DIR/db-$PG_DATABASES-`date +"%Y%m%d-%H%M%S"`.tgz -C $DIR db-$PG_DATABASES.bkp 2>> $ERRORLOG

if [ "$?" -ne 0 ]; then
    echo "ERROR to compact database dump :: '$PG_DATABASES'";
    ERROR=1;
fi

# clean .bkp files and old compact files, only last 7 days
rm -rf $DIR/*.bkp
find $DIR/ -name "*.tgz" -mtime +7 -type f -exec rm -f {} \;

# AWS S3
aws s3 sync /opt/backup/postgresql/ s3://garagem-pgdump

echo "PG dump process start at $start_date"
echo "PG dump process end   at `date +%Y-%m-%d_%H:%M:%S`"