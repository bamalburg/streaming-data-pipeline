# Overview

By now you've seen some different Big Data frameworks such as Kafka and Spark. Now we'll be focusing in on HBase. In this homework, your
challenge is to write answers that make sense to you, and most importantly, **in your own words!**
Two of the best skills you can get from this class are to find answers to your questions using any means possible, and to
reword confusing descriptions in a way that makes sense to you. 

### Tips
* You don't need to write novels, just write enough that you feel like you've fully answered the question
* Use the helpful resources that we post next to the questions as a starting point, but carve your own path by searching on Google, YouTube, books in a library, etc to get answers!
* We're here if you need us. Reach out anytime if you want to ask deeper questions about a topic 
* This file is a markdown file. We don't expect you to do any fancy markdown, but you're welcome to format however you like


### Your Challenge
1. Create a new branch for your answers 
2. Complete all of the questions below by writing your answers under each question
3. Commit your changes and push to your forked repository

## Questions
#### What is a NoSQL database? 

Answer:
- it's hard to define other than "not a relational database", I think
  - however, some NoSQL databases do have the ability to be queried with a SQL-esque language and have some similarities to relational databases
- there are multiple kinds - key value store (aka table?), graph, document store, columnar aka column store, wide column store (there may be some overlap in these terms...?)
- it's sometimes thought of as more flexible than a relational database (for example, because it doesn't [usually] enforce a schema when writing to a database)
- it's usually more denormalized than a relational database - aka, some data is repeated and stored in multiple places to allow for quicker reads, but this often leads to less consistency, slower writes, bigger data size
  - less consistency:
    - duplicated data might not get updated at exactly the same time in different places, so when you query it, you might not get the most recently updated data - aka the data could be "stale" (related: ACID, transactions)
- easier to have a distributed NoSQL database than a distributed relational database, I think, because data is stored multiple times in multiple places, and data that you would normally want to query together is often put into the same partition or document, so there isn't as much need to query lots of tables for a given query 
  - (which, if the tables are only stored once, then they would sometimes be far away from where the rest of the data is that you want to include in your query, which makes things slower to join together and return. All the data you normally want to query together is stored in the same place, making it easy/quick to return.)
- CAP theorem 
  - "any distributed data store can only provide two of the following three guarantees: consistency, availability, partition tolerance
  - When a network partition failure happens, it must be decided whether to:
    - cancel the operation and thus decrease the availability but ensure consistency or to
    - proceed with the operation and thus provide availability but risk inconsistency." (wiki)
  - usually NoSQL databases sacrifice consistency in these situations, like I mentioned above. I guess the idea is that if you want fast reads, it's fine if the data is a bit "stale"
  - I guess if there aren't any network partitions (aka it's not distributed, I think) then you could have both consistency and availability

#### In your own words, what is Apache HBase? 

Answer: 
- a type of NoSQL database called a "wide-column store" (also, open-source)
- wide-column store: 
  - has rows, columns, tables - but the names and format of columns can vary from row to row in the same table (unlike in a relational database)
  - examples: Google Bigtable, Apache HBase, Azure Cosmos DB
  - sometimes (usually, I think?) supports column families
  - fault tolerant
  - no schema upon write
  - differences with relational:
    - relational database: "if one row needs an additional column, that column must be added to the entire table, with null or default values provided for all the other rows."
    - wide-column store: "A column [for a given row, I think] is only written if there’s a data element for it."
    - relational database: "If you need to query that RDBMS table for a value that isn’t indexed, the table scan to locate those values will be very slow."
    - wide-column store: "Each data element can be referenced by the row key, but querying for a value is optimized like querying an index in a RDBMS, rather than a slow table scan."
  - "highly scalable because the data is stored in individual columns which can be sharded or partitioned across multiple servers."
    - Does this means (in cosmos DB terms) that the column name is the partition key's value(s)?
  - Use cases
    - "use cases that require a large dataset that can be distributed across multiple database nodes, especially when the columns are not always the same for every row.
      - Log data
      - IoT (Internet of Things) sensor data
      - Time-series data, such as temperature monitoring or financial trading data
      - Attribute-based data, such as user preferences or equipment features
      - Real-time analytics"
    - "It is designed for data lake use cases and is not typically used for web and mobile applications"
      - interesting - because one source said Cosmos DB is a type of wide-column store, and it is used for web / mobile applications I believe...?  


#### What are some strengths and limitations of HBase? 
* [HBase By Examples](https://sparkbyexamples.com/apache-hbase-tutorial/) 

Answer:
- benefits:
    - query speed (optimized for reads)
    - scalability
    - flexible data model
- downsides:
    - worse querying...flexibility / commands? (unless you use Hive on top of it?). Otherwise you normally basically just query by row key...?
    - can't join across tables
    - doesn't support transactions
    - requires lots of I/O, CPU, memory
    - not optimized for writes

#### Explain the following concepts: 
* Rowkey
* Column Qualifier
* Column Family


#### What are the differences between Get and Put commands in HBase? 
* [HBase commands](https://www.tutorialspoint.com/hbase/hbase_create_data.htm)


#### What is the HBase Scan command for? 
* [HBase Scan](https://www.tutorialspoint.com/hbase/hbase_scan.htm)

#### What was the most interesting aspect of HBase when went through all the questions? 