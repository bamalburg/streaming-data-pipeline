package com.labs1904.hwe

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Get, Put, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.logging.log4j.{LogManager, Logger}

import scala.collection.convert.ImplicitConversions.`iterator asScala`

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

      // Challenge 1
//      val get = new Get(Bytes.toBytes("10000001"))
//      val result = table.get(get)
//      val userEmail_10000001 = Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail")))
//      println(userEmail_10000001)

      // Challenge 2
//      val put = new Put(Bytes.toBytes("99"))
//      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("username"), Bytes.toBytes("DE-HWE"))
//      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("The Panther"))
//      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("sex"), Bytes.toBytes("F"))
//      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("favorite_color"), Bytes.toBytes("pink"))
//      table.put(put)
//
//      val get = new Get(Bytes.toBytes("99"))
//      val result = table.get(get)
//      val favoriteColor = Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("favorite_color")))
//      println(favoriteColor)

      // Challenge 3
//      val scan = new Scan().withStartRow(Bytes.toBytes("10000001")).withStopRow(Bytes.toBytes("10006001"))
//      val scanner = table.getScanner(scan).iterator()
//      println(scanner.size)
////      https://hbase.apache.org/2.1/apidocs/org/apache/hadoop/hbase/client/Scan.html


      // Challenge 4
//      Challenge #4: Delete the user with ID = 99 that we inserted earlier.
//        Delete
//      val myDeleteObject = new Delete("rowkey") //Deletes entire row
//
//      https://hbase.apache.org/2.1/apidocs/org/apache/hadoop/hbase/client/Delete.html
        // START HERE //







      //      logger.debug(result)
    } catch {
      case e: Exception => logger.error("Error in main", e)
    } finally {
      if (connection != null) connection.close()
    }
  }
}
