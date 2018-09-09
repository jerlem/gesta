package window;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import window.entities.TableModelMats;
import window.entities.TableModelProf;
import window.entities.TableModelSelection;

public class WindowTables implements IEvents {
	
	/**
	 * updateProfesseur
	 * 
	 * Load/update Professeur JTable from Database
	 * 
	 * @param w
	 */
	public void updateTableProfesseur(Window w) {
		// Title
		JLabel titleProfesseurs = new JLabel("Liste des Professeurs");
		titleProfesseurs.setFont(new Font("Verdana", Font.PLAIN, 18));
		w.panelProfesseur.add(titleProfesseurs, BorderLayout.NORTH);	
		
		// prepare JTable content
		w.updateProfesseur();
		// apply Model with header and data
		w.modelProfs = new TableModelProf(w.listProfs);
		
		w.tblProf = new JTable(w.modelProfs);
		/** one row selection @see TableModelSelection */
		w.tblProf.setSelectionModel(new TableModelSelection());
		w.panelProfesseur.add(new JScrollPane(w.tblProf), BorderLayout.WEST);
		
		// TABLE event selected
		w.tblProf.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			// add listener cast trhought ListSelectionModel 
			// apply to w.selectedProf
	        public void valueChanged(ListSelectionEvent evt) {
	        	ListSelectionModel selection = (ListSelectionModel)evt.getSource();	        	
	        	w.selectedProf = selection.getMinSelectionIndex();
	        	w.updateProfPanel();
	        }
	    });			
	}
	
	/**
	 * updateTableMatiere
	 * 
	 * Load/update Matiere JTable from Database
	 * 
	 * @param w
	 */
	public void updateTableMatiere(Window w) {
		// Title
		JLabel titleMatiere = new JLabel("Liste des Matières");
		titleMatiere.setFont(new Font("Verdana", Font.PLAIN, 18));
		w.panelMatiere.add(titleMatiere, BorderLayout.NORTH);	
		
		// prepare JTable content
		w.updateMatiere();
		// apply Model with header and data
		w.modelMats = new TableModelMats(w.listMats);
		
		w.tblMats = new JTable(w.modelMats);
		/** one row selection @see TableModelSelection */
		w.tblMats.setSelectionModel(new TableModelSelection());
		w.panelMatiere.add(new JScrollPane(w.tblMats), BorderLayout.WEST);
		
		// TABLE event selected
		w.tblMats.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			// add listener cast trhought ListSelectionModel 
			// apply to w.selectedMats
	        public void valueChanged(ListSelectionEvent evt) {
	        	ListSelectionModel selection = (ListSelectionModel)evt.getSource();	        	
	        	w.selectedMat = selection.getMinSelectionIndex();
	        	w.updateMatPanel();
	        }
	    });
		
				
	}

}
