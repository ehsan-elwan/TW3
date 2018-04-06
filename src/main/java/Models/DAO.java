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
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<School> getEstablishment() {
        List<School> result = new LinkedList<>();
        School sch;
        String sql = "SELECT distinct * FROM SchoolName";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int id = rs.getInt("id_SchoolName");
                    String lname = rs.getString("nom");
                    String sigle = rs.getString("sigle");
                    String postalCode = rs.getString("codePostal");
                    String city = rs.getString("ville");
                    String country = rs.getString("pays");
                    int id_region = rs.getInt("id_region");
                    sch = new School(id, lname, sigle,
                            postalCode, city, country, id_region);

                    result.add(sch);
                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Student> getStudentByEstablishment(String SchoolName) {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT Distinct * FROM ancien_etudiant, a_effectue, "
                + "SchoolName, formation WHERE "
                + "ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + "a_effectue.id_formation = formation.id_formation AND "
                + "SchoolName.nom = ? AND "
                + "SchoolName.id_SchoolName=formation.id_SchoolName";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, SchoolName);
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
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<School> getSchoolByFormation(String formationLabel) {
        List<School> result = new LinkedList<>();
        School sch;
        String sql = "SELECT Distinct * FROM SchoolName, formation "
                + "WHERE SchoolName.id_SchoolName=formation.id_SchoolName "
                + "AND formation.intitule=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, formationLabel);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int sch_id = rs.getInt("id_SchoolName");
                    String sch_name = rs.getString("nom");
                    String sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    int sch_id_region = rs.getInt("id_region");

                    sch = new School(sch_id, sch_name, sigle, posteCode,
                            sch_city, sch_country, sch_id_region);

                }

            }
            stmt.close();
            connection.close();

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
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<School> getSchoolByCity(String cityName) {
        List<School> result = new LinkedList<>();
        School sch;
        String sql = "SELECT * FROM SchoolName WHERE ville =?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cityName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int sch_id = rs.getInt("id_SchoolName");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String sch_posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    int sch_id_region = rs.getInt("id_region");

                    sch = new School(sch_id, sch_name, sch_sigle, sch_posteCode,
                            sch_city, sch_country, sch_id_region);

                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<String> getCitiesFromSchool() {
        List<String> result = new LinkedList<>();
        String sql = "SELECT Distinct ville, pays FROM SchoolName order by ville asc";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String city = rs.getString("ville");
                    String country = rs.getString("pays");
                    result.add(city + ", " + country);
                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Formation> getFormationByCity(String cityName) {
        List<Formation> result = new LinkedList<>();
        Formation form;
        School sch;
        String sql = "SELECT Distinct * FROM  formation ,SchoolName "
                + "WHERE formation.id_SchoolName = SchoolName.id_SchoolName "
                + "AND SchoolName.ville=?";
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

                    int sch_id = rs.getInt("id_SchoolName");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String sch_posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    int sch_id_region = rs.getInt("id_region");

                    sch = new School(sch_id, sch_name, sch_sigle, sch_posteCode,
                            sch_city, sch_country, sch_id_region);

                    form = new Formation(for_id, for_intitule, sigle, for_type,
                            for_speciality, sch);
                    result.add(form);

                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Formation> getFormationsLikeLabel(String form_lab) {
        List<Formation> result = new LinkedList<>();
        Formation form;
        School sch;
        String sql = "SELECT Distinct * FROM  formation ,SchoolName "
                + "WHERE formation.id_SchoolName = SchoolName.id_SchoolName "
                + "AND formation.intitule LIKE ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + form_lab + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int for_id = rs.getInt("id_formation");
                    String for_intitule = rs.getString("intitule");
                    String sigle = rs.getString("sigle");
                    String for_type = rs.getString("type");
                    String for_speciality = rs.getString("specialite");

                    int sch_id = rs.getInt("id_SchoolName");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String sch_posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    int sch_id_region = rs.getInt("id_region");

                    sch = new School(sch_id, sch_name, sch_sigle, sch_posteCode,
                            sch_city, sch_country, sch_id_region);

                    form = new Formation(for_id, for_intitule, sigle, for_type,
                            for_speciality, sch);
                    result.add(form);
                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<String> getCityByFormation(String form_label) {
        List<String> result = new LinkedList<>();
        String sql = "SELECT Distinct SchoolName.ville FROM formation ,SchoolName "
                + "WHERE formation.id_SchoolName = SchoolName.id_SchoolName "
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
            stmt.close();
            connection.close();
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
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int getNBOfStudentWithoutJobBySchool(String SchoolName) {
        int result = 0;
        String sql = "SELECT formation.sigle , COUNT(*) AS withjob FROM "
                + " ancien_etudiant, a_effectue, etablissement,formation "
                + " WHERE ancien_etudiant.id_etud NOT IN ( SELECT distinct poste.id_etud FROM "
                + " poste, etablissement, a_effectue, ancien_etudiant, formation WHERE "
                + " ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + " a_effectue.id_formation = formation.id_formation "
                + " AND etablissement.id_etablissement=formation.id_etablissement "
                + " AND poste.fin<CURDATE() AND etablissement.nom=?) "
                + " AND ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + " a_effectue.id_formation = formation.id_formation "
                + " AND etablissement.id_etablissement=formation.id_etablissement"
                + " GROUP BY formation.sigle";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, SchoolName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result = rs.getInt("withOutJob");
                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public int getNBOfStudentMaster(String SchoolName) {
        int result = 0;
        String sql = "SELECT formation.sigle , COUNT(*) AS withjob FROM "
                + " ancien_etudiant, a_effectue, etablissement,formation "
                + " WHERE ancien_etudiant.id_etud IN ( SELECT distinct poste.id_etud FROM "
                + " poste, etablissement, a_effectue, ancien_etudiant, formation WHERE "
                + " ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + " a_effectue.id_formation = formation.id_formation "
                + " AND etablissement.id_etablissement=formation.id_etablissement "
                + " AND poste.fin<CURDATE() AND etablissement.nom=?) "
                + " AND ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + " a_effectue.id_formation = formation.id_formation "
                + " AND etablissement.id_etablissement=formation.id_etablissement"
                + " GROUP BY formation.sigle";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, SchoolName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result = rs.getInt("withjob");
                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
