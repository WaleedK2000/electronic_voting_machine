package com.quizz.evm.ballot;

import com.quizz.evm.Database;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class Database_Menu {

    public static int newElection(String election_name) throws SQLException {
        String sql = "INSERT INTO election (name, status) VALUES (?, true)";
        Connection con = Database.mySQLConnection();
        assert con != null;
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, election_name);
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        int ret;
        if (resultSet.next())
            ret = resultSet.getInt(1);
        else
            ret = -1;

        con.close();
        return ret;
    }

    public static ArrayList<String> getPartyList() throws SQLException {
        ArrayList<String> checkbox = new ArrayList<>();
        String sql = "SELECT party.id FROM party";

        Connection con = Database.mySQLConnection();
        assert con != null;
        PreparedStatement statement = con.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            String s_id = resultSet.getString("id");
            checkbox.add(s_id);


        }
        con.close();
        //ret.append(resultSet.getString(1)).append(" ").append(resultSet.getString(2));
        return checkbox;
    }

    public static void insertCandidate(int election, String first, String last, String cnic, String party) throws SQLException {
        //String sql = "INSERT INTO candidate "

        Connection con = Database.mySQLConnection();
        assert con != null;
        if (!hasCandidate(cnic, con)) {
            //insert candidate
            insertCandidate(first, last, cnic, party, con);
        }

        insertIntoElection(election, cnic, con);

        con.close();

    }

    private static void insertCandidate(String first, String last, String cnic, String party, Connection con) throws SQLException {
        String sql = "INSERT INTO candidate (cnic, party_id, first_name, last_name) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cnic);
        ps.setString(2, party);
        ps.setString(3, first);
        ps.setString(4, last);

        ps.execute();

    }

    private static void insertIntoElection(int election, String cnic, Connection con) throws SQLException {
        String sql = "INSERT INTO candidate_election (election_id, cnic) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, election);
        ps.setString(2, cnic);
        ps.execute();

    }

    private static boolean hasCandidate(String cnic, Connection con) throws SQLException {
        String sql = "SELECT 1 FROM candidate WHERE cnic = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cnic);
        ResultSet resultSet = ps.executeQuery();

        return resultSet.next();
    }

    public static ArrayList<String> searchCNIC(String cnic) throws SQLException {
        Connection con = Database.mySQLConnection();
        String sql = "SELECT party_id, first_name, last_name " +
                "FROM candidate " +
                "WHERE cnic = ? ";

        assert con != null;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,cnic);
        ResultSet resultSet = ps.executeQuery();

        ArrayList<String> ed;

        if(resultSet.next()) {
            ed = new ArrayList<>();

            ed.add(resultSet.getString("first_name"));
            ed.add(resultSet.getString("last_name"));
            ed.add(resultSet.getString("party_id"));
            return ed;
        }
        else {
            return null;
        }
    }

    public static void insertVoter(String first, String last, String cnic, int election_id) throws SQLException {
        Connection con = Database.mySQLConnection();
        String sql = "INSERT INTO voter (first_name, last_name, cnic, election_id) VALUES (?, ?, ?, ?)";


            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,first);
            ps.setString(2,last);
            ps.setString(3,cnic);
            ps.setInt(4, election_id);
            ps.execute();



    }

    public static void insertParty(String name, String id) throws SQLException {
        Connection con = Database.mySQLConnection();
        String sql = "INSERT INTO party (id,name) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.execute();

    }

    public static void endVote(int ids) throws SQLException {

        System.out.println(ids + " - ----");
        String sql = "UPDATE election SET status = 0 WHERE id = ?";

        Connection con = Database.mySQLConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,ids);
        ps.executeUpdate();

    }

    public static int checkIncompleteElection() throws SQLException {

        String sql = "SELECT id FROM election WHERE status = 1";
        Connection con = Database.mySQLConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            return rs.getInt(1);
        }
        else {
            return -1;
        }

    }

    public static ObservableList<Res_Candidate> demoResultTable(){
        ObservableList<Res_Candidate> candidates = FXCollections.observableArrayList();

            candidates.add(new Res_Candidate("ABC", "CDDD", 28));
        candidates.add(new Res_Candidate("CCC", "CDdddDD", 21));


        return candidates;
    }



    public static ObservableList<Res_Candidate> loadResult(int id) throws SQLException {

        System.out.println("idhr");
        String sql = "SELECT candidate.first_name, candidate.last_name, candidate.party_id, COUNT(vote.candidate_cnic) AS 'Votes', candidate.cnic, candidate_election.election_id\n" +
                "FROM candidate " +
                "inner JOIN candidate_election " +
                "ON candidate.cnic = candidate_election.cnic " +
                " LEFT JOIN vote " +
                " ON candidate.cnic = vote.candidate_cnic  " +
                "WHERE candidate_election.election_id = ? " +
                "GROUP BY candidate.cnic " +
                "ORDER BY COUNT(vote.candidate_cnic) desc";

        Connection con = Database.mySQLConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ObservableList<Res_Candidate> candidates = FXCollections.observableArrayList();


        while(rs.next()){
           // SimpleStringProperty name = rs.getString(1) + rs.getString(2);

            SimpleStringProperty name = new SimpleStringProperty();
            name.set(rs.getString(1) + " " + rs.getString(2));

            SimpleIntegerProperty votes = new SimpleIntegerProperty();
            votes.set(rs.getInt(4));

            SimpleStringProperty party = new SimpleStringProperty();
            party.set(rs.getString(3));

            candidates.add(new Res_Candidate(  name, party, votes  ));
        }
        return candidates;
    }



}
