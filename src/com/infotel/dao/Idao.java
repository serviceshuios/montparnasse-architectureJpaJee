package com.infotel.dao;

import java.util.List;

import com.infotel.metier.Adresse;
import com.infotel.metier.Connexion;
import com.infotel.metier.Personne;

public interface Idao {

	public int ajouterPersonne(Personne p);
	public Personne getPersonne(int idPersonne);
	public Personne affichagePersonne(int idPersonne);
	public int supprimerPersonne(Personne p);
	public int modifierPersonne(Personne p);
	public List<Personne> findAllPersonnes();
	public List<Personne> rechercheParMC(String mc);
	
	public int ajouterAdresse(Adresse a);
	public Adresse getAdresse(int idAdresse);
	public Adresse affichageAdresse(int idAdresse);
	public int supprimerAdresse(Adresse a);
	public int modifierAdresse(Adresse a);
	public List<Adresse> findAllAdresses();
	public List<Adresse> rechercheAdresseParMC(String mc);
	
	public int ajouterConnexion(Connexion c);
	public Connexion getConnexion(int idConnexion);
	public Connexion affichageConnexion(int idConnexion);
	public int supprimerConnexion(Connexion c);
	public int modifierConnexion(Connexion c);
	public List<Connexion> findAllConnexions();
	public List<Connexion> rechercheConnexionParMC(String mc);
	
	public List<Personne> findAllPersonnesConnexion();
	public List<Personne> findAllPersonnesAdresse();
	public List<Adresse> findAllPersonneAdresses();
}