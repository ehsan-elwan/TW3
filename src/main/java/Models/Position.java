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
public class Position {

    private final String post_intitule;
    private final Date post_start_date;
    private final Date post_end_date;
    private final String post_desc;
    private final String post_speciality;
    private int post_st_id;
    private int post_ent_id;

    public Position(String post_intitule, Date post_start_date,
            Date post_end_date, String post_desc, String post_speciality,
            int post_st_id, int post_ent_id) {

        this.post_desc = post_desc;
        this.post_end_date = post_end_date;
        this.post_ent_id = post_ent_id;
        this.post_intitule = post_intitule;
        this.post_speciality = post_speciality;
        this.post_st_id = post_st_id;
        this.post_start_date = post_start_date;
    }

    public String getPost_intitule() {
        return post_intitule;
    }

    public Date getPost_start_date() {
        return post_start_date;
    }

    public Date getPost_end_date() {
        return post_end_date;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public String getPost_speciality() {
        return post_speciality;
    }

    public int getPost_st_id() {
        return post_st_id;
    }

    public int getPost_ent_id() {
        return post_ent_id;
    }

}
