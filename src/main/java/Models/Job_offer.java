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
public class Job_offer {

    private final int job_id;
    private final String job_poste_name;
    private final Date job_start_date;
    private final Date job_end_date;
    private final String job_type;
    private final String job_desc;

    public Job_offer(int job_id, String job_poste_name, Date job_start_date,
            Date job_end_date, String job_type, String job_desc) {

        this.job_desc = job_desc;
        this.job_end_date = job_end_date;
        this.job_id = job_id;
        this.job_poste_name = job_poste_name;
        this.job_start_date = job_start_date;
        this.job_type = job_type;

    }

    public int getJob_id() {
        return job_id;
    }

    public String getJob_poste_name() {
        return job_poste_name;
    }

    public Date getJob_start_date() {
        return job_start_date;
    }

    public Date getJob_end_date() {
        return job_end_date;
    }

    public String getJob_type() {
        return job_type;
    }

    public String getJob_desc() {
        return job_desc;
    }
}
