/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.controller;

import com.asecna.utils.Json;
import com.test.model.Utilisateur;
import com.test.repository.UtilisateurRepository;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository user;

//    @ExceptionHandler(Exception.class)
    @GetMapping(value = "", headers = "Accept=application/json")
    public Json get(HttpServletResponse res) {
        Json json = null;
        try {
            List<Utilisateur> st = user.findAll();
            json = new Json(Json.SUCCESS, st, "");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, null, e.getMessage());
        }
        return json;
    }
    
    @PostMapping(value = "/login", headers = "Accept=application/json")
    public Json login(@RequestBody Utilisateur usr, HttpServletResponse res) {
        Json json = null;
        try {
            String email = usr.getLogin(), mdp = usr.getMdp();
            List<Utilisateur> uts = user.findByLogin(email);
            Utilisateur u = null;
            if (uts.size() > 0) {
                if (uts.get(0).getMdp().compareTo(Utilisateur.md5(mdp)) != 0) {
                    throw new Exception("Email ou mot de passe incorrect");
                }
                u = uts.get(0);
            } else {
                throw new Exception("Email ou mot de passe incorrect");
            }
            u.setMdp("");
            json = new Json(Json.SUCCESS, u, "");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, e.toString(), e.getMessage());
        }
        return json;
    }

    @PostMapping(value = "/signin", headers = "Accept=application/json")
    public Json signin(@RequestBody Utilisateur u, HttpServletResponse res) {
        Json json = null;
        try {
            String id = u.save(user);
            json = new Json(Json.SUCCESS, id, "");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, e.getMessage(), e.getMessage());
        }
        return json;
    }

    @PutMapping(value = "/{id}/edit", headers = "Accept=application/json")
    public Json edit(@RequestBody Utilisateur ui, @PathVariable String id, HttpServletResponse res) {
        Json json = null;
        try {
            Optional<Utilisateur> opt_u = user.findById(id);
            Utilisateur u = opt_u.get();
            u.setLogin(ui.getId());
            u.setNom(ui.getNom());
            user.save(u);
            json = new Json(Json.SUCCESS, u, "");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, null, e.getMessage());
        }
        return json;
    }
    
}
