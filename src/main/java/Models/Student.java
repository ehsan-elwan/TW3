/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Ehsan
 */
public class Student {
    private final int st_id;
    private final String st_fname;
    private final String st_lname;
    private final String st_email;
    private final Date st_promotion;
    private final String st_spec;
    private final String st_cursus;
    private final int st_L3average;
    
    public Student(int id, String fname, String lname,
            String email, Date promotion, String spec,
            String cursus, int L3average){
        
        this.st_L3average=L3average;
        this.st_cursus=cursus;
        this.st_email=email;
        this.st_fname=fname;
        this.st_id=id;
        this.st_promotion=promotion;
        this.st_spec = spec;
        this.st_lname = lname;
        
    }

    public int getId() {
        return st_id;
    }

    public String getFname() {
        return st_fname;
    }

    public String getLname() {
        return st_lname;
    }

    public String getEmail() {
        return st_email;
    }

    public Date getPromotion() {
        return st_promotion;
    }

    public String getSpec() {
        return st_spec;
    }

    public String getCursus() {
        return st_cursus;
    }

    public int getL3average() {
        return st_L3average;
    }
    
}
