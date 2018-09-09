package window;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import window.Window.menuSelected;



public interface IEvents {

	/*  ------------------------------------------------------------------------- 
	 *   	
	 *   	FRAME EVENTS 
	 *  
	 *  ------------------------------------------------------------------------- */
	public static void onWindowOpen() { }
	public static void onWindowChanged() { }
	public static void onWindowReduce() { }
	public static void onWindowRestore() { }
	public static void onWindowUnfocus() { }
	public static void onWindowFocus() { }
	public static void onWindowClose() { } 
	/*  -------------------------------------------------------------------------
	 *  		
	 *  	MENU EVENTS
	 *  
	 *  ------------------------------------------------------------------------- */
	public static void onMenuSelected(String evt, Window w) {
		
		switch (evt) { 
		/* CODE HERE */			
			case "menuSelected_Version":
			break;
			
			case "menuSelected_SelectTableProf":
				//change menu shown and refresh
				w.tableManager.updateTableProfesseur(w);
				w.switchMenu(menuSelected.Professeur);
				
			break;
			
			case "menuSelected_SelectTableMat":
				w.switchMenu(menuSelected.Matiere);
			break;
			
			case "menuSelected_Retour":
				w.switchMenu(menuSelected.root);
			break;
			
			case "menuSelected_Quitter":
				w.executor.shutdown();
				w.dispose();
			break;
			
			case "menuSelected_About":
				String message ="Gesta V1.0 \n(c) 2018, Jérome Lemarchand\n et William Lecomte";
				JOptionPane.showMessageDialog(w, message);
			break;
		/* --- END CODE  --- */

		}
	}	

	
	/*  -------------------------------------------------------------------------
	 *  		
	 *  	INTERFACE EVENTS
	 *  
	 *  ------------------------------------------------------------------------- */
	public static void action(Window w, ActionEvent e) {
		/*String triggeredBy = e.getActionCommand();
		
		switch (triggeredBy) {
			case "":
			break;
		}*/
	}

	
	/*  -------------------------------------------------------------------------
	 *  		
	 *  	UPDATE
	 *  
	 *  ------------------------------------------------------------------------- */
	
	public static void update(Window w) {
		/* --- CODE HERE --- */
		/* --- END CODE  --- */
	}
	
}
