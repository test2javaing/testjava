/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.model;

import com.test.Repository.VoitureRepository;
import com.test.repository.UtilisateurRepository;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Andy
 */
@Entity
public class Voiture {
    @Id
    private String id;
    private String nom;
    private double prix;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Voiture(String id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Voiture() {
    }

    
    
    public String save(UtilisateurRepository repo, VoitureRepository vt_repo) {
        String id = "VT" + repo.nextVal("voiture_seq");
        this.setId(id);
        
        return vt_repo.save(this).getId();
    }
    
    
}
