
import com.peak2peakmedia.model.GroceryItem;
import com.peak2peakmedia.model.GroceryList2;

import java.io.File;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by colinhill on 2/1/16.
 */
public class Main1 {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";
    static final ArrayList<String> listNames = new ArrayList<String>();


    //  Database credentials
    static final String USER = "root";
    static final String PASS = "teamtreehouse";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        getListNames();
        mainMenu(input);
    }

    public static void mainMenu(Scanner input) {
        boolean flag = true;
        do {
            System.out.println("Enter 1 - To Create a New List");
            System.out.println("Enter 2 - To Edit an existing List");
            System.out.println("Enter 3 - To Enter Items Menu");
            System.out.println("Enter 4 - To View all lists\n");
            System.out.println("Enter 0 - To Exit");
            int response = input.nextInt();
            switch (response) {
                case 1:
                    createNewList();
                    break;
                case 2:
//                    getListNames();
                    loadList();
                    break;
                case 3:
                    itemsMenu();
                    break;
                case 0:
                    flag = false;
                    break;
                case 4:
                    getListNames();
                    break;
                default:
                    System.out.println("Please enter a valid menu option");
                    break;
            }
        } while (flag);
    }

    public static void itemsMenu() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;

        do {
            System.out.println("Enter 1 - To view all Items");
            System.out.println("Enter 2 - To Create a new Item");
            System.out.println("Enter 3 - To Delete an Existing Item");
            System.out.println("Enter 4 - To Add an Item to your List");
            System.out.println("Enter 9 - To go back to the Main Menu");

            int response = input.nextInt();

            switch (response) {
                case 1:
                    viewAllItemsInDatabase();
                    break;
                case 2:
                    addItemToDatabase();
                    break;
                case 3:
                    viewAllItemsInDatabase();
                    System.out.println("Enter the ID of the ITEM you wish to Delete");
                    deleteItemFromDatabase(input.nextInt());
                    break;
                case 4:
                    flag = false;
                    break;
                case 9:
                    mainMenu(input);
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a valid menu option");
                    break;
            }
        } while (flag);
    }

    public static void createNewList() {
        Scanner input = new Scanner(System.in);

        GroceryList2 list = new GroceryList2();

        System.out.println("Enter the name for the List: ");
        list.setName(input.nextLine());

        System.out.printf("Do you want to add items to \" %s \" now? y/n \n", list.getName());

        String response = input.next();

        if (response.contains("y")) {
            populateList(list);
        }
    }

    private static void populateList(GroceryList2 list) {
        Scanner input = new Scanner(System.in);
        GroceryItem item;
        boolean flag = true;


        System.out.println("Would you like to add an item? y/n");
        do {
            if (input.next().contains("y")) {
                itemsMenu();
                System.out.println("Enter the ID of the item you wish to add: ");
                item = selectItemFromDatabase(input.nextInt());
                System.out.printf("How many %s(s) would you like to add?", item.getItemName());
                item.setQty(input.nextInt());
                list.addItem(item);
            } else {
                System.out.println("List before Save \n" + list);
                saveList(list);
                flag = false;
            }
            System.out.println("Would you like to add another item? y/n");
        } while (flag);
    }


    // Begin ITEMS
    public static GroceryItem createNewItem() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the Item Name: ");
        String name = input.next();

        System.out.println("Enter the Price of the Item: ");
        double price = input.nextDouble();

        return new GroceryItem(/*ID,*/ name, 1, price, 10);
    }

    public static void addItemToDatabase() {
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

            System.out.println("Inserting records into the table...");
            stmt = connection.createStatement();

            GroceryItem item = createNewItem();

            String sql = "INSERT INTO store " +
                    "VALUES (id, '" + item.getItemName() + "'," + item.getUnitTotalPrice() + item.getStock()+ ")";
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

            String sql = "SELECT id, name, price FROM store";
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


            for (GroceryItem item : list.itemOrders) {

                String sql = "INSERT INTO storelists " +
                        "VALUES (id, '" + list.getName() + "', '"+ /* + item.getID() +*/ "', '" + item.getQty() + "')";
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

    public static void getListNames() {
        Connection conn = null;
        Statement stmt = null;

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
        }
    }

    //Format this to look nice for User
    public static void printList(GroceryList2 list) {
        try {

            PrintStream output = new PrintStream(new File(list.getName() + ".txt"));
            System.out.println("Save List \n" + list);

            output.println(list.getName() + "\n\n");

            for (GroceryItem item : list.itemOrders) {
                output.printf("%s %d \n", item.getItemName(), item.getQty());
            }
            output.printf("\n\nTotal Cost: \t\t\t\t\t $%.2f\n", list.getTotalCost());
        } catch (Exception e) {
            System.out.println("File Not Found!");
        }
    }
}
