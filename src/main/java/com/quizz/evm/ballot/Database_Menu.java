package com.quizz.evm.ballot;

import com.quizz.evm.Database;

import java.sql.*;
import java.util.ArrayList;

public class Database_Menu {

    public static int newElection(String election_name) throws SQLException {
        String sql = "INSERT INTO election (name) VALUES (?)";
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


}
