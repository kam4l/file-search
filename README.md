README 
======

Overview
--------

* Rest API to search recursively through directories of files for list of words provided 
by the user.
* The base path can be configured by the user by modifying _searchPath_ value in the _filesearch.yml_ file 

Dependencies
------------

* http://openjdk.java.net/[Java 8]

Running
-------

 To clean and run all tests, use `gradle clean test`
 
 To run, use `gradle run`
 
 Sample query
 * <http://localhost:8787/search-files?word=test>
 
Swagger
-------

 * <http://localhost:8787/swagger>