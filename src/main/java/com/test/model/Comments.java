/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.Model;

import com.test.repository.CommentsRepository;
import com.test.repository.UtilisateurRepository;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Andy
 */
@Entity
public class Comments {
    @Id
    private String id;
    private String value;
    private String id_usr;
    @Column(name="id_voiture")
    private String voiture;
    private Timestamp date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId_usr() {
        return id_usr;
    }

    public void setId_usr(String id_usr) {
        this.id_usr = id_usr;
    }

    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Comments(String id, String value, String id_usr, String voiture, Timestamp date) {
        this.id = id;
        this.value = value;
        this.id_usr = id_usr;
        this.voiture = voiture;
        this.date = date;
    }

    public Comments() {
    }
    
    public String save(UtilisateurRepository repo, CommentsRepository coms) {
        String id = "CM" + repo.nextVal("comments_seq");
        Timestamp now = coms.actualDate();
        
        this.setId(id);
        this.setDate(now);
        
        return coms.save(this).getId();
    }
    
}
