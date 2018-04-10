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

    private final int sch_id;
    private final String sch_name;
    private final String sigle;
    private final String posteCode;
    private final String sch_city;
    private final String sch_country;
    private final int sch_id_region;

    public School(int sch_id, String sch_name, String sigle,
            String posteCode,String sch_city, String sch_country, int sch_id_region) {

        this.sch_country = sch_country;
        this.sch_id = sch_id;
        this.sch_id_region = sch_id_region;
        this.sch_name = sch_name;
        this.posteCode = posteCode;
        this.sigle = sigle;
        this.sch_city =sch_city;

    }

    public int getEst_id() {
        return sch_id;
    }

    public String getEst_name() {
        return sch_name;
    }

    public String getSigle() {
        return sigle;
    }

    public String getPosteCode() {
        return posteCode;
    }
    
    public String getEst_city() {
        return sch_city;
    }

    public String getEst_country() {
        return sch_country;
    }

    public int getEst_id_region() {
        return sch_id_region;
    }
    

}
