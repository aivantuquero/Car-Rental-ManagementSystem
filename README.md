# Car-Rental-ManagementSystem
A Car Rental Management System built in Eclipse IDE using JAVA Swing and MYSQL


> This project was built using JavaSE 15 and has a compiler compliance level of JavaSE 11.


## How to install

1. Clone the project using `https://github.com/aivantuquero/Car-Rental-ManagementSystem.git`.

2. Import `dumpFile.sql` located at /Car Rental System/database to MYSQL database using `mysql -u user -p yourDbNameHere < dumpFile.sql` and Change the `Connect()` methods from the source code based on your driver and server information. If you need more in-depth tutorial about importing the database kindly [watch this video](https://youtu.be/BsKXzm6qbcM).

        ```
        public void Connect()
        {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // kindly change "/localhost/rentcar_db", "root" and "password" according to your driver and mysql server information.
                    con = DriverManager.getConnection("jdbc:mysql://localhost/rentcar_db", "root","password");
                    }
        }            
        ``` 
>The connect method is located at `CarsPanel.java`, `CustomerPanel.java`, and `RentPanel.java`. Hence, you have to configure the `Connect()` method in all three of them.

3. Import the `mysql-connector-java-8.0.25.jar` and `rs2xml` located at the folder called `dependencies` on Java Build Path as Libraries in Classpath.
        ![image](https://user-images.githubusercontent.com/54162088/121796911-49593d80-cc4f-11eb-8eb5-a22ebd9117f7.png)
        >Your build path should now look like this.

4. Done



## How to use

1. Run login.java and enter `admin` as username and password.
        ![image](https://user-images.githubusercontent.com/54162088/121797149-08fabf00-cc51-11eb-85cb-17bf9a5fb65f.png)
>You can configure the username and password in the source code.

2. After that, you will be greeted with a dashboard.
     
        
### The dashboard

![image](https://user-images.githubusercontent.com/54162088/121797234-8e7e6f00-cc51-11eb-9f26-65e7bd50e325.png)

...>The dashboard features three buttons namely, Manage Customers, Manage Cars, Rent a car.


