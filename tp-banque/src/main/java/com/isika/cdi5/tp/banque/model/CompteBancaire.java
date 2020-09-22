package com.isika.cdi5.tp.banque.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CompteBancaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String numeroCompte;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Enumerated(EnumType.STRING)
	private TypeCompteBancaire type;

	private BigDecimal solde;

	@OneToOne
	private Client client;
	
	public CompteBancaire() {
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public TypeCompteBancaire getType() {
		return type;
	}

	public void setType(TypeCompteBancaire type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getSolde() {
		return solde;
	}

	public void setSolde(BigDecimal bigDecimal) {
		solde = bigDecimal;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompteBancaire [id=");
		builder.append(id);
		builder.append(", numeroCompte=");
		builder.append(numeroCompte);
		builder.append(", dateCreation=");
		builder.append(dateCreation);
		builder.append(", type=");
		builder.append(type);
		builder.append(", solde=");
		builder.append(solde);
		builder.append(", clientId=");
		builder.append(client.getId());
		builder.append("]");
		return builder.toString();
	}
	
}
