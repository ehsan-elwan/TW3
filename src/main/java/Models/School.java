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
public class School {

    private final int est_id;
    private final String est_name;
    private final String sigle;
    private final String posteCode;
    private final String est_city;
    private final String est_country;
    private final int est_id_region;

    public School(int est_id, String est_name, String sigle,
            String posteCode,String est_city, String est_country, int est_id_region) {

        this.est_country = est_country;
        this.est_id = est_id;
        this.est_id_region = est_id_region;
        this.est_name = est_name;
        this.posteCode = posteCode;
        this.sigle = sigle;
        this.est_city =est_city;

    }

    public int getEst_id() {
        return est_id;
    }

    public String getEst_name() {
        return est_name;
    }

    public String getSigle() {
        return sigle;
    }

    public String getPosteCode() {
        return posteCode;
    }
    
    public String getEst_city() {
        return est_city;
    }

    public String getEst_country() {
        return est_country;
    }

    public int getEst_id_region() {
        return est_id_region;
    }
    

}
