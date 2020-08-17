# jdbc_wrapper

This jdbc wrapper is made to make crud operations on any of your relational database easy from your java application .

Note: Make sure the driver for the particular database(mysql-connector,oracle-connector,etc) is installed and classpath is set.

Advantages of using this wrapper:

-Switch between database easily with minimal change in code.

-No need to worry about loading and registering the driver for the user.

-Abstracting the jdbc steps to read,create,update etc and maintain the datastructures. 




Usage:

I.For connecting to database:

  1.create object of jdbcCrud class by providing the database url,username and password of your database for connecting to 
  your database


II.create/update/delete

  1. pass the sql query to create(String),update(String),delete(String) methods of jdbcCrud object.
  
  
III.read

  1.pass the table name to read() and print the table records.
  

