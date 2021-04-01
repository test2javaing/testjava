/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.controller;

import com.asecna.utils.Json;
import com.test.Repository.VoitureRepository;
import com.test.model.Voiture;
import com.test.repository.UtilisateurRepository;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andy
 */

@RestController
@RequestMapping("/voiture")
public class VoitureController {
    @Autowired
    UtilisateurRepository repo;
    
    @Autowired
    VoitureRepository vt_repo;
    
    @GetMapping(value = "", headers = "Accept=application/json")
    public Json get(HttpServletResponse res) {
        Json json = null;
        try {
            List<Voiture> st = vt_repo.findAll();
            json = new Json(Json.SUCCESS, st, "");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, null, e.getMessage());
        }
        return json;
    }
    
    @PostMapping(value = "/add", headers = "Accept=application/json")
    public Json add(@RequestBody Voiture vt, HttpServletResponse res) {
        Json json = null;
        try {
            String id = vt.save(repo, vt_repo);
            json = new Json(Json.SUCCESS, id, "");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, e.toString(), e.getMessage());
        }
        return json;
    }
    
    @CrossOrigin
    @PutMapping(value = "/{id}/edit", headers = "Accept=application/json")
    public Json edit(@RequestBody Voiture vt, @PathVariable String id) {
        Json json = null;
        try {
            Optional<Voiture> old = vt_repo.findById(id);
            Voiture new_vt = old.get();
            String nom = (vt.getNom().compareTo("") == 0) ? new_vt.getNom() : vt.getNom();
            double prix = (vt.getPrix() == 0) ? new_vt.getPrix() : vt.getPrix();
            new_vt.setNom(nom);
            new_vt.setPrix(prix);
            vt_repo.save(new_vt);
            json = new Json(Json.SUCCESS, new_vt, "");
        } catch (Exception e) {
            e.printStackTrace();
            json = new Json(Json.ERROR, null, e.getMessage());
        }
        return json;
    }
    
    @CrossOrigin
    @DeleteMapping(value = "/{id}/delete", headers = "Accept=application/json")
    public Json delete(@PathVariable String id, HttpServletResponse res) {
        Json json = null;
        try {
            Optional<Voiture> old = vt_repo.findById(id);
            Voiture com = old.get();
            vt_repo.delete(com);
            json = new Json(Json.SUCCESS, id, "Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, null, e.getMessage());
        }
        
        return json;
    }
}
