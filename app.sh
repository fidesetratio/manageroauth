#!/bin/bash


start(){
   nohup java -jar /home/patartimotius/applicationtesting/manageroauth/manageroauth-0.0.1-SNAPSHOT.jar > /home/patartimotius/applicationtesting/manageroauth/log.txt 2>&1 &	
	echo $! > /home/patartimotius/applicationtesting/manageroauth/pid.file
}

stop(){
 if [ -f /home/patartimotius/applicationtesting/manageroauth/pid.file ]; then
 if ps -p $(cat /home/patartimotius/applicationtesting/manageroauth/pid.file) > /dev/null;then
 	kill $(cat /home/patartimotius/applicationtesting/manageroauth/pid.file) > /dev/null
	echo 'kill them...'
 fi
fi

}

command=$1

 if [ $command == 'start' ]
 then
	start
  elif [ $command == 'stop' ]
  then	
	stop
 fi

echo $command
