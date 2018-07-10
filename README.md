# Mars-Rover
This is a solution to the problem described in (https://github.com/abdulg/Mars-Rover) and it was built in [Scala](https://www.scala-lang.org/), using [SBT](https://www.scala-sbt.org/) for dependencies management.

## Requirements
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 8
* [Scala](https://www.scala-lang.org/download/) ~2.12.6
* [SBT](https://www.scala-sbt.org/download.html) ~1.1.6


## Running the application

To run the application, do the following:
* Clone this repository;
* Move into the repository folder;
* Run the command: `sbt run` for executing the application **or**;
* Run the command: `sbt test` for executing the tests **or**;
* Run the command: `sbt assembly`for generating an executable jar file.
    * To execute the jar file, run: `java -jar [path/to/file]`