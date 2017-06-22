#!/bin/bash
BUILD_DIR=`pwd`
PROJECT_NAME=GLDTestFederation

echo "Building the _generated project..."
cd $BUILD_DIR/${PROJECT_NAME}_generated
mvn clean install

echo "Building the base project..."
cd $BUILD_DIR/${PROJECT_NAME}
mvn clean install

echo "Building the _deployment project..."
cd $BUILD_DIR/${PROJECT_NAME}_deployment
mvn clean install
