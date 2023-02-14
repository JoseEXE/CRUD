package model;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String numtel;
	private String statut;
	/*
	 * variable id pour enregistrer un nouveau client
	 */
	public static String idNewClient="";
	
	/*
	 * Constructeur complet
	 */
	public Client(int id, String nom, String prenom, String numtel, String statut) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numtel = numtel;
		this.statut = statut;
	}
	
	/*
	 * Constructeur Create()
	 */
	public Client(String nom, String prenom, String numtel) {
		this.nom = nom;
		this.prenom = prenom;
		this.numtel = numtel;
	}
	
	/*
	 * Constructeur update()
	 */
	public Client(int id, String nom, String prenom, String numtel) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numtel = numtel;
	}
	/*
	 * Constructeur vide
	 */
	public Client() {

	}

	/*
	 * Getters setters
	 */
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

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
