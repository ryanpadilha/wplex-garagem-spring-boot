#!/bin/bash

sudo /var/wplex/apps/wplex-garagem/bin/stop.sh
sudo rm -rf /var/wplex/apps/wplex-garagem
sudo tar -zxvf /var/wplex/apps/artifact/wplex-garagem-*-dev.tar.gz -C /var/wplex/apps/
sudo rm -rf /var/wplex/apps/wplex-garagem/*.properties
sudo cp /var/wplex/apps/application-prd-garagem.properties /var/wplex/apps/wplex-garagem/config
sudo /var/wplex/apps/wplex-garagem/bin/start.sh prd-garagem
