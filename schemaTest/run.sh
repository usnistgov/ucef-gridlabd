#!/bin/bash
cp -r conf/ target
cd target
java -Djava.net.preferIPv4Stack=true -jar schema-test-0.0.1-SNAPSHOT.jar conf/SchemaTest.json

