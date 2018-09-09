package window;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.*;

import window.entities.*;
import mySqlBase.*;
import mySqlBase.entities.BddTabMatiere;
import mySqlBase.entities.BddTabProfesseur;


/** --------------------------------------------------------------------------------------
 * 
 * 			Window
 * 
 *  --------------------------------------------------------------------------------------
 *  
 *	JFrame Handling : events, mouseEvent, frameEvents , menu Factory, etc.
 * 
 * @author JLemarchand (c), 2018
 * @version 0.1
 * 
 * -------------------------------------------------------------------------------------- */
public class Window extends JFrame implements
				ActionListener, IWindowSettings, IEvents, IWindowInterface, IBdd {
	
	public enum menuSelected { root, Professeur, Matiere }
	/**
	 * Generated SVUID
	 */
	private static final long serialVersionUID = 2822392513190739565L;
	
	// for scheduled operations 
	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	// Window handling	
	protected Ressource ressource;
	protected WindowEventAdapter evtWindow = new WindowEventAdapter();


	// MenuBar elements :
	protected WindowMenu menu;
	protected JMenuBar swingMenuBar;
	protected ArrayList<JMenu> swingMenu = new ArrayList<JMenu>();
	protected ArrayList<JMenuItem> swingMenuItem = new ArrayList<JMenuItem>();
	
	// Panel and Window Elements :
	protected JPanel panelMain = new JPanel();	
	// Menu selection and panels
	protected menuSelected currentMenu = menuSelected.Professeur;
	protected JPanel panelTableSelection = new JPanel();
	protected JPanel panelProfesseur = new JPanel();
	protected JPanel panelMatiere = new JPanel();
	
	// text area and scroll bar
	protected JTextField mailSubject = new JTextField();
	protected JTextArea mailContent = new JTextArea();
	protected JScrollPane scrollBar = new JScrollPane();
	
	// status bar elements
	protected JLabel labelInfo = new JLabel();
	protected JLabel labelErr = new JLabel();
	
	// tables
	public WindowTables tableManager = new WindowTables();
	
	/**
	 *  Professeurs
	 */
	public ArrayList<BddTabProfesseur> listProfs = new ArrayList<BddTabProfesseur>();
	protected TableModelProf modelProfs;
	protected JTable tblProf = new JTable();
	protected int selectedProf = -1;
	
	protected JTextField txtProfId = new JTextField("");
	protected JTextField txtProfNom = new JTextField("");
	protected JTextField txtProfPrenom = new JTextField("");
	
	/**
	 *  Matiere
	 */
	public ArrayList<BddTabMatiere> listMats = new ArrayList<BddTabMatiere>();
	protected TableModelMats modelMats;
	protected JTable tblMats = new JTable();
	protected int selectedMat = -1;
	
	protected JTextField txtMatId = new JTextField("");
	protected JTextField txtMatNom = new JTextField("");
	
	// database infos:
	protected BddManager dataBase = new BddManager("jdbc:mysql://localhost:3306/gesta",
												   "root", "",
												   "?serverTimezone=UTC&useSSL=false");
	
	/**
	 * Constructor uses WindowSetting interface to set and configure
	 * @see IWindowSettings.java
	 */
	public Window() {}
	
	public void init() {
		IWindowSettings.init(this);
		this.addWindowListener(evtWindow);
		IEvents.update(this);
		
		listMats = IBdd.showMatiere(dataBase);
	}
	
	// TODO : IMPLEMENT
	public void updateInterface() { }
	
	/**
	 *  setInterface 
	 *  
	 *  add interface defined in IWindowInterface.java
	 */
	public void setInterface() { IWindowInterface.createInterface(this); }
	
	// trasmit Action event
	@Override public void actionPerformed(ActionEvent e) { IEvents.action(this, e); }
	
	
	/* -------------------------------------------------------------------------------------------
	 * 
	 * 	CUSTON METHODS
	 * 
	 * ------------------------------------------------------------------------------------------ */
	
	/**
	 * switchMenu
	 * 
	 * Change content Panel
	 *  
	 * @param menu
	 */
	public void switchMenu(menuSelected menu) {
		// no changes?
		if (this.currentMenu == menu) return;
		// change menu selected
		// get the cardLayout to change current card
		CardLayout cardLayout = (CardLayout) panelMain.getLayout();

		if (menu == menuSelected.root) {
			cardLayout.show(panelMain, "root");
		} else if (menu == menuSelected.Matiere) {
			updateMatiere();
			cardLayout.show(panelMain, "matieres");
		} else {
			updateProfesseur();
			cardLayout.show(panelMain, "professeurs");
		}
		this.currentMenu = menu;
		System.out.println("menu switch ->" + menu.toString());
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * update ProfPanel TextFields to the selected table row
	 */
	public void updateProfPanel() {
		BddTabProfesseur currentProf = listProfs.get(selectedProf);
		txtProfId.setText( String.valueOf(currentProf.getId() ));
		txtProfNom.setText( currentProf.getNom() );
		txtProfPrenom.setText( currentProf.getPrenom() );
	}
	
	/**
	 * update MatPanel TextFields to the selected table row
	 */
	public void updateMatPanel() {
		BddTabMatiere currentMat = listMats.get(selectedMat);
		txtMatId.setText( String.valueOf(currentMat.getId() ));
		txtMatNom.setText( currentMat.getNom() );
	}
	
	/**
	 * baseShowMatiere
	 * 
	 * load/update professeur Table
	 */
	public void updateProfesseur() {
		//Update Profs list
		System.out.println("updating table profs");
		// make request
		listProfs = IBdd.showProfesseur(dataBase);

		// hold on
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// apply new model
		TableModelProf nm = new TableModelProf(listProfs);
		nm.fireTableDataChanged();
		tblProf.setModel(nm);
		
		// repaint
		repaint();
		revalidate();
	}

	/**
	 * baseShowMatiere
	 * 
	 * load/update Matiere Table
	 */	
	public void updateMatiere() {
		//Update Profs list
		System.out.println("updating matiere profs");
		// make request
		listMats = IBdd.showMatiere(dataBase);

		// hold on
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// apply new model
		TableModelMats nmat = new TableModelMats(listMats);
		nmat.fireTableDataChanged();
		tblMats.setModel(nmat);
		
		// repaint
		repaint();
		revalidate();
	}
	
	/**
	 * 
	 * Add a new Professeur from txtProfNom, txtProfPrenom
	 */
	public void addNewProfesseur() {
		String nom= txtProfNom.getText();
		String prenom = txtProfPrenom.getText();
		
		if (nom.length() > 5 && prenom.length() > 1) {
			IBdd.insertProfeseur(dataBase, nom , prenom);
			updateProfesseur();	
		} else {
			JOptionPane.showMessageDialog(this, "Champs Nom ou Prénom Invalides");
		}
	}	
	
	/**
	 * 
	 * Add a new Matiere from txtProfNom
	 */
	public void addNewMatiere() {
		String nom= txtMatNom.getText();
		
		if (nom.length() > 2) {
			IBdd.insertMatiere(dataBase, nom);
			updateMatiere();
		} else {
			JOptionPane.showMessageDialog(this, "Champ Nom Invalide");
		}
	}	
	
	/**
	 * Change a Professeur from txtProfNom, txtProfPrenom
	 */
	public void changeProfesseur() {
		String nom= txtProfNom.getText();
		String prenom = txtProfPrenom.getText();
		
		if (nom.length() > 3 && prenom.length() > 1) {
			IBdd.changeProfesseur(dataBase, selectedProf+1,  nom , prenom);
			updateProfesseur();
		} else {
			JOptionPane.showMessageDialog(this, "Champs Nom ou Prénom Invalides");
		}
		
	}
	
	/**
	 * Change a Professeur from txtProfNom, txtProfPrenom
	 */
	public void changeMatiere() {
		String nom= txtMatNom.getText();
		
		if (nom.length() > 3) {
			IBdd.changeMatiere(dataBase, selectedMat+1,  nom);
			updateMatiere();
		} else {
			JOptionPane.showMessageDialog(this, "Champs Nom ou Prénom Invalides");
		}
		
	}
	
}
