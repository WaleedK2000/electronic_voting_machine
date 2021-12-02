package com.quizz.evm.ballot;


import com.quizz.evm.HelloApplication;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BallotController implements Initializable {

    boolean vote_complete = false;

    @FXML
    private GridPane blallot_grid;
    private  ObservableList<Candidate> candidates;
    private VoteListener voteListener;
    private int election_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        election_id = 1;

        try {
            can();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        voteListener = new VoteListener() {
            @Override
            public void onClickListener(Candidate candidate) {
                try {
                    vote(candidate);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        int rows = 1;
        int columns = 0;

        for (Candidate candidate : candidates) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cast.fxml"));
            //fxmlLoader.setLocation(getClass().getResource("cast.fxml"));

            try {
                VBox box = fxmlLoader.load();

                //box.setOnMouseClicked(event -> setOut());
                CastController castController = fxmlLoader.getController();
                castController.setData(candidate, voteListener);

                if (columns == 2) {
                    columns = 0;
                    ++rows;
                }
                blallot_grid.add(box, columns++, rows);
                GridPane.setMargin(box, new Insets(5));

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("OHH ------------");
            }

        }


    }

    private void vote(Candidate candidate) throws SQLException {

        try {
            Database_Ballot.addVote(1, candidate.getCnic());
        } catch (SQLException e){
            System.out.println(e);
            System.out.println("SQL EXCEPTION OCCURRED");
        }

        System.out.println("Current number of votes: " + candidate.getVoteCount());

        Stage stage = (Stage) blallot_grid.getScene().getWindow();
stage.close();
    }

    private void can() throws SQLException {
        System.out.println("-------");
        candidates =  Database_Ballot.fetchCandidates(1);
    }

    protected void setOut(){
        System.out.println("Yes");
    }


    public static void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Vote");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ballot/ballot.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Loading");
        }

        window.setScene(scene);
        window.setResizable(false);
        window.show();


    }

}
