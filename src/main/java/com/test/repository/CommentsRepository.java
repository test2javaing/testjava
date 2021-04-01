/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.repository;

import com.test.Model.Comments;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Andy
 */
public interface CommentsRepository extends JpaRepository<Comments, String>{
    @Query(value = "SELECT NOW()", nativeQuery = true)
    public Timestamp actualDate();
    
    @Query(value = "SELECT COMMENTS.ID, COMMENTS.VALUE, UTILISATEUR.NOM AS ID_USR, COMMENTS.ID_VOITURE, DATE FROM COMMENTS JOIN "
                        + "UTILISATEUR ON COMMENTS.ID_USR = UTILISATEUR.ID_USR where ID_VOITURE LIKE :id order by Date DESC", nativeQuery = true)
    List<Comments> findByVoiture(@Param("id") String id);
}
