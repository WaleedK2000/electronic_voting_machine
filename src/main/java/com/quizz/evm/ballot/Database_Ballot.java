package com.quizz.evm.ballot;

import com.quizz.evm.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database_Ballot {
    public static void addVote(int election_id, String cnic) throws SQLException {

        String  sql_query = "INSERT INTO vote( election_id, candidate_cnic) values (? , ?)";
        Connection con = Database.mySQLConnection();
        assert con != null;
        PreparedStatement statement = con.prepareStatement(sql_query);
        statement.setInt(1, election_id);
        statement.setString(2,cnic);
        statement.execute();
        con.close();

    }
    public static ObservableList<Candidate> fetchCandidates(int election_id) throws SQLException {
        String sql_query = "SELECT candidate.first_name, candidate.last_name, candidate.party_id, candidate_election.election_id, candidate.cnic\n" +
                "FROM candidate\n" +
                "INNER JOIN candidate_election\n" +
                "\tON candidate.cnic = candidate_election.cnic\n" +
                "WHERE candidate_election.election_id = ?\n" +
                "ORDER BY candidate.first_name";

        Connection con = Database.mySQLConnection();
        assert con != null;
        PreparedStatement statement = con.prepareStatement(sql_query);
        statement.setInt(1, election_id);
        ResultSet resultSet = statement.executeQuery();

        return getCandidate(resultSet);
    }

    public static ObservableList<Candidate> getCandidate(ResultSet rs) throws SQLException {
        ObservableList<Candidate> candidates = FXCollections.observableArrayList();
        while (rs.next()) {
            String name = rs.getString("first_name") + " " + rs.getString("last_name");
            String party = rs.getString("party_id");
            String cnic = rs.getString("cnic");
            candidates.add(new Candidate(name, party, cnic));
        }
        return candidates;
    }

}
