call mvn clean package -Dmaven.test.skip=true -pl jscsqm-web,jscsqm-jbpm-server -am -f ../pom.xml -P profile-js-test
pause