package utils;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Recherche {

	public void sort(String query, DefaultTableModel model, JTable table) {
		TableRowSorter<DefaultTableModel> tableSort = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tableSort);
		
		tableSort.setRowFilter(RowFilter.regexFilter(query));
		
	}

}
