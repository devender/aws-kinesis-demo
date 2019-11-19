# aws-kinesis-demo

Simple Demo App, when you run the unit test it will write 10 messages to a Kinesis data stream and read from it.

## Before you run
Please `application.properties` in src/main/resources, add your aws credentials and change the kinesis stream.

## AWS IAM Roles Required

* AmazonDynamoDBFullAccess
* AmazonKinesisFullAccess

Expected output when the test is run.
```
INFO  ...  Received Message: {Time=1574119709211}
INFO  ...  Received Message: {Time=1574120113272}
INFO  ...  Received Message: {Time=1574120114302}
INFO  ...  Received Message: {Time=1574120115306}
INFO  ...  Received Message: {Time=1574120116308}
INFO  ...  Received Message: {Time=1574120117314}
INFO  ...  Received Message: {Time=1574120118317}
INFO  ...  Received Message: {Time=1574120119321}
INFO  ...  Received Message: {Time=1574120120322}
INFO  ...  Received Message: {Time=1574120121325}
```

## AWS Kinesis Streams Notes:
*   Shards
    *   A stream consist of a one or more shards.
    *   Each shard represents a fixed unit of capacity.
    *   What this means is: the more capacity you need for a stream, the more shards you will need to add.
    *   You can increase/decrease the number of shards at any time.
    *   Each shard can support:
        *   1000 writes/sec OR
        *   Write rate of 1 MB/sec OR
        *   5 reads/sec OR
        *   Read rate of 2 MB/sec
        
* Record
    *   A record consist of a sequence number, a partition key and data blob.
    *   Data blob can be upto 1 MB.
    *   Max retention period of 7 days, defaults to 24 hours.
    *   Can change the retention period after creating the stream

* Behind the scenes     
    *   Unlike Apache Kafka the AWS Kinesis does not provide out-of-the-box support for consumer groups. 
    *   The support of this feature is implemented as a part of MetadataStore key for shard checkpoints in the KinesisMessageDrivenChannelAdapter
    *   And uses Amazon DynamoDB under the covers 
    *   Amazon Kinesis Client Library (KCL) automatically creates an Amazon DynamoDB table for each Amazon Kinesis Application to track and maintain state information such as resharding events and sequence number checkpoints
    *   In addition the LockRegistry is used to ensure exclusive access to each shard. This way only one channel adapter in the same consumer group will consumer messages from a single shard in the stream it is configured for.

* Links
    *   [Common Pitfalls](https://www.youtube.com/watch?v=MELPeni0p04)
    *   [Limits](https://aws.amazon.com/kinesis/data-streams/faqs/)
    *   [Spring Cloud Kinesis binder](https://github.com/spring-cloud/spring-cloud-stream-binder-aws-kinesis/blob/master/spring-cloud-stream-binder-kinesis-docs/src/main/asciidoc/overview.adoc)
    *   [Lessons Learnt](https://tech.trivago.com/2018/07/13/aws-kinesis-with-lambdas-lessons-learned/)
