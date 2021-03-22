package com.unitedcoder.regression.databasetest;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    //create a method to connect database
    public static Connection connectToDataBaseServer(String dburl,String dbPort,
                                                     String dbUserName,String dbPassword,
                                                     String defaultDatabase,ConnectionType connectionType)
    {
        Connection connection=null;
        String JTDS_Driver="net.sourceforge.jtds.jdbc.Driver";//sql
        String MYSQL_Driver="com.mysql.cj.jdbc.Driver";//my sql
        switch (connectionType){
            case MSSQLSERVER:
                try {
                    Class.forName(JTDS_Driver);//load the drive in to memory
                } catch (ClassNotFoundException e) {
                    new RuntimeException("Please check driver information");
                }
                String connectionUrl="jdbc:jtds:sqlserver://" + dburl + ":"
                        + ";databaseName=" + defaultDatabase;
                try {
                    connection= DriverManager.getConnection(connectionUrl,dbUserName,dbPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case MYSQLServer:
                try {
                    Class.forName(MYSQL_Driver).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                String mySqlConnection="jdbc:mysql://"+dburl+":"+dbPort+"/"+defaultDatabase;
                try {
                    connection=DriverManager.getConnection(mySqlConnection,dbUserName,dbPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("You need to specify data base connection type(MSSQL or MySQL)");
        }
        //verify if the connection is established or not
        try {
            if(!connection.isClosed()){
                System.out.println("Data base connection is established");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeDataBaseConnection(Connection connection){
        try {
            if(connection.isClosed()){
                System.out.println("Data base connection has been closed");
            }else{
                connection.close();
                System.out.println("connection is closed!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}





