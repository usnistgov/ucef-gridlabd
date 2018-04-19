#!/bin/bash
cd target
cp ../RTI.rid .
cp -r ../conf/ .
java -Djava.net.preferIPv4Stack=true -jar gridlabd-federate-0.1.0-SNAPSHOT.jar conf/GridLAB-D.json

