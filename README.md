# Summary

This Project represents the multithreading problem in combination with log4j2  
2.14.0 and its jdbc logger.

The Test shows that 
the Threads are not running parallel:

[src/main/de/itout/test/log4j_jdbc_test/LoggerTest.java](src/test/de/itout/test/log4j_jdbc_test/LoggerTest.java)

![Alt text](img/jdbc_logger_multithreading.gif?raw=true "Multithreading")


I added a Flight Recording File under: [recording.jfr](recording.jfr)

## Testsystem

OS: Win10 Pro 2004 Build 19041.572

Java: OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.282-b08, mixed mode)

Processor: Intel(R) Core(TM) i7-8550U CPU @ 1.80GHz (8 CPUs), ~2.0GHz

Memory: 32768MB RAM


# Requirements 

- JDK 1.8 (I tested it with hotspot OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.282-b08, mixed mode))
- Maven 3.X
- SQL Server DB

# Steps to reproduce the problem

- Create a Database named SPortal on the SQL Server `IF db_id(N'SPortal') IS NULL CREATE DATABASE SPortal COLLATE Latin1_General_CS_AS`
- Create the schama on the Database (see [src/main/resources/schema.sql](src/test/resources/schema_mssql.sql))
- Edit the SQL Connection infos in src/main/resources/log4j2.xml 
- Build it `mvn clean install`
- copy the file Log4j_JDBC_Test-0.0.1-SNAPSHOT.jar to the lib dir
- Run it `cd target` `java -XX:StartFlightRecording:filename=recording.jfr -cp lib\* de.itout.test.log4j_jdbc_test.LoggerTest`
- Analyse the threads for eg. with visualVM

