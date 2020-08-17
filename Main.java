package com.company;

//
//-->java.sql package includes the following pre-defined library to design Jdbc applications.
//
//        java.sql package includes the following predefined library:-
//        I-----interface C-------class
//
//1. Driver (I)
//        2. DriverManager (C)
//        3. Connection (I)
//        4. Statement (I)
//        5. PreparedStatement (I)
//        6. ResultSet (I)
//        7. ResultSetMetaData (I)
//        8. DatabaseMetaData (I)
//        9. Savepoint(i)
//

import jdk.jfr.StackTrace;

import java.sql.*;
import java.util.Scanner;


class jdbcCrud{
    public Connection con;
    private Statement st;
    private ResultSet rs;

    public jdbcCrud(String url,String userName,String passWord){
        try {
//            load and register the driver(loading the class from mysql-connector jar files)
//            The java.lang.Class.forName(String name, boolean initialize, ClassLoader loader)
//            method returns the Class object associated with the class or interface with the given string name, using the given class loader.
//            if no classloader mentioned then bootloader is used.
            Class.forName("com.mysql.jdbc.Driver");   //not required for mysql driver>4.0 since they internallly load and register

            //establish connection between java application and database
            //Connection ---> is an interface provided by jdbc and every database vendor implements(here mysql) it to provide the functionality mentioned in interface
            con = DriverManager.getConnection(url,userName,passWord);

            System.out.println("connected to database");

            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch(Throwable ex){
            ex.printStackTrace();
        }
    }

    public boolean createTable(String table){
        try{
            st.execute("create table " + table);
        }
        catch(Throwable ex){
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public void read(String tableName){
        try{
            rs=st.executeQuery("select * from " + tableName);
            ResultSetMetaData rmt=rs.getMetaData();
            int size=rmt.getColumnCount();

            for(int i=1;i<=size;i++){
                System.out.print(rmt.getColumnName(i) + "           ");
            }

            System.out.println("");

            while(rs.next()){
                for(int i=1;i<=size;i++){

                    String swt=rmt.getColumnTypeName(i);

                    switch(swt){
                        case "VARCHAR" :

                        case "CHAR" :
                            System.out.print(rs.getString(i));
                            break;

                        case "INT" :
                            System.out.print(rs.getInt(i));
                            break;

                        case "BOOLEAN" :
                            System.out.print(rs.getBoolean(i));
                            break;

                        case "DATE" :
                            System.out.print(rs.getDate(i));
                            break;

                        case "TIME" :
                            System.out.print(rs.getTime(i));
                            break;

                        default :
                            System.out.println(rs.getString(i));
                            break;
                    }

                    System.out.print("          ");
                }
                System.out.println("");

            }
        }
        catch(Throwable ex){
            ex.printStackTrace();
        }
    }

    //    The updater methods do not update the underlying database; instead the updateRow or insertRow methods are called to update the database.
    public boolean update(String query){
        try{
            st.executeUpdate(query);
        }
        catch (Throwable ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean delete(String query){
        try{
            st.executeUpdate(query);
        }
        catch (Throwable ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean close(){
        try{
            con.close();
        }
        catch(Throwable ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}

//Example to use
class Main{
    public static void main(String args[]){
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the database url  : ");
            String url=sc.nextLine();
//            String url="jdbc:mysql://localhost:3306/coursera_ICA";

            System.out.println("Enter the database username  : ");
            String userName=sc.nextLine();

            System.out.println("Enter the database password  : ");
            String passWord=sc.nextLine();

            jdbcCrud cr=new jdbcCrud(url,userName,passWord);

            cr.read("employee");

            cr.close();
    }
}

