package viewer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class ApplicationRunner {
    public static void main(String[] args) {
        /*Viewer viewer = new Viewer();
        try {
            viewer.initDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        new SQLiteViewer();

    }

}
