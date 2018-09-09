package mySqlBase;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mySqlBase.entities.BddTabMatiere;
import mySqlBase.entities.BddTabProfesseur;

public interface IBdd {
	/**
	 * insertProffeseur
	 * ajoute un proffesseur dans la table
	 * 
	 * @param proffesseur
	 */
	public static boolean insertProfeseur(BddManager bdm, String profNom , String profPrenom) {
		bdm.bddManagerExecuteUpdate("INSERT INTO professeur (prof_nom, prof_prenom) values('"+profNom+"'"+",'"+profPrenom+"');");
		return true;
	}
	
	public static boolean insertMatiere(BddManager bdm,String matiere) { 
		try {
			bdm.connect();
			Statement st = bdm.connection.createStatement();
			String tempo =("INSERT INTO matiere (mat_nom) values('"+matiere
					+"'"+");");
			st.executeUpdate(tempo);			
		} catch (SQLException e) {
			// SQL error 
			e.printStackTrace();
			return false;
		}
		bdm.close();
		return true;
	}
	
	// changer le nom d'un professeur
	public static boolean changeProfesseur(BddManager bdm,int id, String newName, String newFirstName) {
		try {
			bdm.connect();
			Statement st = bdm.connection.createStatement();
			String tempo =("UPDATE professeur SET prof_nom ='"+newName+"' WHERE prof_id = "+id);
			String tempo2 = ("UPDATE professeur SET prof_prenom = '"+newFirstName+"' WHERE prof_id = "+id);
			st.executeUpdate(tempo);
			st.executeUpdate(tempo2);

		} catch (SQLException e) {
			//SQL Error
			e.printStackTrace();
		}
		bdm.close();
		
		
		return true;
	}
	
	// changer le nom d'une matière
	public static boolean changeMatiere(BddManager bdm,int id, String newName) {
		try {
			bdm.connect();
			Statement st = bdm.connection.createStatement();
			String tempo =("UPDATE matiere SET mat_nom ='"+newName+"' WHERE mat_id = "+id);
			st.executeUpdate(tempo);			
		} catch (SQLException e) {
			// SQL error 
			e.printStackTrace();
			return false;
		}
		bdm.close();
		return true;
	}
	
	// affichage des professeurs
	public static ArrayList<BddTabProfesseur> showProfesseur(BddManager bdm) { 
		ArrayList<BddTabProfesseur> listProf = new ArrayList<BddTabProfesseur>();
		try {
			bdm.connect();
			Statement st = bdm.connection.createStatement();
			ResultSet result = st.executeQuery("select * from professeur");
			while (result.next()) {
				int idProf = result.getInt("prof_id");
				String nameProf = result.getString("prof_nom");
				String firstNameProf = result.getString("prof_prenom");
				BddTabProfesseur prof = new BddTabProfesseur(idProf, nameProf, firstNameProf);
				listProf.add(prof);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		bdm.close();
		return listProf;	
	}
	
	// affichage des matières
	public static ArrayList<BddTabMatiere> showMatiere(BddManager bdm) {
		ArrayList<BddTabMatiere> listMatiere = new ArrayList<BddTabMatiere>();
		try {
			bdm.connect();
			if (bdm.connection != null) {
				
				Statement st = bdm.connection.createStatement();
				ResultSet result = st.executeQuery("select * from matiere");
				while(result.next()) {
					int idMatiere = (result.getInt("mat_id"));
					String nameMatiere = (result.getString("mat_nom"));
					BddTabMatiere mat =new BddTabMatiere(idMatiere , nameMatiere);
					listMatiere.add(mat);
				}
			}else {
				System.out.println("Connection NULLE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bdm.close();
		return listMatiere; 
	}
	
	
}
