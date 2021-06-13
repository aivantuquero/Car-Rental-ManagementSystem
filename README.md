# Car-Rental-ManagementSystem
A Car Rental Management System built in Eclipse IDE using JAVA Swing and MYSQL

> This project was built using JavaSE 15 and has a compiler compliance level of JavaSe 11

## How to install

1. Import dumpFile.sql located at /Car Rental System/database to MYSQL database and Change the database connection parameters from the source code based on your driver and server information.

```
{
public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rentcar_db", "root","password");
        }
}
``` 

