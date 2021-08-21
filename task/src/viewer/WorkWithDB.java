package viewer;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WorkWithDB {
    static String fileName = "";
    static String url = "";
    static SQLiteDataSource dataSource = new SQLiteDataSource();
    static int columnCount;
    static List<String> columnName = new ArrayList<>();

    public static int getColumnCount() {
        return columnCount;
    }


    public WorkWithDB(String fileName) {
        this.fileName = fileName;
        url = "jdbc:sqlite:" + fileName;
        dataSource.setUrl(url);
    }
    public WorkWithDB() {
        this.fileName = "";
        url = "jdbc:sqlite:" + "";
        dataSource.setUrl(url);
    }

    static List<String> getTables() throws Exception {
        String get = "SELECT name FROM sqlite_master WHERE type ='table'" +
                " AND name NOT LIKE 'sqlite_%';";
        List<String> data = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (PreparedStatement statement = con.prepareStatement(get)) {
                // Statement execution
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        data.add(resultSet.getString("name"));
                    }
                } else {
                    throw new Exception("File doesn't exist!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    static List<String> getData(String select) {
        List<String> data = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (PreparedStatement statement = con.prepareStatement(select)) {
                // Statement execution
                ResultSet resultSet = statement.executeQuery();
                //Retrieving the ResultSetMetaData object
                ResultSetMetaData rsmd = resultSet.getMetaData();
                //getting the column count
                columnCount = rsmd.getColumnCount();
                //getting the columns names
                for (int i = 1; i <= columnCount; i++ ) {
                    columnName.add(rsmd.getColumnName(i));
                }
                // getting result set by row
                if (resultSet.isBeforeFirst()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            stringBuilder.append(resultSet.getString(i) + " ");
                        }
                        //one bucket is one row now
                        data.add(stringBuilder.toString());
                        stringBuilder.setLength(0);

                    }
                } else {
                    System.out.println("No data found");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
