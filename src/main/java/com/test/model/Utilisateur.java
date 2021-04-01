/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.model;

import com.test.repository.UtilisateurRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

/**
 *
 * @author Andy
 */
@Entity
public class Utilisateur {
    @Id
    @Column(name = "id_usr")
    private String id;
    @Column(name = "login")
    private String login;
    @Column(name = "mdp")
    private String mdp;
    private String nom;
    

    public String getId() {
        return id;
    }

    public void setId(String id_usr) {
        this.id = id_usr;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Utilisateur(String id, String login, String mdp, String nom) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
    }

    public Utilisateur() {
    }

    @Override
    public String toString() {
        return "Utilisateu{" + "id=" + id + ", login=" + login + ", mdp=" + mdp + ", nom=" + nom + '}';
    }

    

    public static String md5(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }

    @Transactional()
    public String save(UtilisateurRepository repo) throws NoSuchAlgorithmException, Exception {
        String id = "USR" + repo.nextVal("utilisateur_seq");
        this.setId(id);
        this.setMdp(Utilisateur.md5(this.getMdp()));
        
        repo.save(this);
        
        return id;
    }
}
