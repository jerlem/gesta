package mySqlBase.entities;

public class BddTabMatiere {
	int id;
	String nom;
	/**
	 * @param id
	 * @param nom
	 */
	public BddTabMatiere(int id, String nom) {
		this.id = id;
		this.nom = nom;
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
	@Override
	public String toString() {
		return "BddTabMatiere [id=" + id + ", nom=" + nom + "]";
	}
	
}
