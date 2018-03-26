/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ehsan
 */
public class DAO {

    private final MysqlDataSource myDataSource;

    public DAO(MysqlDataSource dataSource) {
        myDataSource = new DataSource().getMySQLDataSource();
    }

    public List<Student> getStudent() {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT distinct * FROM ancien_etudiant";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int id = rs.getInt("id_etud");
                    String lname = rs.getString("nom");
                    String fname = rs.getString("prenom");
                    String email = rs.getString("mail");
                    Date promotion = rs.getDate("promotion");
                    String spec = rs.getString("specialite");
                    String cursus = rs.getString("cursus");
                    int L3average = rs.getInt("moyenne_l3_id");

                    st = new Student(id, fname, lname,
                            email, promotion, spec, cursus, L3average);
                    result.add(st);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Establishment> getEstablishment() {
        List<Establishment> result = new LinkedList<>();
        Establishment est;
        String sql = "SELECT distinct * FROM etablissement";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int id = rs.getInt("id_etablissement");
                    String lname = rs.getString("nom");
                    String sigle = rs.getString("sigle");
                    String postalCode = rs.getString("codePostal");
                    String city = rs.getString("ville");
                    String country = rs.getString("pays");
                    int id_region = rs.getInt("id_region");
                    est = new Establishment(id, lname, sigle,
                            postalCode, city, country, id_region);
                    result.add(est);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Student> getStudentByEstablishment(String etablissementName) {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT Distinct * FROM ancien_etudiant, a_effectue, "
                + "etablissement, formation WHERE "
                + "ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + "a_effectue.id_formation = formation.id_formation AND "
                + "etablissement.nom = ? AND "
                + "etablissement.id_etablissement=formation.id_etablissement";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, etablissementName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int id = rs.getInt("id_etud");
                    String lname = rs.getString("nom");
                    String fname = rs.getString("prenom");
                    String email = rs.getString("mail");
                    Date promotion = rs.getDate("promotion");
                    String spec = rs.getString("specialite");
                    String cursus = rs.getString("cursus");
                    int L3average = rs.getInt("moyenne_l3_id");

                    st = new Student(id, fname, lname,
                            email, promotion, spec, cursus, L3average);
                    result.add(st);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Establishment> getEtablissementByFormation(String formationLabel) {
        List<Establishment> result = new LinkedList<>();
        Establishment est;
        String sql = "SELECT Distinct * FROM etablissement, formation "
                + "WHERE etablissement.id_etablissement=formation.id_etablissement "
                + "AND formation.intitule=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, formationLabel);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int est_id = rs.getInt("id_etablissement");
                    String est_name = rs.getString("nom");
                    String sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String est_city = rs.getString("ville");
                    String est_country = rs.getString("pays");
                    int est_id_region = rs.getInt("id_region");

                    est = new Establishment(est_id, est_name, sigle, posteCode,
                            est_city, est_country, est_id_region);

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Student> getStudentByFormation(String formationLabel) {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT Distinct * FROM ancien_etudiant, a_effectue, formation "
                + "WHERE ancien_etudiant.id_etud=a_effectue.id_etud "
                + "AND a_effectue.id_formation = formation.id_formation AND "
                + "formation.intitule = ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, formationLabel);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_etud");
                    String lname = rs.getString("nom");
                    String fname = rs.getString("prenom");
                    String email = rs.getString("mail");
                    Date promotion = rs.getDate("promotion");
                    String spec = rs.getString("specialite");
                    String cursus = rs.getString("cursus");
                    int L3average = rs.getInt("moyenne_l3_id");

                    st = new Student(id, fname, lname,
                            email, promotion, spec, cursus, L3average);
                    result.add(st);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Establishment> getEtablissementByCity(String cityName) {
        List<Establishment> result = new LinkedList<>();
        Establishment est;
        String sql = "SELECT * FROM etablissement WHERE ville =?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cityName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int est_id = rs.getInt("id_etablissement");
                    String est_name = rs.getString("nom");
                    String sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String est_city = rs.getString("ville");
                    String est_country = rs.getString("pays");
                    int est_id_region = rs.getInt("id_region");

                    est = new Establishment(est_id, est_name, sigle, posteCode,
                            est_city, est_country, est_id_region);

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<String> getEtablissementByCity() {
        List<String> result = new LinkedList<>();
        String sql = "SELECT Distinct ville FROM etablissement order by ville asc";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String city = rs.getString("ville");
                    result.add(city);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Formation> getFormationByCity(String cityName) {
        List<Formation> result = new LinkedList<>();
        Formation form;
        String sql = "SELECT Distinct * FROM  formation ,etablissement "
                + "WHERE formation.id_etablissement = etablissement.id_etablissement "
                + "AND etablissement.ville=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cityName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int for_id = rs.getInt("id_formation");
                    String for_intitule = rs.getString("intitule");
                    String sigle = rs.getString("sigle");
                    String for_type = rs.getString("type");
                    String for_speciality = rs.getString("specialite");
                    int for_id_est = rs.getInt("id_etablissement");

                    form = new Formation(for_id, for_intitule, sigle, for_type,
                            for_speciality, for_id_est);

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<String> getCityByFormation(String form_label) {
        List<String> result = new LinkedList<>();
        String sql = "SELECT Distinct etablissement.ville FROM formation ,etablissement "
                + "WHERE formation.id_etablissement = etablissement.id_etablissement "
                + "AND formation.intitule=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, form_label);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String city = rs.getString("ville");
                    result.add(city);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int getNBOfStudentByFormation(String form_label) {
        int result = 0;
        String sql = "SELECT Distinct COUNT(*) AS nb FROM formation, a_effectue, "
                + "ancien_etudiant WHERE formation.id_formation = a_effectue.id_formation "
                + "AND ancien_etudiant.id_etud=a_effectue.id_etud "
                + "AND formation.intitule = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, form_label);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result = rs.getInt("nb");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
