/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.Repository;

import com.test.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andy
 */
public interface VoitureRepository extends JpaRepository<Voiture, String> {
    
}
