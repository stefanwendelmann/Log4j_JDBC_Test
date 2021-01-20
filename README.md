# Summary

This Project represents the multithreading problem in combination with log4j2  
2.14.0 and its jdbc logger.

The Test (src/test/de/itout/test/log4j_jdbc_test/LoggerTest.java) shows that 
the Threads are not running parallel:

img/jdbc_logger_multithreading.gif

# Requirements 

- JDK 1.8
- Maven 3.X
- SQL Server DB

# Steps to reproduce the problem

- Create a Database named SPortal on the SQL Server `IF db_id(N'SPortal') IS NULL CREATE DATABASE SPortal COLLATE Latin1_General_CS_AS`
- Create the schama on the Database (see src/test/resources/schema.sql)
- Edit the SQL Connection infos in src/test/resources/log4j2-test.xml 
- Run the Test `mvn verify`
- Analyse the threads for eg. with visualVM

