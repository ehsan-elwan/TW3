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
public class Enterprise {

    private final int ent_id;
    private final String ent_name;
    private final String ent_address;
    private final String ent_speciality;
    private final String ent_city;
    private final String ent_postalCode;
    private final String ent_country;

    public Enterprise(int ent_id, String ent_name, String ent_address,
            String ent_speciality, String ent_city, String ent_postalCode,
            String ent_country) {

        this.ent_address = ent_address;
        this.ent_city = ent_city;
        this.ent_country = ent_country;
        this.ent_id = ent_id;
        this.ent_name = ent_name;
        this.ent_postalCode = ent_postalCode;
        this.ent_speciality = ent_speciality;

    }

    public int getEnt_id() {
        return ent_id;
    }

    public String getEnt_name() {
        return ent_name;
    }

    public String getEnt_address() {
        return ent_address;
    }

    public String getEnt_speciality() {
        return ent_speciality;
    }

    public String getEnt_city() {
        return ent_city;
    }

    public String getEnt_postalCode() {
        return ent_postalCode;
    }

    public String getEnt_country() {
        return ent_country;
    }

}
