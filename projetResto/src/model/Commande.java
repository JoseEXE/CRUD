package model;

import java.sql.Timestamp;

public class Commande {
	private int id;
	private Client id_client;
	private User id_user;
	private String commentaire;
	private Double total;
	private Timestamp date_comm;
	private String Type_paiement;
	private String etat;
	
	/*
	 * constructeur pour la methode create() qui sert a hydrater la base de donnée commande
	 */
	public Commande(Client id_client, User id_user, String commentaire, Double total, Timestamp date_comm,
			String type_paiement, String etat) {
		this.id_client = id_client;
		this.id_user = id_user;
		this.commentaire = commentaire;
		this.total = total;
		this.date_comm = date_comm;
		Type_paiement = type_paiement;
		this.etat = etat;
	}
	
/*
 * constructeur commande pour debuter la commande apres avoir identifié/creer un client, et dans le but de creer des lignes 
 * de details de commande
 */
	
	public Commande() {
		
	}
/*
 * constructeur pour instancier une commande lors du findById de détail_Commande
 */
	public Commande(int id) {
		this.id = id;
	}
	

	public Commande(int id, Client id_client, User id_user, Timestamp date_comm, String etat) {
	this.id = id;
	this.id_client = id_client;
	this.id_user = id_user;
	this.date_comm = date_comm;
	this.etat = etat;
}


	/*
	 * generation getters setters
	 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Client getId_client() {
		return id_client;
	}

	public void setId_client(Client id_client) {
		this.id_client = id_client;
	}

	public User getId_user() {
		return id_user;
	}

	public void setId_user(User id_user) {
		this.id_user = id_user;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Timestamp getDate_comm() {
		return date_comm;
	}

	public void setDate_comm(Timestamp date_comm) {
		this.date_comm = date_comm;
	}

	public String getType_paiement() {
		return Type_paiement;
	}

	public void setType_paiement(String type_paiement) {
		Type_paiement = type_paiement;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", id_client=" + id_client + ", id_user=" + id_user + ", date_comm=" + date_comm
				+ ", etat=" + etat + "]";
	}

	
}
