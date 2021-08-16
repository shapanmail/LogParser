### Log Parser ###

### Problem Description ###

The task is to parse a log file containing HTTP requests and to report on its contents. For a given log file we want to
know:

- The number of unique IP addresses
- The top 3 most visited URLs
- The top 3 most active IP addresses

Example Data 177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] "GET /intranet-analytics/ HTTP/1.1" 200 3574 A log file with
test data is included with this assignment.

### How to run ###

* Build, test and Run

```bash
    cd ${PROJECT_HOME}
    ./gradlew clean test --info
    ./gradlew run --args=src/main/resources/log.txt  # pass input file as arguments.
``` 

### Design Decision ###

- Used regular expression to parse the logs properties and created POJO from these properties.
- Created inverted index for IP and URLs, which provides following benefits.
    * Does not decrease performance as the dataset grow.
    * Easier to add new search feature(search by a specific IP or URL, do other aggregation) without changing the
      implementation. 
  