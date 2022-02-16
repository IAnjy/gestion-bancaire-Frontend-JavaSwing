package utils;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Recherche {

	public void sort(String query, DefaultTableModel model, JTable table) {
		// TODO Auto-generated method stub
		TableRowSorter<DefaultTableModel> tableSort = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tableSort);
		
		tableSort.setRowFilter(RowFilter.regexFilter(query));
		
		/*TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));*/
	}

}
