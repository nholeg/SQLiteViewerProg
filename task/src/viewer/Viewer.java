package viewer;

import viewer.SQLiteViewer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Viewer {


    private static final String firstDatabaseFileName = "firstDatabase.db";
    private static final String secondDatabaseFileName = "secondDatabase.db";

    static void initDatabase() throws SQLException {

        deleteDatabaseFiles();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + firstDatabaseFileName);
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS contacts (\n" +
                "\tcontact_id INTEGER PRIMARY KEY,\n" +
                "\tfirst_name TEXT NOT NULL,\n" +
                "\tlast_name TEXT NOT NULL,\n" +
                "\temail TEXT NOT NULL UNIQUE,\n" +
                "\tphone TEXT NOT NULL UNIQUE\n" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS groups (\n" +
                "   group_id INTEGER PRIMARY KEY,\n" +
                "   name TEXT NOT NULL\n" +
                ");");

        statement.execute("DELETE FROM contacts");
        statement.execute("INSERT INTO contacts VALUES(1, 'Sharmin', 'Pittman', 'sharmin@gmail.com', '202-555-0140')");
        statement.execute("INSERT INTO contacts VALUES(2, 'Fred', 'Hood', 'fred@gmail.com', '202-555-0175')");
        statement.execute("INSERT INTO contacts VALUES(3, 'Emeli', 'Ortega', 'emeli@gmail.com', '202-555-0138')");

        connection.close();

        connection = DriverManager.getConnection("jdbc:sqlite:" + secondDatabaseFileName);
        statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS projects (\n" +
                "\tid integer PRIMARY KEY,\n" +
                "\tname text NOT NULL,\n" +
                "\tbegin_date text,\n" +
                "\tend_date text\n" +
                ");");
        connection.close();
    }


    public static void deleteDatabaseFiles() {
        File firstFile = new File(firstDatabaseFileName);
        if (firstFile.exists()) {
            boolean ignored = firstFile.delete();
        }

        File secondFile = new File(secondDatabaseFileName);
        if (secondFile.exists()) {
            boolean ignored = secondFile.delete();
        }
    }

}