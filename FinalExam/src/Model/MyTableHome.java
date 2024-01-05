package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableHome extends AbstractTableModel {

    private Vector<Vector<Object>> data;
    private Vector<String> columnNames;

    public MyTableHome(ResultSet rs) {
        initData(rs);
    }

    public void initData(ResultSet rs) {
        data = new Vector<>();
        columnNames = new Vector<>();

        try {
            java.sql.ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Define the columns you want to display
            String[] desiredColumns = {"GameName", "StudioName", "Genre", "ReleaseYear", "Price"};

            // Get column names
            for (String desiredColumn : desiredColumns) {
                columnNames.add(desiredColumn);
            }

            // Get data
            while (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                for (String desiredColumn : desiredColumns) {
                	rowData.add(rs.getObject(desiredColumn));
                }
                data.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
}