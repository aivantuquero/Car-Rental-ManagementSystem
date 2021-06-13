# Car-Rental-ManagementSystem
A Car Rental Management System built in Eclipse IDE using JAVA Swing and MYSQL

> This project was built using JavaSE 15 and has a compiler compliance level of JavaSE 11.

## How to install

1. Import dumpFile.sql located at /Car Rental System/database to MYSQL database using `mysql -u user -p yourDbNameHere < dumpFile.sql` and Change the database connection parameters from the source code based on your driver and server information. If you need more in-depth tutorial about importing the database kindly [watch this video](https://youtu.be/BsKXzm6qbcM).

```
//the rentcar_db is the database name
//
//

public void Connect()
{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rentcar_db", "root","password");
            }
}            
``` 

