package window.entities;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import mySqlBase.entities.BddTabMatiere;

public class TableModelMats extends AbstractTableModel {
	private final ArrayList<BddTabMatiere> mats = new ArrayList<BddTabMatiere>();
	    
	    final String[]  header = { "Id", "Nom"};
		private static final long serialVersionUID = 1L;
	
		/**
		 * Constuctor : needs header and data
		 */
		public TableModelMats(ArrayList<BddTabMatiere> data) {
			super();
			
			for (int i = 0; i < data.size(); i++) {
				mats.add(data.get(i));
			}
		}
		
		// counts cols and rows
		@Override public int getColumnCount() { return header.length; }
		@Override public int getRowCount() { return mats.size(); }
	
		public String getColumnName(int col) {
			return header[col];
		}
		
		// retrieve values
		@Override
		public Object getValueAt(int row, int col) {
			 switch(col){
	         case 0:
	             return String.valueOf(mats.get(row).getId());
	         case 1:
	             return mats.get(row).getNom();
	         default:
	     		return null;
			 }
		}
}
