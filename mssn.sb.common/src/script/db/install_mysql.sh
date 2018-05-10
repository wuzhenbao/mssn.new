#!/bin/bash
function install_usage
{
    echo "Usage: install_mysql.sh /home/wuzhenbao/soft/mysql-advanced-5.6.13-linux-glibc2.5-i686.tar.gz /usr/local/mysql /home/root/soft/my.cnf"
}
if [[ $# != 3 ]]; then
    install_usage
    exit 1
fi
PKG_PATH=$1
INSTALL_PATH=$2
MY_CNF=$3
if [[ ! -e $PKG_PATH ]]; then
    echo $PKG_PATH" is not exists."
    exit 1
fi
if [ -d $INSTALL_PATH ];then
    rm -rf $INSTALL_PATH/*
fi
if [[ ! -d $INSTALL_PATH ]]; then
    echo $INSTALL_PATH" is not exists, creation it."
    echo 'wzb-1983!@#456'|sudo mkdir $INSTALL_PATH
fi
if [[ ! -d ./mysql ]]; then
    echo "create temp directory ./mysql"
    mkdir ./mysql
fi
echo "Start unzip "$PKG_PATH
tar -zxvf $PKG_PATH -C ./mysql >/dev/null 2>&1
echo "End unzip "$PKG_PATH
PKG_NAME=${PKG_PATH##*/}
PKG_NAME=${PKG_NAME%.tar.gz}
echo "pkgname is "$PKG_NAME
echo "begin copy files to "$INSTALL_PATH
cp -r ./mysql/$PKG_NAME/* $INSTALL_PATH > /dev/null 2>&1
echo "Copy file end."
rm -rf ./mysql
#create group if not exists
group="mysql"
grep $group /etc/group > /dev/null 2>&1
if [ $? -ne 0 ]; then
   groupadd $group 
   echo "add group $group"
fi
user="mysql"
#create user if not exists
grep $user /etc/passwd > /dev/null 2>&1
if [ $? -ne 0 ]; then
    useradd $user -g $group
    echo "add user $user"
fi
cd $INSTALL_PATH
chown -R mysql:mysql ./
#copy default conf

if [ -e /etc/my.cnf ]; then
    rm /etc/my.cnf
fi
#cp $MY_CNF /etc/
#mv /etc/my-default.cnf /etc/my.cnf
mkdir data
chmod 770 data
bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
chown -R root:root ./ 
chown -R mysql:mysql data
cp support-files/mysql.server /etc/init.d/
systemctl start mysql.service
chkconfig --add mysql
chkconfig --level 345 mysql on
C#aqQv*rI4P,


