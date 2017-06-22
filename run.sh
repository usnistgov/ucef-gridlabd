#!/bin/bash
PROJECT_DIR=`pwd`
FED_MANAGER_DIR=$PROJECT_DIR/fedmanager
GLD_FEDERATE_DIR=$PROJECT_DIR/gldfederate
TEST_FEDERATION_DIR=$PROJECT_DIR/testfederation/GLDTestFederation_deployment

cd $FED_MANAGER_DIR

echo "Running the federation manager..."
xterm -T "Federation Manager" -sl 2048 -hold -e "RTI_RID_FILE=$FED_MANAGER_DIR/RTI.rid mvn exec:java -P FederationManagerExecJava" &

read -rsp $'Press any key to continue...\n' -n1 key

cd $GLD_FEDERATE_DIR

echo "Running the gridlabd federate..."
xterm -T "GridLAB-D" -sl 2048 -hold -e "mvn exec:exec -Dconfiguration.filepath=$GLD_FEDERATE_DIR/config.yml" &

cd $TEST_FEDERATION_DIR

echo "Running the TestFederate federate..."
xterm -T "Test Federate" -sl 2048 -hold -e "mvn exec:exec -P TestFederate,JavaFed" &

echo "Starting the federation..."
curl -i -X POST http://127.0.0.1:8083/api/fedmgr --data '{"action": "START"}' -H "Content-Type: application/json" 

