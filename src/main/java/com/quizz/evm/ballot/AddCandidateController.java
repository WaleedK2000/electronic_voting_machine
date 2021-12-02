package com.quizz.evm.ballot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCandidateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private GlobalVars globalVars;


    @FXML
    private Label errorLabel;

    @FXML
    private Label notFound;

    @FXML
    private Label partyLabel;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private TextField cnic;

    @FXML
    private ComboBox<String> party;

    public AddCandidateController(){
        globalVars = new GlobalVars();
    }

    private void initialize_party() {
        try {

            ObservableList<String> os = FXCollections.observableArrayList(Database_Menu.getPartyList());
            party.setItems(os);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @FXML
    public void checkCNIC(ActionEvent event){
        String s_cnic = cnic.getText();
        ArrayList<String> dat;
        try {
           dat = Database_Menu.searchCNIC(s_cnic);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if (dat == null){
            notFound.setText("Record Not Found. Please Enter All Details");

            first.setDisable(false);
            last.setDisable(false);
            party.setDisable(false);

        }
        else{
            System.out.println(dat);
            first.setText(dat.get(0));
            last.setText(dat.get(1));
            party.getSelectionModel().select(dat.get(2));
        }




    }

    public void insert(MouseEvent event)  {
        int election_num = globalVars.getElection_id();

        try {

            Database_Menu.insertCandidate(election_num, first.getText(), last.getText(), cnic.getText(), party.getSelectionModel().getSelectedItem());
            root = FXMLLoader.load(getClass().getResource("admin_candidate.fxml"));

        } catch (SQLException e) {
            System.out.println("Error SQL");
            errorLabel.setText("Error: Candidate Already Entered");
            System.out.println(e);
        } catch (IOException e){
            System.out.println("Cannot load admin candidate");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Unknown Exception");
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_party();
    }
}
