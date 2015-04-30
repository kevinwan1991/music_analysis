#!/bin/bash
source /home/ec2-user/.bash_profile
mvn compile
mvn package
sudo cp /home/ec2-user/applications/musicA/target/musicA-1.0.war /home/ec2-user/opt/apache-tomcat-7.0.59/webapps/musicA-1.0.war
