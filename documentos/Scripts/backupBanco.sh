#!/bin/sh
# mysqlBackup.sh

DATA=`/bin/date+%Y%m%d`

# NOME armazena o nome do arquivo de backup, neste casoi
# o diretório é /home/smart/backup, configure aqui o seu
# diretório de backup
NOME="/home/smart/backup/dbgac-$DATA.sql"

# variáveis do MySQL
HOST="localhost"
USER="root"
PASSWORD="root"
DATABASE="dbgac"

mysqldump -h $HOST -u $USER -p$PASSWORD > $NOME
