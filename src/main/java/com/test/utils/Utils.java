/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asecna.utils;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Andy
 */
public class Utils {
    public static Date removeDays(Date d, int days) {
        LocalDate ld = d.toLocalDate().minusDays(days);
        Date removed = Date.valueOf(ld);
        
        return removed;
    }
    
}
