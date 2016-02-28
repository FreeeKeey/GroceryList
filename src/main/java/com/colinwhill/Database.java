package com.colinwhill;

import com.colinwhill.model.GroceryItem;
import com.colinwhill.model.GroceryList2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by colinhill on 2/28/16.
 */
public class Database {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";


    //  Database credentials
    static final String USER = "root";
    static final String PASS = "teamtreehouse";

//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//
//        ObservableList<GroceryItem> items = FXCollections.observableArrayList();
//
//        items.add(new GroceryItem("Tomatoes", 3, 1.99, 27));
//        items.add(new GroceryItem("Pineapple", 2, 4.99, 12));
//        items.add(new GroceryItem("Apples", 4, 2.99, 4));
//
////        GroceryList2 list = new GroceryList2("Listy", items,1);
//
////        Supermarket market = new Supermarket(list);
////
////        System.out.println(list);
////
////        list.addItem(new GroceryItem("Peanuts", 2, 6.99, 2));
////
////        System.out.println(list);
//
//
//        getListNames();
//        mainMenu(input);
//    }



    public void addItemToDatabase(GroceryItem item) {
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

            System.out.println("Inserting records into the table...");
            stmt = connection.createStatement();

            String sql = "INSERT INTO store " +
                    "VALUES (id, '" + item.getItemName() + "'," + item.getUnitTotalPrice() + item.getStock() + ")";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public static void viewAllItemsInDatabase() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM store";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                double price = rs.getFloat("price");
                String name = rs.getString("name");
                int stock = rs.getInt("remaining");

                //Display values
                System.out.printf("ID: %d \t Item: %s Price: %.2f Remaining: %d\n", id, name, price, stock);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void deleteItemFromDatabase(int id) {
        Connection conn = null;
        Statement stmt = null;
        GroceryItem item = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("You Deleted ID " + id + "...");
            stmt = conn.createStatement();

            String sql = "DELETE FROM store WHERE id=" + id;
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static GroceryItem selectItemFromDatabase(int id) {
        Connection conn = null;
        Statement stmt = null;
        GroceryItem item = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM store WHERE id=" + id;
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                id = rs.getInt("id");
                double price = rs.getFloat("price");
                String name = rs.getString("name");
                int stock = rs.getInt("remaining");

                //Display values
                //System.out.printf("ID: %d \t Item: %s Price: %.2f \n \n", id, name, price);

                item = new GroceryItem(/*ID,*/ name, 1, price, stock);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return item;
    }
    // END ITEMS

    public static void saveList(GroceryList2 list) {
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

            System.out.println("Inserting records into the table...");
            stmt = connection.createStatement();


            for (GroceryItem item : list.currentGroceryList) {

                String sql = "INSERT INTO storelists " +
                        "VALUES (id, '" + list.getName() + "', '" + /* + item.getID() +*/ "', '" + item.getQty() + "')";
                stmt.executeUpdate(sql);
            }
            System.out.println("Inserted records into the table...");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public static void loadList() {
        Connection conn = null;
        Statement stmt = null;
        String name = "";

        System.out.println("Enter the Name of the List you wish to Load: ");

        Scanner input = new Scanner(System.in);
        name = input.nextLine();
        GroceryList2 list = new GroceryList2();
        list.setName(name);

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM storelists WHERE name=\"" + name + "\"";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                name = rs.getString("name");
                int itemID = rs.getInt("itemID");
                int itemQty = rs.getInt("itemQty");

                //Add items to the List
                GroceryItem item = selectItemFromDatabase(itemID);
                item.setQty(itemQty);
                list.addItem(item);

            }

            // Display List once all Items have been added
            System.out.println(list);

            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public List<String> getListNames() {
        Connection conn = null;
        Statement stmt = null;
        List<String> listNames = new ArrayList<>();

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT DISTINCT name FROM storelists";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            System.out.println("You have the Following Lists:");
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("name");

                //Display values
                System.out.println(name);
                listNames.add(name);
            }
            System.out.println();
            rs.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
        return listNames;
    }

    //Format this to look nice for User
    public static void printList(GroceryList2 list) {
        try {

            PrintStream output = new PrintStream(new File(list.getName() + ".txt"));
            System.out.println("Save List \n" + list);

            output.println(list.getName() + "\n\n");

            for (GroceryItem item : list.currentGroceryList) {
                output.printf("%s %d \n", item.getItemName(), item.getQty());
            }
            output.printf("\n\nTotal Cost: \t\t\t\t\t $%.2f\n", list.getTotalCost());
        } catch (Exception e) {
            System.out.println("File Not Found!");
        }
    }


}

