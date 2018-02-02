# Airline [![Build Status](https://travis-ci.org/Olezha/Airline.svg?branch=master)](https://travis-ci.org/Olezha/Airline) [![codecov](https://codecov.io/gh/Olezha/Airline/branch/master/graph/badge.svg)](https://codecov.io/gh/Olezha/Airline) [![Dependency Status](https://www.versioneye.com/user/projects/5a72e8a80fb24f5752c0f584/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5a72e8a80fb24f5752c0f584)
## Company that stores airplanes
### Instructions for launching
After cloning the repository,
run the following commands on the root folder of the repository

Optional: to switch to an `entry task stage` run ```git checkout c89cc42```
```sh
mvn clean install
java -jar target/airline-1.0.jar
```

### Usage 
Enter ```help``` to list all commands
```sh
Airline (to display available commands type help)
airline>help
AVAILABLE COMMANDS

Airline Shell Controller
        add: Add aircraft
        cc: Carrying capacity of all the aircraft in the airline
        delete: Delete
        fuel: Airplanes corresponding to a given range of fuel consumption parameters
        mock: Simulate objects
        search: Search
        show: Show all aircraft
        sort: List of aircraft of the company sorted by flight range
        tc: Total capacity of all the aircraft in the airline

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error


airline>|
```

Here's the sample session:
```sh
/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/java -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -javaagent:/opt/idea-IU-171.4424.56/lib/idea_rt.jar=38013:/opt/idea-IU-171.4424.56/bin -Dfile.encoding=UTF-8 -classpath /usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/charsets.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/cldrdata.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/icedtea-sound.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/jaccess.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/nashorn.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/jce.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/jsse.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/management-agent.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/resources.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/rt.jar:/home/oleh/workspace/Airline/target/classes:/home/oleh/.m2/repository/org/springframework/boot/spring-boot-starter-data-jpa/1.5.9.RELEASE/spring-boot-starter-data-jpa-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/boot/spring-boot-starter/1.5.9.RELEASE/spring-boot-starter-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/boot/spring-boot/1.5.9.RELEASE/spring-boot-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/1.5.9.RELEASE/spring-boot-autoconfigure-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/boot/spring-boot-starter-logging/1.5.9.RELEASE/spring-boot-starter-logging-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/ch/qos/logback/logback-classic/1.1.11/logback-classic-1.1.11.jar:/home/oleh/.m2/repository/ch/qos/logback/logback-core/1.1.11/logback-core-1.1.11.jar:/home/oleh/.m2/repository/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.jar:/home/oleh/.m2/repository/org/slf4j/log4j-over-slf4j/1.7.25/log4j-over-slf4j-1.7.25.jar:/home/oleh/.m2/repository/org/yaml/snakeyaml/1.17/snakeyaml-1.17.jar:/home/oleh/.m2/repository/org/springframework/boot/spring-boot-starter-aop/1.5.9.RELEASE/spring-boot-starter-aop-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/spring-aop/4.3.13.RELEASE/spring-aop-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/aspectj/aspectjweaver/1.8.13/aspectjweaver-1.8.13.jar:/home/oleh/.m2/repository/org/springframework/boot/spring-boot-starter-jdbc/1.5.9.RELEASE/spring-boot-starter-jdbc-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/org/apache/tomcat/tomcat-jdbc/8.5.23/tomcat-jdbc-8.5.23.jar:/home/oleh/.m2/repository/org/apache/tomcat/tomcat-juli/8.5.23/tomcat-juli-8.5.23.jar:/home/oleh/.m2/repository/org/springframework/spring-jdbc/4.3.13.RELEASE/spring-jdbc-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/hibernate/hibernate-core/5.0.12.Final/hibernate-core-5.0.12.Final.jar:/home/oleh/.m2/repository/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.jar:/home/oleh/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.0.Final/hibernate-jpa-2.1-api-1.0.0.Final.jar:/home/oleh/.m2/repository/org/javassist/javassist/3.21.0-GA/javassist-3.21.0-GA.jar:/home/oleh/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar:/home/oleh/.m2/repository/org/jboss/jandex/2.0.0.Final/jandex-2.0.0.Final.jar:/home/oleh/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar:/home/oleh/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.1.Final/hibernate-commons-annotations-5.0.1.Final.jar:/home/oleh/.m2/repository/org/hibernate/hibernate-entitymanager/5.0.12.Final/hibernate-entitymanager-5.0.12.Final.jar:/home/oleh/.m2/repository/javax/transaction/javax.transaction-api/1.2/javax.transaction-api-1.2.jar:/home/oleh/.m2/repository/org/springframework/data/spring-data-jpa/1.11.9.RELEASE/spring-data-jpa-1.11.9.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/data/spring-data-commons/1.13.9.RELEASE/spring-data-commons-1.13.9.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/spring-orm/4.3.13.RELEASE/spring-orm-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/spring-context/4.3.13.RELEASE/spring-context-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/spring-expression/4.3.13.RELEASE/spring-expression-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/spring-tx/4.3.13.RELEASE/spring-tx-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/spring-beans/4.3.13.RELEASE/spring-beans-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/oleh/.m2/repository/org/slf4j/jcl-over-slf4j/1.7.25/jcl-over-slf4j-1.7.25.jar:/home/oleh/.m2/repository/org/springframework/spring-aspects/4.3.13.RELEASE/spring-aspects-4.3.13.RELEASE.jar:/home/oleh/.m2/repository/org/springframework/shell/spring-shell-starter/2.0.0.M2/spring-shell-starter-2.0.0.M2.jar:/home/oleh/.m2/repository/org/springframework/shell/spring-shell-core/2.0.0.M2/spring-shell-core-2.0.0.M2.jar:/home/oleh/.m2/repository/org/springframework/boot/spring-boot-starter-validation/1.5.9.RELEASE/spring-boot-starter-validation-1.5.9.RELEASE.jar:/home/oleh/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/8.5.23/tomcat-embed-el-8.5.23.jar:/home/oleh/.m2/repository/org/hibernate/hibernate-validator/5.3.6.Final/hibernate-validator-5.3.6.Final.jar:/home/oleh/.m2/repository/javax/validation/validation-api/1.1.0.Final/validation-api-1.1.0.Final.jar:/home/oleh/.m2/repository/com/fasterxml/classmate/1.3.4/classmate-1.3.4.jar:/home/oleh/.m2/repository/org/jline/jline/3.4.0/jline-3.4.0.jar:/home/oleh/.m2/repository/org/jline/jline-terminal-jna/3.4.0/jline-terminal-jna-3.4.0.jar:/home/oleh/.m2/repository/net/java/dev/jna/jna/4.2.2/jna-4.2.2.jar:/home/oleh/.m2/repository/org/jline/jline-terminal/3.4.0/jline-terminal-3.4.0.jar:/home/oleh/.m2/repository/org/springframework/shell/spring-shell-standard/2.0.0.M2/spring-shell-standard-2.0.0.M2.jar:/home/oleh/.m2/repository/org/springframework/shell/spring-shell-standard-commands/2.0.0.M2/spring-shell-standard-commands-2.0.0.M2.jar:/home/oleh/.m2/repository/org/springframework/shell/spring-shell-shell1-adapter/2.0.0.M2/spring-shell-shell1-adapter-2.0.0.M2.jar:/home/oleh/.m2/repository/org/springframework/shell/spring-shell-jcommander-adapter/2.0.0.M2/spring-shell-jcommander-adapter-2.0.0.M2.jar:/home/oleh/.m2/repository/org/springframework/shell/spring-shell-table/2.0.0.M2/spring-shell-table-2.0.0.M2.jar:/home/oleh/.m2/repository/org/apache/derby/derby/10.13.1.1/derby-10.13.1.1.jar:/home/oleh/.m2/repository/org/projectlombok/lombok/1.16.18/lombok-1.16.18.jar:/home/oleh/.m2/repository/org/springframework/spring-core/4.3.13.RELEASE/spring-core-4.3.13.RELEASE.jar ua.olezha.airline.AirlineApplication
Airline (to display available commands type help)
airline>show
┌──┬────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
└──┴────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>mock
airline>show
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│1 ╎COMMUTERLINER     ╎10              ╎2000                 ╎10000           ╎150                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│2 ╎HELICOPTER        ╎18              ╎800                  ╎3050            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│3 ╎WIDE_BODY_AIRLINER╎200             ╎18000                ╎14300           ╎555                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│5 ╎HELICOPTER        ╎4               ╎2110                 ╎2300            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│7 ╎COMMUTERLINER     ╎101             ╎2014                 ╎1675            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│8 ╎HELICOPTER        ╎9               ╎900                  ╎1900            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│9 ╎WIDE_BODY_AIRLINER╎1020            ╎12000                ╎10100           ╎1600                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│10╎WIDE_BODY_AIRLINER╎555             ╎14500                ╎9900            ╎2500                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│12╎HELICOPTER        ╎24              ╎2600                 ╎900             ╎630                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│14╎COMMUTERLINER     ╎189             ╎1600                 ╎19000           ╎2250                 │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>tc
3084
airline>cc
75924
airline>fuel 800 1400
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>sort
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│12╎HELICOPTER        ╎24              ╎2600                 ╎900             ╎630                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│7 ╎COMMUTERLINER     ╎101             ╎2014                 ╎1675            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│8 ╎HELICOPTER        ╎9               ╎900                  ╎1900            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│5 ╎HELICOPTER        ╎4               ╎2110                 ╎2300            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│2 ╎HELICOPTER        ╎18              ╎800                  ╎3050            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│10╎WIDE_BODY_AIRLINER╎555             ╎14500                ╎9900            ╎2500                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│1 ╎COMMUTERLINER     ╎10              ╎2000                 ╎10000           ╎150                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│9 ╎WIDE_BODY_AIRLINER╎1020            ╎12000                ╎10100           ╎1600                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│3 ╎WIDE_BODY_AIRLINER╎200             ╎18000                ╎14300           ╎555                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│14╎COMMUTERLINER     ╎189             ╎1600                 ╎19000           ╎2250                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>sort -desc
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│14╎COMMUTERLINER     ╎189             ╎1600                 ╎19000           ╎2250                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│3 ╎WIDE_BODY_AIRLINER╎200             ╎18000                ╎14300           ╎555                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│9 ╎WIDE_BODY_AIRLINER╎1020            ╎12000                ╎10100           ╎1600                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│1 ╎COMMUTERLINER     ╎10              ╎2000                 ╎10000           ╎150                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│10╎WIDE_BODY_AIRLINER╎555             ╎14500                ╎9900            ╎2500                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│2 ╎HELICOPTER        ╎18              ╎800                  ╎3050            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│5 ╎HELICOPTER        ╎4               ╎2110                 ╎2300            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│8 ╎HELICOPTER        ╎9               ╎900                  ╎1900            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│7 ╎COMMUTERLINER     ╎101             ╎2014                 ╎1675            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│12╎HELICOPTER        ╎24              ╎2600                 ╎900             ╎630                  │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>add HELICOPTER 1 2 3 4
airline>fuel 0 10
┌──┬──────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type      ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│15╎HELICOPTER╎1               ╎2                    ╎3               ╎4                    │
└──┴──────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>help add


NAME
	add - Add aircraft

SYNOPSYS
	add [-aircraft-type] aircraft-type  [[-seating-capacity] int]  [[-carrying-capacity-kg] int]  [[-flight-range-km] int]  [[-fuel-consumption-liters-per-hour] int]  

OPTIONS
	-aircraft-type  aircraft-type
		Type [WIDE_BODY_AIRLINER|COMMUTERLINER|HELICOPTER]
		[Mandatory]

	-seating-capacity  int
		Seating capacity
		[Optional, default = 0]

	-carrying-capacity-kg  int
		Carrying capacity (kg)
		[Optional, default = 0]

	-flight-range-km  int
		Flight range (km)
		[Optional, default = 0]

	-fuel-consumption-liters-per-hour  int
		Fuel consumption (liters per hour)
		[Optional, default = 0]



airline>add HELICOPTER -seating-capacity 8
airline>fuel 0 10
┌──┬──────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type      ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│15╎HELICOPTER╎1               ╎2                    ╎3               ╎4                    │
├╌╌┼╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│16╎HELICOPTER╎8               ╎0                    ╎0               ╎0                    │
└──┴──────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>exit

Process finished with exit code 0
```
