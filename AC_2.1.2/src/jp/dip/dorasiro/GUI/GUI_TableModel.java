package jp.dip.dorasiro.GUI;

import javax.swing.table.DefaultTableModel;

/**
*@author Dorasiro
*/

public class GUI_TableModel extends DefaultTableModel {
	public GUI_TableModel(String[][] tableData, String[] columnNames) {
		super(tableData, columnNames);
	}

	@Override
	public boolean isCellEditable(int row,int col) {
		if(col == 3) {
			return false;
		}else {
			return true;
		}
	}
}
