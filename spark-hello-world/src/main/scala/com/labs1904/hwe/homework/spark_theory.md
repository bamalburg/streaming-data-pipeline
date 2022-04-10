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
* It also includes multiple modules (libraries) which aid with that - for streaming data, machine learning, interacting with graphs, etc. 
* There are various languages (APIs) that work with it - Python, Scala, Java, SQL, etc. 

#### What is distributed data processing? How does it relate to Apache Spark?  
[Apache Spark for Beginners](https://medium.com/@aristo_alex/apache-spark-for-beginners-d3b3791e259e)
* Answer: 

#### On the physical side of a spark cluster, you have a driver and executors. Define each and give an example of how they work together to process data
* Answer: 

#### Define each and explain how they are different from each other 
* RDD (Resilient Distributed Dataset)
  * Answer: immutable (can't change it), fault tolerant (can recover from errors / crashes), distributed (spread over multiple servers - this is what makes it fault tolerant?) collection of objects (data) that can be operated on in parallel
* DataFrame
  * Answer:
* DataSet
  * Answer:

#### What is a spark transformation?
[Spark By Examples-Transformations](https://sparkbyexamples.com/apache-spark-rdd/spark-rdd-transformations/)
* Answer: operations performed on an RDD that return a new RDD. Examples: map, filter, join, union, etc.

#### What is a spark action? How do actions differ from transformations? 
* Answer: 
* Operations that return a value (just one value...?) after performing a computation on an RDD. Examples: reduce, count, first, etc.
* Different than a transformation because actions force transformations to actually happen; transformations are lazy so they don't actually happen until a value (from calling an action) is needed.


#### What is a partition in spark? Why would you ever need to repartition? 
[Spark Partitioning](https://sparkbyexamples.com/spark/spark-repartition-vs-coalesce/)

#### What was the most fascinating aspect of Spark to you while learning? 
