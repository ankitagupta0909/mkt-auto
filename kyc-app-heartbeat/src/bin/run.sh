#!/bin/sh

BATCH_DIR="$(dirname $0)"
BATCH_DIR="$(dirname $BATCH_DIR)"

java -cp $BATCH_DIR:$BATCH_DIR/lib/*:/etc/kyc-config -Duser.timezone=UTC -Dlog4j.configuration=$BATCH_DIR/citrus/resources/log4j.xml org.testng.TestNG $BATCH_DIR/test/heartbeat.xml >> /var/log/kyc-heartbeat/out.txt
