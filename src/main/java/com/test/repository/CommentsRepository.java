/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.repository;

import com.test.Model.Comments;
import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Andy
 */
public interface CommentsRepository extends JpaRepository<Comments, String>{
    @Query(value = "SELECT NOW()", nativeQuery = true)
    public Timestamp actualDate();
}
