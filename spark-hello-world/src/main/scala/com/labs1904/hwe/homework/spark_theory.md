# Overview

Similar to the work you did for Kafka, this is your crash course into Spark through different questions. In this homework, your
challenge is to write answers that make sense to you, and most importantly, **in your own words!**
Two of the best skills you can get from this class are to find answers to your questions using any means possible, and to
reword confusing descriptions in a way that makes sense to you. 

### Tips
* You don't need to write novels, just write enough that you feel like you've fully answered the question
* Use the helpful resources that we post next to the questions as a starting point, but carve your own path by searching on Google, YouTube, books in a library, etc to get answers!
* We're here if you need us. Reach out anytime if you want to ask deeper questions about a topic 
* This file is a markdown file. We don't expect you to do any fancy markdown, but you're welcome to format however you like
* Spark By Examples is a great resources to start with - [Spark By Examples](https://sparkbyexamples.com/)

### Your Challenge
1. Create a new branch for your answers 
2. Complete all of the questions below by writing your answers under each question
3. Commit your changes and push to your forked repository

## Questions
#### What problem does Spark help solve? Use a specific use case in your answer 
* Helpful resource: [Apache Spark Use Cases](https://www.toptal.com/spark/introduction-to-apache-spark)
* [Overivew of Apache Spark](https://www.youtube.com/watch?v=znBa13Earms&t=42s)
* Answer: The problem it helps solve is processing lots of data quickly, efficiently, and [relatively] easily. 
* Use case: The one mentioned most in-depth in the reading is warning people about earthquakes. 
* There is streaming data from Twitter (lots of it). Using Spark (specifically a module called Spark Streaming), you can get that data and quickly filter it to tweets talking about earthquakes.
* Then you can use another aspect (module) of Spark, MLib, to predict whether the tweet is talking about a currently-happening earthquake or not.
* There could be lots of tweets in a short amount of time with a high likelihood of talking about a currently-happening earthquake in a given location.
* If so, then using SparkSQL (another module of Spark), you could query a table of people who would like to receive warnings about earthquakes,
* and send them a warning email. All of this would happen very quickly because Spark can process data very quickly, and it's useful in cases like this where reacting quickly can save lives. 

#### What is Apache Spark?
* Helpful resource: [Spark Overview](https://www.youtube.com/watch?v=ymtq8yjmD9I) 
* Answer: 
* An engine (a type of software) written to quickly process lots of data (in parallel / distributed).
* It also includes multiple modules (libraries?) which aid with that - for streaming data, machine learning, querying with SQL, interacting with graphs, etc. 
* There are various languages (APIs) that work with it - Python, Scala, Java, SQL, etc. 
* It can do batch processing and stream processing.
* Module = component?
* Module = group of libraries?
* Spark Core, Spark SQL, MLib, GraphX, Spark Streaming - modules of Spark
* Spark does not have its own storage - it needs something else for storage. Like HDFS or a database. 
* The video said "resilient" meant that RDDs only exist for a short period of time (aka they are temporary).
  * This may be true, but that's not what the "resilient" refers to, is it?
* DAG: (directed acyclic graph?)
  * series of steps which will get executed at a later stage
  * first step in a DAG: val x = blah. This creates RDD 1 in the DAG.
  * second step in a DAG: val y = x.map(blah). This creates RDD 2 in the DAG.
  * When you create RDD 2, it becomes a child of RDD 1 in the DAG. 
  * When you execute an action like y.count(blah), it triggers the execution of the DAG from the beginning - aka, data gets loading into RDD 1, then RDD 2, etc.
  * If you execute the same action again, it will again trigger the execution of the DAG from the beginning.
    * Is there a way to prevent this, like by caching the results from the first execution? I think so; look into this. 

#### What is distributed data processing? How does it relate to Apache Spark?  
[Apache Spark for Beginners](https://medium.com/@aristo_alex/apache-spark-for-beginners-d3b3791e259e)
* Answer: 

#### On the physical side of a spark cluster, you have a driver and executors. Define each and give an example of how they work together to process data
* Answer: 

#### Define each and explain how they are different from each other 
* RDD (Resilient Distributed Dataset)
  * Answer: immutable (can't change it), fault tolerant (can recover from errors / crashes), distributed (spread over multiple servers - this is what makes it fault tolerant?) collection of objects (data) that can be operated on in parallel.
  * An empty, in-memory (of your nodes - multiple of them) RDD is created right when a val is declared, but it is not populated with data until an action is called which needs it (lazy evaluation).
* DataFrame
  * Answer:
* DataSet
  * Answer:

#### What is a spark transformation?
[Spark By Examples-Transformations](https://sparkbyexamples.com/apache-spark-rdd/spark-rdd-transformations/)
* Answer: 
* operations performed on an RDD that return a new RDD. 
* Examples: map, filter, join, union, etc.
* The RDD becomes part of a DAG, which is series of steps to be executed later. See above. 

#### What is a spark action? How do actions differ from transformations? 
* Answer: 
* Operations that return a value (just one value...?) after performing a computation on an RDD. 
* Examples: reduce, count, first, etc.
* Different than a transformation because actions force transformations to actually happen; transformations are lazy so they don't actually happen until a value (from calling an action) is needed.
* An action actually triggers the execution of a DAG (see above). (Just one DAG, or could it trigger the execution of multiple at once?)


#### What is a partition in spark? Why would you ever need to repartition? 
[Spark Partitioning](https://sparkbyexamples.com/spark/spark-repartition-vs-coalesce/)

#### What was the most fascinating aspect of Spark to you while learning? 
* DAGs, since I know a bit about these but wasn't expecting them to come up within Spark.
* If we didn't use Spark, what could we use instead? What if we didn't "use" anything - what would that look like?
* Contra Spark
  * What are the downsides of Spark?
  * It almost seems like when people are explaining Spark, it's "so obvious" that it's much better because of X, Y, Z. 
  * If that's true, why didn't someone come up with X, Y, Z earlier on? 
    * Were there limitations at that time which made X, Y, Z impractical, and then something changed which made them practical?
  * What are the drawbacks of X, Y, Z (even today)? Like which kinds of use cases would Spark not be good for?
