package connectionToDatabase;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableview {
	JTable SQLTable = null;

	// create JTable view based on a generic SQL query
	public JTableview(String SQLquery) {
		SQLTable = genSQLTable(SQLquery);
	}

	public JTable getSQLTable() {
		return SQLTable;
	}

	// based on the result set of the SQL query a JTable is created
	private JTable genSQLTable(String SQLquery) {
		int columnCount = 0;
		int cnt = 1;
		// initialize local JTable view
		JTable tableview = new JTable();
		tableview.enableInputMethods(false);
		tableview.setDragEnabled(false);
		tableview.setColumnSelectionAllowed(false);
		// selection of exact one field value
		tableview.setRowSelectionAllowed(true);
		tableview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// create model and initialize model with reference to JTable view
		DefaultTableModel model = (DefaultTableModel) tableview.getModel();
		try { // initialize DB connection
			DB_connection con = DB_connection.getDbConnection();
			// use generic SQL query to get the result set
			ResultSet rs = con.executequery_rs(SQLquery);
			if (rs != null) { // fill model: identify column size of the result
								// set
				ResultSetMetaData rsmd = rs.getMetaData();
				columnCount = rsmd.getColumnCount();
				// add column labels to the model
				for (int column = 1; column <= columnCount; column++) {
					model.addColumn(rsmd.getColumnLabel(column));
				}
				// add rows content to the model
				Object[] objects = new Object[columnCount];
				while (rs.next()) {
					cnt = 0;
					// add column content of next row to the model
					while (cnt < columnCount) {
						objects[cnt] = rs.getObject(cnt + 1);
						cnt++;
					}
					model.addRow(objects);
				}

				con.disconnect();
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex);
		}
		// set model content to JTable view and return JTable view
		tableview.setModel(model);
		return tableview;
	}

	// 1. update JTable view based on a generic SQL query
	public void updateSQLTable(String SQLquery) {
		int columnCount = 0;
		int rowCount = 0;
		int cnt = 0;
		// 1.1 get model from reference to JTable view and initialize model
		DefaultTableModel model = (DefaultTableModel) SQLTable.getModel();
		rowCount = model.getRowCount();
		while (rowCount != 0) {
			model.removeRow(rowCount - 1);
			rowCount--;
		}
		try { // get current DB connection
			DB_connection con = DB_connection.getDbConnection();
			ResultSet rs = con.executequery_rs(SQLquery);
			if (rs != null) { // 1.2 fill model: identify column size of the
								// result set
				ResultSetMetaData rsmd = rs.getMetaData();
				columnCount = rsmd.getColumnCount();
			}
			// 1.3 add rows content to the model
			Object[] objects = new Object[columnCount];
			while (rs.next()) {
				cnt = 0;
				// 1.4 add column content of next row to the model
				while (cnt < columnCount) {
					objects[cnt] = rs.getObject(cnt + 1);
					cnt++;
				}
				model.addRow(objects);
			}
			con.disconnect();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex);
		}
		// 1.5 refresh model content to JTable view
		SQLTable.revalidate();
	}
}