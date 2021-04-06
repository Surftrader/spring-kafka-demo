# Spring + Kafka

### Step 1:
[Download the latest Kafka release and extract it](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.7.0/kafka_2.13-2.7.0.tgz)

*~ tar -xzf kafka_2.13-2.7.0.tgz*  
*~ cd kafka_2.13-2.7.0*

### Step 2:
### Start the Kafka Environment:
### Start the ZooKeeper service.  
Note: Soon, ZooKeeper will no longer be required by Apache Kafka.  
Run the following commands in order to start all services in the correct order:  

*~ bin/zookeeper-server-start.sh config/zookeeper.properties*

###Start the Kafka broker service  
Open another terminal session and run:  
*~ bin/kafka-server-start.sh config/server.properties*

### Step 3:
### Create a topic to store your events  
So before you can write your first events, you must create a topic. Open another terminal session and run:  
*~ bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic spring-kafka-demo --partitions 1 --replication-factor 1* 

All of Kafka's command line tools have additional options: run the kafka-topics.sh command without any arguments to display usage information. For example, it can also show you details such as the partition count of the new topic:  

*~ bin/kafka-topics.sh --describe --topic spring-kafka-demo --bootstrap-server localhost:9092*

### Step 4:
### Read the events  
Open another terminal session and run the console consumer client to read the events you just created:  

*~ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic spring-kafka-demo*  

### Step 5:  
Run this Application

### To terminate the Kafka environment:  
1.  Stop the producer and consumer clients with Ctrl-C, if you haven't done so already.
2.  Stop the Kafka broker with Ctrl-C.
3.  Lastly, stop the ZooKeeper server with Ctrl-C

If you also want to delete any data of your local Kafka environment including any events you have created along the way, run the command:  

*~ rm -rf /tmp/kafka-logs /tmp/zookeeper*

