package com.infotel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infotel.metier.Adresse;
import com.infotel.metier.Personne;
import com.infotel.metier.Connexion;

public class DaoImpl implements Idao {

	
	//unité de persistance -> crée l'autoroute
	EntityManagerFactory emf;
	
	//porteur de requetes
	EntityManager em;
	
	@Override
	public int ajouterPersonne(Personne p) {
		
		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.persist(p);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
		
		return p.getId();
	}

	@Override
	public Personne getPersonne(int idPersonne) {
		
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Personne p = new Personne();
		
		try {
			//1-recuperer personne
			p = em.getReference(Personne.class, idPersonne);
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public int supprimerPersonne(Personne p) {
		
		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.remove(p);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
				
		return p.getId();
	}

	@Override
	public int modifierPersonne(Personne p) {

		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.merge(p);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
		return p.getId();
	}

	@Override
	public Personne affichagePersonne(int idPersonne) {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Personne p = new Personne();
		
		try {
			//1-recuperer personne
			p = em.find(Personne.class, idPersonne);
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Personne> findAllPersonnes() {

		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Personne> l = new ArrayList<Personne>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT p FROM Personne p"); 
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l; //ResultList au lieu du result set
	}

	@Override
	public List<Personne> rechercheParMC(String mc) {
		
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Personne> l = new ArrayList<Personne>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT p FROM Personne p where nom like :lenom "); // les deux points fonctionnent comme point d'interrogation
			q.setParameter("lenom", "%"+mc+"%");
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l; 
	}

	@Override
	public int ajouterAdresse(Adresse a) {
		//ouverture de l'unité de persistance
				emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
				em = emf.createEntityManager();
				EntityTransaction tx = em.getTransaction();
				
				try {
					//1- débuter la transaction
					tx.begin();
					//2-effectuer la requete
					em.persist(a);
					//3-valider la transaction
					tx.commit();
					//4-fermer l'unité de persistance
					em.close();
					emf.close();
				} catch (Exception e) {
					//annuler la transaction si le persist ne se passe pas bien
					tx.rollback();
					
				}
				
				return a.getIdAdresse();
	}

	@Override
	public Adresse getAdresse(int idAdresse) {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Adresse a = new Adresse();
		
		try {
			//1-recuperer personne
			a = em.getReference(Adresse.class, idAdresse);
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Adresse affichageAdresse(int idAdresse) {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Adresse a = new Adresse();
		
		try {
			//1-recuperer personne
			a = em.find(Adresse.class, idAdresse);
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;	
		}

	@Override
	public int supprimerAdresse(Adresse a) {
		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.remove(a);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
				
		return a.getIdAdresse();
	}

	@Override
	public int modifierAdresse(Adresse a) {
		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.merge(a);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
		return a.getIdAdresse();
	}

	@Override
	public List<Adresse> findAllAdresses() {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Adresse> l = new ArrayList<Adresse>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT a FROM Adresse a"); 
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l; //ResultList au lieu du result set
	}

	@Override
	public List<Adresse> rechercheAdresseParMC(String mc) {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Adresse> l = new ArrayList<Adresse>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT a FROM Adresse a where cp like :leCp "); // les deux points fonctionnent comme point d'interrogation
			q.setParameter("leCp", mc+"%");
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l; 
	}

	@Override
	public int ajouterConnexion(Connexion c) {
		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.persist(c);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
		
		return c.getIdConnexion();
	}

	@Override
	public Connexion getConnexion(int idConnexion) {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Connexion c= new Connexion();
		
		try {
			//1-recuperer personne
			c = em.getReference(Connexion.class, idConnexion);
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Connexion affichageConnexion(int idConnexion) {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Connexion c = new Connexion();
		
		try {
			//1-recuperer personne
			c = em.find(Connexion.class, idConnexion);
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public int supprimerConnexion(Connexion c) {
		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.remove(c);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
				
		return c.getIdConnexion();
	}

	@Override
	public int modifierConnexion(Connexion c) {
		//ouverture de l'unité de persistance
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); // demojpa-pu est le nom de la persistence-unit
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			//1- débuter la transaction
			tx.begin();
			//2-effectuer la requete
			em.merge(c);
			//3-valider la transaction
			tx.commit();
			//4-fermer l'unité de persistance
			em.close();
			emf.close();
		} catch (Exception e) {
			//annuler la transaction si le persist ne se passe pas bien
			tx.rollback();
			
		}
		return c.getIdConnexion();
	}

	@Override
	public List<Connexion> findAllConnexions() {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Connexion> l = new ArrayList<Connexion>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT c FROM Connexion c"); 
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Connexion> rechercheConnexionParMC(String mc) {
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Connexion> l = new ArrayList<Connexion>();
	
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT c FROM Connexion c where login like :lelogin "); // les deux points fonctionnent comme point d'interrogation
			q.setParameter("lelogin", "%"+mc+"%");
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l; 
	}

	public List<Personne> findAllPersonnesConnexion(){
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Personne> l = new ArrayList<Personne>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT p FROM Personne p join p.connexion");  // connexion viens de la methode private Connexion connexion dans la classe Personne
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<Personne> findAllPersonnesAdresse(){
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Personne> l = new ArrayList<Personne>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT p FROM Personne p right join p.adresse");  // connexion viens de la methode private Connexion connexion dans la classe Personne
			l= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<Adresse> findAllPersonneAdresses(){
		emf = Persistence.createEntityManagerFactory("demojpa-pu"); 
		em = emf.createEntityManager();
		Query q = null; //query est une requete
		List<Adresse> a = new ArrayList<Adresse>();
		
		try {
			//1-recuperer personne
			q = em.createQuery("SELECT p FROM Personne p right join p.adresse");  // connexion viens de la methode private Connexion connexion dans la classe Personne
			a= q.getResultList();
			//2-fermer
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

}
