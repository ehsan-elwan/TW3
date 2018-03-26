/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Ehsan
 */
public class Formation {
    
    private final int for_id;
    private final String for_intitule;
    private final String sigle;
    private final String for_type;
    private final String for_speciality;
    private final int for_id_est;
    
    public Formation (int for_id, String for_intitule, String sigle,
            String for_type, String for_speciality,int for_id_est) {
        
        this.for_id = for_id;
        this.for_intitule = for_intitule;
        this.for_speciality = for_speciality;
        this.for_type =for_type;
        this.sigle =sigle;
        this.for_id_est = for_id_est;
    }

    public int getFor_id_est() {
        return for_id_est;
    }

    public int getFor_id() {
        return for_id;
    }

    public String getFor_intitule() {
        return for_intitule;
    }

    public String getSigle() {
        return sigle;
    }

    public String getFor_type() {
        return for_type;
    }

    public String getFor_speciality() {
        return for_speciality;
    }
    
}
