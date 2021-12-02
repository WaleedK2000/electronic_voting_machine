package com.quizz.evm.ballot;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Admin_Candidate implements Initializable {

    GlobalVars globalVars;

    @FXML
    private GridPane blallot_grid;
    @FXML
    private ObservableList<Candidate> candidates;
    @FXML
    private VoteListener voteListener;
    @FXML
    private int election_id;

    Admin_Candidate(){
        globalVars = new GlobalVars();
    }

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
                // Modify Details
                System.out.println("hello");
            }
        };

        int rows = 1;
        int columns = 0;

        for (Candidate candidate : candidates) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cast.fxml"));
            fxmlLoader.setLocation(getClass().getResource("cast.fxml"));

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

    private void can() throws SQLException {
        System.out.println("-------");
        candidates =  Database_Ballot.fetchCandidates(1);
    }





}
