/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.google.gson.JsonObject;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ehsan
 */
public class DAO {

    private final MysqlDataSource myDataSource;

    public DAO(MysqlDataSource dataSource) {
        myDataSource = new DataSource().getMySQLDataSource();
    }

    public List<Student> getStudentsLikeName(String lname) {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT distinct * FROM ancien_etudiant WHERE "
                + "ancien_etudiant.nom LIKE ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + lname + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int id = rs.getInt("id_etud");
                    lname = rs.getString("nom");
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

    public List<School> getSchools() {
        List<School> result = new LinkedList<>();
        School sch;
        String sql = "SELECT distinct * FROM etablissement";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int sch_id = rs.getInt("id_etablissement");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    float lat = rs.getFloat("lat");
                    float lng = rs.getFloat("lng");
                    int id_region = rs.getInt("id_region");
                    sch = new School(sch_id, sch_name, sch_sigle,
                            posteCode, sch_city, sch_country, lat, lng, id_region);

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

    public List<Student> getStudentBySchool(int sch_id) {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT Distinct * FROM ancien_etudiant, a_effectue,"
                + " etablissement, formation WHERE "
                + "etablissement.id_etablissement = ? AND "
                + "ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + "a_effectue.id_formation = formation.id_formation AND "
                + "etablissement.id_etablissement=formation.id_etablissement";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sch_id);
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

                    int sch_id = rs.getInt("id_etablissement");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    float lat = rs.getFloat("lat");
                    float lng = rs.getFloat("lng");
                    int id_region = rs.getInt("id_region");
                    sch = new School(sch_id, sch_name, sch_sigle,
                            posteCode, sch_city, sch_country, lat, lng, id_region);

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

    public List<Student> getStudentByFormationid(int form_id) {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT Distinct * FROM ancien_etudiant, a_effectue, formation "
                + "WHERE ancien_etudiant.id_etud=a_effectue.id_etud "
                + "AND a_effectue.id_formation = formation.id_formation AND "
                + "formation.id_formation = ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, form_id);
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
    
    public List<Student> getStudentByPromo(Date date) {
        List<Student> result = new LinkedList<>();
        Student st;
        String sql = "SELECT Distinct * FROM ancien_etudiant WHERE "
                + "ancien_etudiant.promotion = ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, date);
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

                    int sch_id = rs.getInt("id_etablissement");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    float lat = rs.getFloat("lat");
                    float lng = rs.getFloat("lng");
                    int id_region = rs.getInt("id_region");
                    sch = new School(sch_id, sch_name, sch_sigle,
                            posteCode, sch_city, sch_country, lat, lng, id_region);

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

    public List<String> getCitiesFromSchool() {
        List<String> result = new LinkedList<>();
        String sql = "SELECT Distinct ville, pays FROM etablissement order by ville asc";
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
        String sql = "SELECT Distinct * FROM  formation ,etablissement "
                + "WHERE formation.id_etablissement = etablissement.id_etablissement "
                + "AND upper(etablissement.ville)=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cityName.toUpperCase());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int for_id = rs.getInt("id_formation");
                    String for_intitule = rs.getString("intitule");
                    String sigle = rs.getString("sigle");
                    String for_type = rs.getString("type");
                    String for_speciality = rs.getString("specialite");

                    int sch_id = rs.getInt("id_etablissement");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    float lat = rs.getFloat("lat");
                    float lng = rs.getFloat("lng");
                    int id_region = rs.getInt("id_region");
                    sch = new School(sch_id, sch_name, sch_sigle,
                            posteCode, sch_city, sch_country, lat, lng, id_region);

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
        String sql = "SELECT Distinct * FROM  formation ,etablissement "
                + "WHERE formation.id_etablissement = etablissement.id_etablissement "
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

                    int sch_id = rs.getInt("id_etablissement");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    float lat = rs.getFloat("lat");
                    float lng = rs.getFloat("lng");
                    int id_region = rs.getInt("id_region");
                    sch = new School(sch_id, sch_name, sch_sigle,
                            posteCode, sch_city, sch_country, lat, lng, id_region);

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

    public List<Formation> getAllFormations() {
        List<Formation> result = new LinkedList<>();
        Formation form;
        School sch;
        String sql = "SELECT Distinct * FROM  formation ,etablissement "
                + "WHERE formation.id_etablissement = etablissement.id_etablissement ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int for_id = rs.getInt("id_formation");
                    String for_intitule = rs.getString("intitule");
                    String sigle = rs.getString("sigle");
                    String for_type = rs.getString("type");
                    String for_speciality = rs.getString("specialite");

                    int sch_id = rs.getInt("id_etablissement");
                    String sch_name = rs.getString("nom");
                    String sch_sigle = rs.getString("sigle");
                    String posteCode = rs.getString("codePostal");
                    String sch_city = rs.getString("ville");
                    String sch_country = rs.getString("pays");
                    float lat = rs.getFloat("lat");
                    float lng = rs.getFloat("lng");
                    int id_region = rs.getInt("id_region");
                    sch = new School(sch_id, sch_name, sch_sigle,
                            posteCode, sch_city, sch_country, lat, lng, id_region);

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
        String sql = "SELECT Distinct SchoolName.ville FROM formation ,etablissement "
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

    public JSONObject getNBOfStudentoutOfJob() {
        JSONObject json = new JSONObject();
        HashMap<String, Integer> studentoutOfJob = new HashMap<>();
        String sql = "SELECT formation.sigle, COUNT(*) AS withoutjob "
                + "FROM ancien_etudiant, a_effectue, formation WHERE "
                + "ancien_etudiant.id_etud NOT IN (SELECT distinct poste.id_etud "
                + "FROM poste, ancien_etudiant,a_effectue,formation, etablissement "
                + "WHERE poste.id_etud = ancien_etudiant.id_etud "
                + "AND ancien_etudiant.id_etud=a_effectue.id_etud "
                + "AND a_effectue.id_formation = formation.id_formation "
                + "AND etablissement.id_etablissement=formation.id_etablissement "
                + "AND poste.fin is null or poste.fin> CURDATE()) "
                + "AND formation.id_formation=a_effectue.id_formation "
                + "AND ancien_etudiant.id_etud = a_effectue.id_etud AND "
                + "a_effectue.fin_formation is NOT NULL "
                + "or a_effectue.fin_formation>CURDATE() GROUP BY formation.sigle";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int nb = rs.getInt("withoutjob");
                    String sig = rs.getString("sigle");
                    studentoutOfJob.put(sig, nb);
                }
                HashMap<String, Integer> joinmap = new HashMap();
                HashMap<String, Integer> studentWithJob = getNBOfStudentWithJob();
                HashMap<String, Integer> studentinMaster = getNBOfStudentMaster();
                joinmap.putAll(studentWithJob);
                joinmap.putAll(studentoutOfJob);
                joinmap.putAll(studentinMaster);
                for (Map.Entry<String, Integer> entry : joinmap.entrySet()) {
                    JSONArray array = new JSONArray();
                    JSONObject item = new JSONObject();
                    Integer nbnojob = studentoutOfJob.get(entry.getKey());
                    Integer nbjob = studentWithJob.get(entry.getKey());
                    Integer nbsch = studentinMaster.get(entry.getKey());
                    if (nbnojob == null) {
                        nbnojob = 0;
                    }
                    if (nbjob == null) {
                        nbjob = 0;
                    }
                    if (nbsch == null) {
                        nbsch = 0;
                    }
                    item.put("withoutjob", nbnojob);
                    item.put("withjob", nbjob);
                    item.put("insch", nbsch);
                    array.put(item);
                    json.put(entry.getKey(), array);
                }

            }
            stmt.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }

    public HashMap<String, Integer> getNBOfStudentWithJob() {
        HashMap<String, Integer> hMap = new HashMap<>();
        String sql = "SELECT formation.sigle, COUNT(*) AS withjob  "
                + "FROM ancien_etudiant, a_effectue, formation WHERE "
                + "ancien_etudiant.id_etud IN (SELECT distinct poste.id_etud "
                + "FROM poste, ancien_etudiant,a_effectue,formation, etablissement "
                + "WHERE poste.id_etud = ancien_etudiant.id_etud "
                + "AND ancien_etudiant.id_etud=a_effectue.id_etud "
                + "AND a_effectue.id_formation = formation.id_formation "
                + "AND etablissement.id_etablissement=formation.id_etablissement "
                + "AND poste.fin is null or poste.fin> CURDATE()) "
                + "AND formation.id_formation=a_effectue.id_formation "
                + "AND ancien_etudiant.id_etud = a_effectue.id_etud "
                + "GROUP BY formation.sigle";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int nb = rs.getInt("withjob");
                    String sig = rs.getString("sigle");
                    hMap.put(sig, nb);
                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hMap;
    }

    public HashMap<String, Integer> getNBOfStudentMaster() {
        HashMap<String, Integer> hMap = new HashMap<>();
        String sql = "SELECT formation.sigle, COUNT(*) AS insch FROM "
                + "ancien_etudiant, a_effectue, formation WHERE "
                + "ancien_etudiant.id_etud IN (SELECT "
                + "distinct a_effectue.id_etud FROM ancien_etudiant,a_effectue,formation WHERE "
                + "ancien_etudiant.id_etud=a_effectue.id_etud AND "
                + "a_effectue.id_formation = formation.id_formation AND "
                + "a_effectue.fin_formation is null or a_effectue.fin_formation > CURDATE()) "
                + "AND formation.id_formation=a_effectue.id_formation AND "
                + "ancien_etudiant.id_etud = a_effectue.id_etud GROUP BY formation.sigle";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int nb = rs.getInt("insch");
                    String sig = rs.getString("sigle");
                    hMap.put(sig, nb);
                }

            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hMap;
    }

    public JSONObject getAVGByFormation() {
        JSONObject mainObj = new JSONObject();
        Calendar cal = Calendar.getInstance();
        String sql = "SELECT formation.sigle, AVG(a_effectue.moyenne_M) "
                + "AS avg,a_effectue.debut_formation as yf FROM "
                + "a_effectue,formation where "
                + "a_effectue.id_formation=formation.id_formation "
                + "GROUP BY yf,formation.sigle Order by yf";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                Date date;
                JSONArray ja = new JSONArray();
                JSONObject jo = new JSONObject();
                rs.first();
                date = rs.getDate("yf");
                Integer nb = rs.getInt("avg");
                String sig = rs.getString("sigle");
                jo.put("sigle", sig);
                jo.put("avg", nb);
                ja.put(jo);

                while (rs.next()) {
                    jo = new JSONObject();
                    if (date.equals(rs.getDate("yf"))) {
                        nb = rs.getInt("avg");
                        if (nb != null && nb > 1) {
                            sig = rs.getString("sigle");
                            jo.put("sigle", sig);
                            jo.put("avg", nb);
                            ja.put(jo);

                        }

                    } else {
                        cal.setTime(date);
                        mainObj.put(String.valueOf(cal.get(Calendar.YEAR)), ja);
                        ja = new JSONArray();
                        date = rs.getDate("yf");
                        nb = rs.getInt("avg");
                        sig = rs.getString("sigle");
                        jo.put("sigle", sig);
                        jo.put("avg", nb);
                        ja.put(jo);
                    }

                }
            }

            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mainObj;
    }

    public Student login(String mail, int pass) throws SQLException {
        Student result = null;
        String sql = "SELECT * FROM ancien_etudiant WHERE mail=? AND id_etud=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, mail);
            stmt.setInt(2, pass);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String lname = rs.getString("nom");
                    String fname = rs.getString("prenom");
                    Date promo = rs.getDate("promotion");
                    String spec = rs.getString("specialite");
                    String cursus = rs.getString("cursus");
                    int avgID = rs.getInt("moyenne_l3_id");
                    result = new Student(pass, fname, lname,
                            mail, promo, spec, cursus, avgID);
                }
            }
        }
        return result;
    }

    public JsonObject addFormation(String title, String sigle,
            String type, String spec, int sch_id) {
        JsonObject mainObj = new JsonObject();
        String msg = "";
        int msg_code = 0;
        String sql = "INSERT INTO formation "
                + "(intitule,sigle ,type,specialite,id_etablissement) "
                + "values (?,?,?,?,?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, sigle);
            stmt.setString(3, type);
            stmt.setString(4, spec);
            stmt.setInt(5, sch_id);
            int count = stmt.executeUpdate();
            if (count > 0) {
                msg = "Formation ajouter avec success";
            } else {
                msg = "Formation n'est pas ajouter, cause: ";
            }

        } catch (SQLException ex) {
            msg += ex.getMessage();
            msg_code = -1;
        }
        mainObj.addProperty("msg", msg);
        mainObj.addProperty("code", msg_code);
        return mainObj;
    }
    
    
}
