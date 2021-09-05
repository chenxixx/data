#!/bin/sh
####################################需根据服务配置#########################################
# 服务名称
SERVER_NAME=dataService
# 启动环境变量
PROFILES=$(cat /project/profile.txt)
# 独立服务文件夹(绝对路径)
SERVER_PATH=/cloud/dataService
SERVER_JAR=${SERVER_PATH}/$SERVER_NAME.jar
SERVER_PROPERTY=${SERVER_PATH}/config/application.yml
LOG_FILE=${SERVER_PATH}/config/logback.xml
JAVA_OPTS_EXT="-Xms256m -Xmx256m -Xss256k -XX:PermSize=64m -XX:MaxNewSize=64m -XX:MaxPermSize=128m -Xdebug -Xrunjdwp:transport=dt_socket,address=8767,server=y,suspend=n"
#####################################################################
# 查文件是否齐全
if [ ! -f $SERVER_JAR ];then
	echo "server bin doesn't exist, path=$SERVER_JAR"
	exit 1
fi
# 校验启动环境变量不为空
echo $PROFILES
if [ ! -n "$PROFILES" ]; then
  echo "PROFILES is empty"
  exit 1
fi
# 监控主进程
while true
do
	pid=$(ps -ef | grep jav[a] | grep "$SERVER_NAME" |awk '{print $2}')
	if [ -z "$pid" ];then
	    java $JAVA_OPTS_EXT -jar -Dspring.config.location=$SERVER_PROPERTY $SERVER_JAR --spring.profiles.active=$PROFILES --logging.config=$LOG_FILE
	fi
	sleep 3
done
