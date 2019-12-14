import java.io.PrintWriter;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class PurchaseServer implements Runnable {
    static String dbfile = "H:\\COMP3700_Project2\\Data\\Project2_Database.db";

    //public static void main(String[] args) {
    public void run() {
        int port = 3000;

        /*if (args.length > 0) {
            System.out.println("Running arguments: ");
            for (String arg : args)
                System.out.println(arg);
            port = Integer.parseInt(args[0]);
            dbfile = args[1];
        }*/

        try {
            ServerSocket server = new ServerSocket(port);

            System.out.println("Server is listening at port = " + port);

            while (true) {
                Socket pipe = server.accept();
                PrintWriter out = new PrintWriter(pipe.getOutputStream(), true);
                Scanner in = new Scanner(pipe.getInputStream());

                String command = in.nextLine();
                if (command.equals("GET")) {
                    String str = in.nextLine();
                    System.out.println("GET purchase with id = " + str);
                    int purchaseID = Integer.parseInt(str);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Purchases WHERE PurchaseID = " + purchaseID;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            out.println(rs.getInt("CustomerID"));
                            out.println(rs.getInt("ProductID"));
                            out.println(rs.getDouble("Price"));
                            out.println(rs.getDouble("Quantity"));
                            out.println(rs.getDouble("Cost"));
                            out.println(rs.getDouble("Tax"));
                            out.println(rs.getDouble("Total"));
                            out.println(rs.getString("Date"));
                        }
                        else
                            out.println("null");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();
                }

                if (command.equals("PUT")) {
                    String id = in.nextLine();
                    String customerID = in.nextLine();
                    String productID = in.nextLine();
                    String price = in.nextLine();
                    String quantity = in.nextLine();
                    String cost = in.nextLine();
                    String tax = in.nextLine();
                    String total = in.nextLine();
                    String date = in.nextLine();

                    System.out.println("PUT command with PurchaseID = " + id);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Purchases WHERE PurchaseID = " + id;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            rs.close();
                            stmt.execute("DELETE FROM Purchases WHERE PurchaseID = " + id);
                        }

                        sql = "INSERT INTO Purchases VALUES (" + id + "," + customerID + ","
                                + productID + "," + price + "," + quantity + "," + cost + ","
                                + tax + "," + total + ",\"" + date + "\")";
                        System.out.println("SQL for PUT: " + sql);
                        stmt.execute(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();


                } else {
                    out.println(0); // logout unsuccessful!
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    //}
    }

}