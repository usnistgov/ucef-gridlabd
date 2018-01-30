#!/bin/bash
cd GridLabDTestFederation-java-federates/GridLabDTestFederation-impl-java/TestFederate
mvn exec:java -P TestFederate,JavaFed -Dlog4j.configurationFile=conf/log4j2.xml

