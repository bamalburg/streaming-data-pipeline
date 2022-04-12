package com.labs1904.hwe.homework

import org.apache.log4j.Logger
import org.apache.spark.sql.{Dataset, SparkSession}

object HelloWorldBatchApp {
  lazy val logger: Logger = Logger.getLogger(this.getClass)
  val jobName = "HelloWorldBatchApp"

  def main(args: Array[String]): Unit = {
    try {
      logger.info(s"$jobName starting...")
      //TODO: What is a spark session - Why do we need this line?
      // Answer: I think it's just...the starting point to any program using spark...? It's a class, anyways. More background below, but I still don't quite understand probably.
      // SparkSession internally creates SparkConfig and SparkContext with the configuration provided with SparkSession.
      // By default Spark shell provides “spark” object which is an instance of SparkSession class. We can directly use this object where required in spark-shell.
      // Spark Session also includes all the APIs available in different contexts (SQL, Hive, Streaming, Spark)
      val spark = SparkSession.builder()
        .appName(jobName)
        .config("spark.sql.shuffle.partitions", "3")
        //TODO- What is local[*] doing here?
        // Answer: Use local[x] when running in Standalone mode. x should be an integer value and should be greater than 0; this represents how many partitions it should create when using RDD, DataFrame, and Dataset.
        // Ideally, x value should be the number of CPU cores you have.
        // So this means we are running in Standalone mode, and the wildcard probably just defaults to how many CPU cores you on your computer when you run it...?
        // confirmed, I believe: https://spark.apache.org/docs/latest/submitting-applications.html#master-urls
        // "Run Spark locally with as many worker threads as logical cores on your machine."
        .master("local[*]")
        //TODO- What does Get or Create do?
        // Answer: returns a SparkSession object if already exists, creates new one if not exists.
        .getOrCreate()

      import spark.implicits._
      val sentences: Dataset[String] = spark.read.csv("src/main/resources/sentences.txt").as[String]
      // print out the names and types of each column in the dataset
      sentences.printSchema
      // display some data in the console, useful for debugging
      //TODO- Make sure this runs successfully
      sentences.show(truncate = false)
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }
}
