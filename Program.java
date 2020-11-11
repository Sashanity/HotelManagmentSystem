/**
 * Infinity Hotel terminal interface
 * For CS157A, Dr. Kim - Group: Infinity Hotel
 * Based partly on code shared by Dr. Kim for JDBC chapter
 */

import java.sql.*;
import java.util.Scanner;

public class Program {
    private static final String DB_SCHEMA_NAME = "infinity_hotel"; // check & run init file: DCL/inifinity_hotel.sql
    private static final String DB_USERNAME = "hoteluser";
    private static final String DB_PASSWORD = "p123456d";

    public static void main(String[] args ){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_SCHEMA_NAME + "?serverTimezone=UTC",DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            // If DB connection is okay start the program
            DbWrapper dbWrapper = new DbWrapper(connection);
            Model model = new Model(dbWrapper);
            TerminalConsole terminalConsole = new TerminalConsole(new Scanner(System.in),model);
            terminalConsole.startConsole();
        } else {
            System.out.println("Failure: Connection to DB failed. Exiting.");
        }
    }
}
