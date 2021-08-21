package viewer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Listener implements ActionListener {

    private final SQLiteViewer mView;
    public Listener(SQLiteViewer pView) {
        mView = pView;
    }
    private String db ;
    private WorkWithDB dblink = new WorkWithDB();

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mView.button) {
            db = mView.textField.getText();
            dblink = new WorkWithDB(db);
            List<String> data = null;

            try {
                data = dblink.getTables();
            } catch (Exception e) {
                mView.textArea.setEnabled(false);
                mView.execute.setEnabled(false);
                JOptionPane.showMessageDialog(new Frame(), "File doesn't exist!");
                //mView.SQLiteViewerError();
            }

            mView.comboBox.removeAllItems();
            data.stream().map(String::toString).forEach(e -> mView.comboBox.addItem(e));
            mView.textArea.setEnabled(true);
            mView.execute.setEnabled(true);
            mView.textArea.setText("SELECT * FROM " + mView.comboBox.getSelectedItem() + ";");
        } else if (actionEvent.getSource() == mView.comboBox) {
            mView.textArea.setText("SELECT * FROM " + mView.comboBox.getSelectedItem() + ";");
        } else if (actionEvent.getSource() == mView.execute) {
            String select = mView.textArea.getText();
            List<String> data = dblink.getData(select);
            List<List<String>> dataList= data.stream()
                    .map(row -> Arrays.stream(row.split(" "))
                            .collect(Collectors.toList()))
                            .collect(Collectors.toList());
            data.stream().forEach(System.out::println);

            DefaultTableModel model = (DefaultTableModel) mView.table.getModel();
            model.setColumnCount(dblink.getColumnCount());
            model.setColumnIdentifiers(dblink.columnName.toArray());
            for (List<String> row : dataList) {
                model.addRow(row.toArray());
            }
            mView.table.setModel(model);
        }
    }
}
