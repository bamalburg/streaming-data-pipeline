package com.labs1904.hwe

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Get}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.logging.log4j.{LogManager, Logger}

object MyApp {
  lazy val logger: Logger = LogManager.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("MyApp starting...")
    var connection: Connection = null
    try {
      val conf = HBaseConfiguration.create()
      conf.set("hbase.zookeeper.quorum", "hbase02.hourswith.expert")
      connection = ConnectionFactory.createConnection(conf)
      // Example code... change me
      val table = connection.getTable(TableName.valueOf("bmalburg:users"))
      val get = new Get(Bytes.toBytes("10000001"))
      val result = table.get(get)

      // Challenge 1
      val userEmail_10000001 = Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail")))
      println(userEmail_10000001)

      // Challenge 2
      //      Challenge #2: Write a new user to your table with:
      //      Rowkey: 99
      //      username: DE-HWE
      //      name: The Panther
      //        sex: F
      //      favorite_color: pink
      //      (Note that favorite_color is a new column qualifier in this table,
      //      and we are not specifying some other columns every other record has: DOB, email address, etc.)





      //      logger.debug(result)
    } catch {
      case e: Exception => logger.error("Error in main", e)
    } finally {
      if (connection != null) connection.close()
    }
  }
}
