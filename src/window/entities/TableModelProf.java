package window.entities;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import mySqlBase.entities.BddTabProfesseur;

public class TableModelProf extends AbstractTableModel  {
	
    private final ArrayList<BddTabProfesseur> profs = new ArrayList<BddTabProfesseur>();
    
    final String[]  header = { "Id", "Nom", "Prenom"};
	private static final long serialVersionUID = 1L;

	/**
	 * Constuctor : needs header and data
	 */
	public TableModelProf(ArrayList<BddTabProfesseur> data) {
		super();
		
		for (int i = 0; i < data.size(); i++) {
			profs.add(data.get(i));
		}
	}
	
	// counts cols and rows
	@Override public int getColumnCount() { return header.length; }
	@Override public int getRowCount() { return profs.size(); }

	public String getColumnName(int col) {
		return header[col];
	}
	
	// retrieve values
	@Override
	public Object getValueAt(int row, int col) {
		 switch(col){
         case 0:
             return String.valueOf(profs.get(row).getId());
         case 1:
             return profs.get(row).getNom();
         case 2:
             return profs.get(row).getPrenom();
         default:
     		return null;
		 }
	}

}
