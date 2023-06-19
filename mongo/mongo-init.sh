#!/bin/sh

set -e

mongosh <<EOF
db = db.getSiblingDB('football')

db.createUser({
  user: 'football',
  pwd: '$FOOTBALL_PASSWORD',
  roles: [{ role: 'readWrite', db: 'football' }],
});
db.createCollection('standings')

db = db.getSiblingDB('app_config')

db.createUser({
  user: 'app_config',
  pwd: '$APP_CONFIG_PASSWORD',
  roles: [{ role: 'readWrite', db: 'app_config' }],
});
db.createCollection('api-scraper')

EOF