package com.andy.expensetracker.Database;
import com.andy.expensetracker.ConfigLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.sql.*;

public class DBConnection {

    public static final int MAX_ATTEMPT=3;
    public static final int RETRY_DELAY_MS=2000;
    public static Connection connection;

    private static final String server = ConfigLoader.getProperty("DB_Server");
    private static final String user= ConfigLoader.getProperty("DB_User");
    private static final String password = ConfigLoader.getProperty("DB_Password");
    private static final String url = "jdbc:sqlserver://"+server+";databaseName=ExpenseTracker;user="+user+";password="+password+";trustServerCertificate=true;";

    public DBConnection(){

    }

    public Connection getConnection() {
        if (!isDbConnected()) {

            int attempt=0;
            while(attempt<MAX_ATTEMPT){
                try {
                    attempt++;
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                    connection=DriverManager.getConnection(url,user,password);
                    connection=DriverManager.getConnection(url);
                    System.out.println("Database connection successful.");
                    return connection;

                } catch (Exception e) {
                    System.err.println("Connection attempt " + attempt + " failed: " + e.getMessage());
                    if(attempt<MAX_ATTEMPT){
                        try{
                            Thread.sleep(RETRY_DELAY_MS);
                        }catch (InterruptedException ex){
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }

            Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
            alert.setContentText("Cannot connect to the server after 3 attempts.");
            alert.show();

        }

        return connection;
    }



    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }



}
