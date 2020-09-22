package com.isika.cdi5.tp.banque.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.isika.cdi5.tp.banque.model.Client;
import com.isika.cdi5.tp.banque.model.CompteBancaire;
import com.isika.cdi5.tp.banque.model.TypeCompteBancaire;
import com.isika.cdi5.tp.banque.repository.Repository;

public class MainTpBanque {
	
	private static Repository repository = new Repository();

	public static void main(String[] args) {
		
		// Vérifier que hibernate se lance
		CompteBancaire compteBancaire = new CompteBancaire();
		compteBancaire.setNumeroCompte(UUID.randomUUID().toString());
		compteBancaire.setDateCreation(new Date());
		compteBancaire.setType(TypeCompteBancaire.COMPTE_COURANT);
		compteBancaire.setSolde(new BigDecimal(150));
		
		Client client = new Client();
		client.setNom("Test");
		client.setPrenom("TestPrenom");
		client.setAdresse("1 rue Test");
		client.setTelephone("+33612345678");
		client.setMail("test@test.com");

		client.setCompteBancaire(compteBancaire);
		compteBancaire.setClient(client);
		
		Long clientId = repository.persist(client);
		System.out.println(client);
		System.out.println(compteBancaire);
		
		client = repository.rechercheClient(clientId);
		
		// Mise à jour de solde
		repository.depot(client.getCompteBancaire().getId(), new BigDecimal(50));
		
		// Fermer les ressources
		HibernateUtil.closeAll();
	}

	

}
