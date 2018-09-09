package window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;

import mySqlBase.*;
import mySqlBase.entities.BddTabProfesseur;
import window.Window.menuSelected;
import window.entities.TableModelProf;


@SuppressWarnings("unused")
public interface IWindowInterface {
	
	public static void createInterface(Window w) {
		// create and attach JPanel with BorderLayout:
		w.setLayout(new BorderLayout());
		/* ----------------------------------------------------------------------
		 * 	
		 * 		Updating
		 * 
		 * ---------------------------------------------------------------------- */
		w.addMouseListener(new MouseListener(){
		    @Override public void mouseEntered(MouseEvent e) { IEvents.update(w); }
			@Override public void mouseClicked(MouseEvent e) { IEvents.update(w); } 
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseReleased(MouseEvent e) {}
		});
		
		/* ----------------------------------------------------------------------
		 * 	
		 * 		Panel Main: 
		 * 		Card Layout containing 
		 * 		- tableSelection
		 * 		- Professeur
		 * 		- Matiere
		 *  
		 * ---------------------------------------------------------------------- */
		w.panelMain.setLayout(new CardLayout());
		
		w.panelMain.add(w.panelTableSelection, "root");
		w.panelTableSelection.setLayout(new BorderLayout());

		w.panelMain.add(w.panelProfesseur, "professeurs");
		w.panelProfesseur.setLayout(new BorderLayout());

		w.panelMain.add(w.panelMatiere, "matieres");
		w.panelMatiere.setLayout(new BorderLayout());

		w.add(w.panelMain);
		w.switchMenu(menuSelected.root);

		/* ----------------------------------------------------------------------
		 * 	
		 * 		PANEL CARD : TABLE SELECTION
		 * 		Card Layout containing 
		 * 		- tableSelection
		 * 		- Professeur
		 * 		- Matiere
		 *  
		 * ---------------------------------------------------------------------- */
		// Title
		JLabel titleRoot = new JLabel("Liste des Tables");
		titleRoot.setFont(new Font("Verdana", Font.PLAIN, 18));
		w.panelTableSelection.add(titleRoot, BorderLayout.NORTH);
		
		// Button Matieres and event
		JPanel grpSelect = new JPanel();
		JButton buttonMatiere = new JButton("Table : Matières");
		buttonMatiere.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					w.tableManager.updateTableMatiere(w);
					w.switchMenu(menuSelected.Matiere);
				}
			});
		grpSelect.add(buttonMatiere, BorderLayout.WEST);
		
		// Button Professeurs and event
		JButton buttonProfesseurs = new JButton("Table : Professeurs");
		grpSelect.add(buttonProfesseurs, BorderLayout.EAST);
		buttonProfesseurs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				w.tableManager.updateTableProfesseur(w);
				w.switchMenu(menuSelected.Professeur);
			}
		});
		w.panelTableSelection.add(grpSelect);


		/* ------------------------------------- 
		 *	Options Panel for Professeur table
		 * ------------------------------------- */
		
		JPanel optionsPanelProfesseur = new JPanel();
		
		// ID Label
		JLabel lblProfId = new JLabel("id    ");
		lblProfId.setPreferredSize(new Dimension(40, 25));
		optionsPanelProfesseur.add(lblProfId, BorderLayout.LINE_START);
		// ID TextField
		w.txtProfId.setEnabled(false);
		w.txtProfId.setPreferredSize(new Dimension(150, 25));
		optionsPanelProfesseur.add(w.txtProfId, BorderLayout.LINE_END);
		
		
		// NOM Label
		JLabel lblProfNom = new JLabel("Nom   ");
		lblProfNom.setPreferredSize(new Dimension(40, 25));
		optionsPanelProfesseur.add(lblProfNom);	
		// NOM TextField
		w.txtProfNom.setPreferredSize(new Dimension(150, 25));
		optionsPanelProfesseur.add(w.txtProfNom, BorderLayout.LINE_END);
		
		
		// PRENOM Label
		JLabel lblProfPrenom = new JLabel("Prenom");
		lblProfPrenom.setPreferredSize(new Dimension(40, 25));
		// PRENOM Textfield
		optionsPanelProfesseur.add(lblProfPrenom, BorderLayout.LINE_START);
		w.txtProfPrenom.setPreferredSize(new Dimension(150, 25));
		optionsPanelProfesseur.add(w.txtProfPrenom, BorderLayout.LINE_END);
				
		// BUTTONS
		JButton addProfesseur = new JButton("Ajout");
		addProfesseur.setPreferredSize(new Dimension(70, 25));
		optionsPanelProfesseur.add(addProfesseur);
		addProfesseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				w.addNewProfesseur();
			}
		});
		

		JButton modifyProfesseur = new JButton("Renomer");
		modifyProfesseur.setPreferredSize(new Dimension(90, 25));
		optionsPanelProfesseur.add(modifyProfesseur);
		modifyProfesseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				w.changeProfesseur();
			}
		});
		
		
		JButton returnProfesseur = new JButton("<");
		returnProfesseur.setPreferredSize(new Dimension(50, 25));
		optionsPanelProfesseur.add(returnProfesseur);
		returnProfesseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				w.tableManager.updateTableProfesseur(w);
				w.switchMenu(menuSelected.root);
			}
		});
		
		w.panelProfesseur.add(optionsPanelProfesseur);

		

		/* ------------------------------------- 
		 *	Options Panel for Matiere table
		 * ------------------------------------- */
		
		JPanel optionsPanelMat = new JPanel();
		
		// ID Label
		JLabel lblMatId = new JLabel("id    ");
		lblMatId.setPreferredSize(new Dimension(40, 25));
		optionsPanelMat.add(lblMatId, BorderLayout.LINE_START);
		// ID TextField
		w.txtMatId.setEnabled(false);
		w.txtMatId.setPreferredSize(new Dimension(150, 25));
		optionsPanelMat.add(w.txtMatId, BorderLayout.LINE_END);
		
		
		// NOM Label
		JLabel lblMatNom = new JLabel("Nom   ");
		lblMatNom.setPreferredSize(new Dimension(40, 25));
		optionsPanelMat.add(lblMatNom);	
		// NOM TextField
		w.txtMatNom.setPreferredSize(new Dimension(150, 25));
		optionsPanelMat.add(w.txtMatNom, BorderLayout.LINE_END);
		
				
		// BUTTONS
		JButton addMatiere = new JButton("Ajout");
		addMatiere.setPreferredSize(new Dimension(70, 25));
		optionsPanelMat.add(addMatiere);
		addMatiere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				w.addNewMatiere();
			}
		});
		
		JButton modifyMat = new JButton("Renomer");
		modifyMat.setPreferredSize(new Dimension(90, 25));
		optionsPanelMat.add(modifyMat);
		modifyMat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				w.changeMatiere();
			}
		});
		
		JButton returnMatiere = new JButton("<");
		returnMatiere.setPreferredSize(new Dimension(50, 25));
		optionsPanelMat.add(returnMatiere);
		returnMatiere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				w.tableManager.updateTableMatiere(w);
				w.switchMenu(menuSelected.root);
			}
		});
		
		w.panelMatiere.add(optionsPanelMat);
		
		//* ---------------------------------------------------------------------- */
		w.revalidate();
		w.repaint();
	}

	
	
}