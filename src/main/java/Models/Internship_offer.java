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
public class Internship_offer {

    private final int inter_id;
    private final String inter_name;
    private final Date inter_start_date;
    private final Date inter_end_date;

    public Internship_offer(int inter_id, String inter_name, Date inter_start_date,
            Date inter_end_date) {
        this.inter_end_date = inter_end_date;
        this.inter_id = inter_id;
        this.inter_name = inter_name;
        this.inter_start_date = inter_start_date;

    }

    public int getInter_id() {
        return inter_id;
    }

    public String getInter_name() {
        return inter_name;
    }

    public Date getInter_start_date() {
        return inter_start_date;
    }

    public Date getInter_end_date() {
        return inter_end_date;
    }
}
