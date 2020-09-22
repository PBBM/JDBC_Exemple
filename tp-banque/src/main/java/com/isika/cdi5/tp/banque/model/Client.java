package com.isika.cdi5.tp.banque.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nom;
	private String prenom;
	private String adresse;

	private String telephone;
	private String mail;

	@OneToOne(mappedBy = "client", orphanRemoval = true, cascade = { CascadeType.REFRESH })
	private CompteBancaire compteBancaire;

	public Client() {
	}

	public Long getId() {
		return id;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public CompteBancaire getCompteBancaire() {
		return compteBancaire;
	}

	public void setCompteBancaire(CompteBancaire compteBancaire) {
		compteBancaire.setClient(this);
		this.compteBancaire = compteBancaire;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", adresse=");
		builder.append(adresse);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", mail=");
		builder.append(mail);
		builder.append(", compteBancaire=");
		builder.append(compteBancaire);
		builder.append("]");
		return builder.toString();
	}
	
}
