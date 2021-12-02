package com.quizz.evm.ballot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.EventObject;

public class AdminVoteController {

    private GlobalVars globalVars;

    @FXML
    private TextField cnic;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private Button votingScreen;

    @FXML
    private Button continueButton;

    @FXML
    private Label alreadyVoted;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public AdminVoteController(){
        globalVars = new GlobalVars();

        globalVars.setElectionId(1);
    }

    @FXML
    public void showBallot(ActionEvent event){
        try {
            Database_Menu.insertVoter(first.getText(),last.getText(),cnic.getText(),globalVars.getElection_id());
            setState(true);

            if(!alreadyVoted.isDisable()){
                alreadyVoted.setDisable(true);
            }

            BallotController.display();



        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            alreadyVoted.setDisable(false);
        } catch (SQLException e){
            System.out.println("SQL ERROR");
            System.out.println(e);
        }

    }

    public void endVoting(ActionEvent event){

        try {
            Database_Menu.endVote(globalVars.getElection_id());

            root = FXMLLoader.load(getClass().getResource("result.fxml"));

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }



    }



    public void setState(boolean f){
        first.setDisable(f);
        last.setDisable(f);
        cnic.setDisable(f);
        votingScreen.setDisable(f);
        continueButton.setDisable(!f);
    }

    @FXML
    public void enableVote(ActionEvent event){
        setState(false);

        first.setText("");
        last.setText("");
        cnic.setText("");

    }

}
