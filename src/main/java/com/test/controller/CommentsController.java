/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.controller;

import com.asecna.utils.Json;
import com.test.Model.Comments;
import com.test.repository.CommentsRepository;
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
@RequestMapping("/comment")
public class CommentsController {
    
    @Autowired
    UtilisateurRepository repo;
    
    @Autowired
    CommentsRepository coms;
    
    @PostMapping(value = "/{id}", headers = "Accept=application/json")
    public Json get(@PathVariable String id, HttpServletResponse res) {
        Json json = null;
        try {
            List<Comments> st = coms.findByVoiture(id);
            json = new Json(Json.SUCCESS, st, "");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, null, e.getMessage());
        }
        return json;
    }
    
    @PostMapping(value = "/add", headers = "Accept=application/json")
    public Json add(@RequestBody Comments cmts, HttpServletResponse res) {
        Json json = null;
        try {
            String id = cmts.save(repo, coms);
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
    public Json edit(@RequestBody Comments c, @PathVariable String id) {
        Json json = null;
        try {
            Optional<Comments> old = coms.findById(id);
            Comments new_com = old.get();
            new_com.setValue(c.getValue());
            coms.save(new_com);
            json = new Json(Json.SUCCESS, new_com, "");
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
            Optional<Comments> old = coms.findById(id);
            Comments com = old.get();
            coms.delete(com);
            json = new Json(Json.SUCCESS, id, "Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json = new Json(Json.ERROR, null, e.getMessage());
        }
        
        return json;
    }
}
