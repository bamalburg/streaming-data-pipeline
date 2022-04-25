package com.labs1904.hwe

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Delete, Get, Put, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.logging.log4j.{LogManager, Logger}

import java.util
import java.util.List
import scala.collection.convert.ImplicitConversions.`iterator asScala`

object MyApp {
  lazy val logger: Logger = LogManager.getLogger(this.getClass)

  // Optional helper functions
//  implicit def stringToBytes(str: String): Array[Byte] = Bytes.toBytes(str)
  implicit def bytesToString(bytes: Array[Byte]): String = Bytes.toString(bytes)

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
      // https://hbase.apache.org/2.1/apidocs/org/apache/hadoop/hbase/client/Scan.html
//      val scan = new Scan().withStartRow(Bytes.toBytes("10000001")).withStopRow(Bytes.toBytes("10006001"))
//      val scanner = table.getScanner(scan).iterator()
//      println(scanner.size) // had to import something for this; there's probably a better way to solve this challenge...?


      // Challenge 4
      // https://hbase.apache.org/2.1/apidocs/org/apache/hadoop/hbase/client/Delete.html
//      val myDeleteObject = new Delete(Bytes.toBytes("99"))
//      val resultDelete = table.delete(myDeleteObject)
//
//      // check that it was deleted; if so, you should get "null" returned, I think
//      val get = new Get(Bytes.toBytes("99"))
//      val resultGet = table.get(get)
//      val favoriteColor = Bytes.toString(resultGet.getValue(Bytes.toBytes("f1"), Bytes.toBytes("favorite_color")))
//      println(favoriteColor)


      // Challenge 5:
      // There is also an operation that returns multiple results in a single HBase "Get" operation.
      // Write a single HBase call that returns the email addresses for the following 5 users:
      // 9005729, 500600, 30059640, 6005263, 800182 (Hint: Look at the Javadoc for "Table")
      // https://hbase.apache.org/2.1/apidocs/org/apache/hadoop/hbase/client/Table.html
      // default Result[] 	get(List<Get> gets)
      // Extracts specified cells from the given rows, as a batch.

      // probably better / more succint way to write this code
      val get1 = new Get(Bytes.toBytes("9005729"))
      val get2 = new Get(Bytes.toBytes("500600"))
      val get3 = new Get(Bytes.toBytes("30059640"))
      val get4 = new Get(Bytes.toBytes("6005263"))
      val get5 = new Get(Bytes.toBytes("800182"))
      val gets: util.List[Get] = new util.ArrayList[Get]() // confused about util.List vs util.ArrayList

      gets.add(get1)
      gets.add(get2)
      gets.add(get3)
      gets.add(get4)
      gets.add(get5)

      val result = table.get(gets)

      // make sure I got the results
      val userEmail_9005729 = Bytes.toString(result(0).getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail")))
      println(userEmail_9005729)
      val userEmail_800182 = Bytes.toString(result(4).getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail")))
      println(userEmail_800182)


      //      logger.debug(result)
    } catch {
      case e: Exception => logger.error("Error in main", e)
    } finally {
      if (connection != null) connection.close()
    }
  }
}
