package com.unitedcoder.regression.databasetest;




import java.sql.Connection;

    public class DataBaseConnectionTest {
        static String dbUrl="148.72.106.125";
        static String port="3306";
        static String username="testautomation";
        static String password="automation123!";
        static String defaultSchema="i4296639_cc1";
        public static void main(String[] args) {
            Connection connection=ConnectionManager.connectToDataBaseServer(dbUrl,port,username,
                    password,defaultSchema,ConnectionType.MYSQLServer);
            ConnectionManager.closeDataBaseConnection(connection);
        }
    }

