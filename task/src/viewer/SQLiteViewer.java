package viewer;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SQLiteViewer extends JFrame {
    JButton button = new JButton("Open");
    JTextField textField = new JTextField();
    JComboBox<String> comboBox = new JComboBox<>();
    JTextArea textArea = new JTextArea();
    JButton execute = new JButton("Execute");
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    JPanel panel = new JPanel();

    public SQLiteViewer() {
        super("SQLite Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 900);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);


        panel.setLayout(null);
        add(panel, BorderLayout.CENTER);

        textField.setName("FileNameTextField");
        textField.setBounds(10, 10, 600, 20);
        panel.add(textField);

        button.setName("OpenFileButton");
        button.setBounds(600, 10, 80, 20);
        panel.add(button);

        comboBox.setName("TablesComboBox");
        comboBox.setBounds(10,40,670, 20);
        panel.add(comboBox);

        textArea.setName("QueryTextArea");
        textArea.setBounds(10,70,580,300);
        textArea.setEnabled(false);
        panel.add(textArea);

        execute.setName("ExecuteQueryButton");
        execute.setBounds(600, 70, 80, 20);
        execute.setEnabled(false);
        panel.add(execute);

        table.setName("Table");

        scrollPane.createVerticalScrollBar();

        scrollPane.setBounds(10,380,670,450);
        panel.add(scrollPane);


        Listener l = new Listener(this);

        button.addActionListener(l);
        comboBox.addActionListener(l);
        execute.addActionListener(l);

        setVisible(true);
    }
    /*public void SQLiteViewerError() {
        JOptionPane pane = new JOptionPane();
        int n = JOptionPane.showOptionDialog(null, "File doesn't exist!", "Error", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (0 == n || -1 == n) {
            panel.setEnabled(true);
            panel.setVisible(true);
        }
    }*/

}
