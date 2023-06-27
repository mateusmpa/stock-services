docker pull gvenzl/oracle-xe

brew install colima 

colima start --arch x86_64 --memory 4

docker run -d -p 1521:1521 -e ORACLE_PASSWORD=inicial1234 gvenzl/oracle-xe

docker ps

docker exec -it 760fed9a5ab9 bash

sqlplus / as sysdba

alter session set "_ORACLE_SCRIPT"=true;

create user agumon identified by inicial1234;

grant all privileges to agumon;

CTRL+D

sqlplus

usuário: agumon

senha: inicial1234
