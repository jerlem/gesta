package window;

import javax.swing.JFrame;

import window.WindowMenu;

public interface IWindowSettings {
	/* ----------------------------------------------------------------------
	 *	Window parameters HERE :
	 * ---------------------------------------------------------------------- */	
	public static int		windowWidth		= 700;
	public static int		windowHeight	= 350;
	
	public static Boolean	debug			= true;
			
	/* ---------------------------------------------------------------------- */
	
	
	public static void getMenuItems(Window w) {
		
		w.menu.addMenu(new String[] {
	/* ----------------------------------------------------------------------
	 *	Menu Elements HERE :
	 * { separator, enabled, category, caption, description, event, mnemonic, shortcut }
	 * ---------------------------------------------------------------------- */	
			// File | New
			"false", "true",
			"Afficher", "Professeur", "affiche la table des professeurs",
			"menuSelected_SelectTableProf",
			"",""
			
			,"false", "true",
			"Afficher", "Matiere", "affiche la table des matières enseignées",
			"menuSelected_SelectTableMat",
			"", ""
			
			,"false", "true",
			"Afficher", "Retour à la liste", "",
			"menuSelected_Retour",
			"", ""
			
			,"false", "true",
			"Quitter", "Quitter", "Quitte l'application",
			"menuSelected_Quitter",
			"", ""
			
			,"false", "true",
			"?", "A propos", "A propos",
			"menuSelected_About",
			"", ""
			
		});
		
	}
	 
	public static void getIconRessource(Window w) { }
	

	/**
	 * init(Window w)
	 * 
	 * sets most of the parameters :
	 * 		- Window dimension and name and basic EXIT_ON_CLOSE
	 * 		- Retrieve Language data and place into Ressources.texts
	 * 		- Set Window title name and icon 
	 * 		- Create menu elements and attach it to the Window
	 * 		- Turn window Visible
	 * @param w
	 */
	public static void init(Window w) {
		// JFrame settings
		w.setTitle("Gesta");
		w.setSize (windowWidth, windowHeight);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create menuBar
		w.menu = new WindowMenu(w);
		getMenuItems(w);
		w.menu.createMenu(w);
		
		// lastly make window visible
		w.setVisible(true);		
		w.setInterface();
	}

	
	
}
