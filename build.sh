#!/bin/bash
rootdir=`pwd`

cd $rootdir/schema
mvn clean install

cd $rootdir/GridLAB-D
mvn clean install
