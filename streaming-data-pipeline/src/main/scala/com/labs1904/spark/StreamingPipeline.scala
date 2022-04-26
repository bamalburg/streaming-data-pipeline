package com.labs1904.spark

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Get}
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.hadoop.hbase.util.Bytes

/**
 * Spark Structured Streaming app
 *
 */

case class Review(
  marketplace: String,
  customer_id: Int,
  review_id: String,
  product_id: String,
  product_parent: Int,
  product_title: String,
  product_category: String,
  star_rating: Int,
  helpful_votes: Int,
  total_votes: Int,
  vine: String,
  verified_purchase: String,
  review_headline: String,
  review_body: String,
  review_date: String
)


object StreamingPipeline {
  lazy val logger: Logger = Logger.getLogger(this.getClass)
  val jobName = "StreamingPipeline"

  val hdfsUrl = "hdfs://hbase02.hourswith.expert:8020"
  val bootstrapServers = "b-2-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-1-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196,b-3-public.hwe-kafka-cluster.l384po.c8.kafka.us-west-2.amazonaws.com:9196"
  val username = "hwe"
  val password = "1904labs"
  val hdfsUsername = "bmalburg:users" // TODO: set this to your handle

  //Use this for Windows
  //val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  implicit def stringToBytes(str: String): Array[Byte] = Bytes.toBytes(str)
  implicit def bytesToString(bytes: Array[Byte]): String = Bytes.toString(bytes)

  def main(args: Array[String]): Unit = {
    var connection: Connection = null
    try {
      val spark = SparkSession.builder()
        .config("spark.sql.shuffle.partitions", "3")
        .appName(jobName)
        .master("local[*]")
        .getOrCreate()

      val conf = HBaseConfiguration.create()
      conf.set("hbase.zookeeper.quorum", "hbase02.hourswith.expert:2181")
      val connection = ConnectionFactory.createConnection(conf)
      val table = connection.getTable(TableName.valueOf("bmalburg:users"))

      import spark.implicits._

      val ds = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", bootstrapServers)
        .option("subscribe", "reviews")
        .option("startingOffsets", "earliest")
        .option("maxOffsetsPerTrigger", "20")
        .option("startingOffsets","earliest")
        .option("kafka.security.protocol", "SASL_SSL")
        .option("kafka.sasl.mechanism", "SCRAM-SHA-512")
        .option("kafka.ssl.truststore.location", trustStore)
        .option("kafka.sasl.jaas.config", getScramAuthString(username, password))
        .load()
        .selectExpr("CAST(value AS STRING)")
        .as[String]

      // TODO: implement logic here
      val result = ds.map(row => row.split("\t"))

      val reviews = result.map(row =>
        Review(
          row(0), row(1).toInt, row(2), row(3), row(4).toInt, row(5),
          row(6), row(7).toInt, row(8).toInt, row(9).toInt, row(10), row(11),
          row(12), row(13), row(14)
        ))




      val query = reviews.writeStream
        .outputMode(OutputMode.Append())
        .format("console")
        .option("truncate", false)
        .trigger(Trigger.ProcessingTime("5 seconds"))
        .start()




      // [START HERE]

//      Use the customer_id contained within the review message to lookup corresponding user data in HBase.
//        Construct a HBase get request for every review message. The customer_id corresponds to a HBase rowkey.
//        Tip: Open up a connection per partition, instead of per row

      // Just trying to get a single one to print...not sure why it's not working
      // Maybe this is a bad approach and I should instead just try to get all the info somehow,
      // as part of the streaming, and print to console along the way to check if I'm doing it right,
      // and then do whatever else comes after that?
//      val customerToGet = new Get(Bytes.toBytes("24617080"))
//      val result = table.get(customerToGet)
//      val test = Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail")))
//      println(test)






      // Write output to HDFS
//      val query = result.writeStream
//        .outputMode(OutputMode.Append())
//        .format("json")
//        .option("path", s"/user/${hdfsUsername}/reviews_json")
//        .option("checkpointLocation", s"/user/${hdfsUsername}/reviews_checkpoint")
//        .trigger(Trigger.ProcessingTime("5 seconds"))
//        .start()



      query.awaitTermination()

    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }

  def getScramAuthString(username: String, password: String) = {
    s"""org.apache.kafka.common.security.scram.ScramLoginModule required
   username=\"$username\"
   password=\"$password\";"""
  }

  def splitString(string: String): Array[String] = {
    string.split(" ")
  }
}
