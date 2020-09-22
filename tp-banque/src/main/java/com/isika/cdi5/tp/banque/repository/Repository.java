package com.isika.cdi5.tp.banque.repository;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.isika.cdi5.tp.banque.model.Client;
import com.isika.cdi5.tp.banque.model.CompteBancaire;
import com.isika.cdi5.tp.banque.utils.HibernateUtil;

public class Repository {

	private EntityManager entityManager;
	
	public Repository() {
		this.entityManager = HibernateUtil.createEntityManager();
	}
	
	public Long persist(Client client) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		// Bidirectionnalité
		client.getCompteBancaire().setClient(client);
		client.setCompteBancaire(client.getCompteBancaire());
		
		// On persiste le compte
		entityManager.persist(client.getCompteBancaire());
		entityManager.persist(client);
		
		transaction.commit();
		return client.getId();
	}

	public CompteBancaire rechercheCompte(Long id) {
		return this.entityManager.find(CompteBancaire.class, id);
	}

	public Client rechercheClient(Long clientId) {
		return this.entityManager.find(Client.class, clientId);
	}
	
	public void depot(Long compteId, BigDecimal valeur) {
		Optional<CompteBancaire> optionalCompte = Optional.ofNullable(rechercheCompte(compteId));
		if(optionalCompte.isPresent()) {
			CompteBancaire compteBancaire = optionalCompte.get();

			BigDecimal ancienSolde = compteBancaire.getSolde();
			System.out.println("Ancien solde : " + ancienSolde);
			
			BigDecimal nouveauSolde = ancienSolde.add(valeur);
			compteBancaire.setSolde(nouveauSolde);
			System.out.println("Nouveau solde : " + nouveauSolde);
			
			// Mise à jour en BDD
			compteBancaire = modificationCompte(compteBancaire);
		} else {
			System.err.println("Aucun compte bancaire avec ID=" + compteId);
		}
	}
	
	public CompteBancaire modificationCompte(CompteBancaire compteBancaire) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		CompteBancaire merged = this.entityManager.merge(compteBancaire);
		this.entityManager.flush();
		transaction.commit();
		return merged;
	}
}
