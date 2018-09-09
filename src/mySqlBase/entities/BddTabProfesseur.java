package mySqlBase.entities;

public class BddTabProfesseur {
	int id;
	String nom;
	String prenom;
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public BddTabProfesseur(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return "BddTabProfesseur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
}
