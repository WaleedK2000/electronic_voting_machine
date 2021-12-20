package com.quizz.evm.ballot;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    private GlobalVars globalVars;

    @FXML
    private TableView<Res_Candidate> result;

    @FXML
    private TableColumn<Res_Candidate, String> fname;

    @FXML
    private TableColumn<Res_Candidate, String> party;

    @FXML
    private TableColumn<Res_Candidate, Integer> vote;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        globalVars = new GlobalVars();
        int election_id = globalVars.getElection_id();

        fname.setCellValueFactory(new PropertyValueFactory<>("name"));
        party.setCellValueFactory(new PropertyValueFactory<>("party"));
        vote.setCellValueFactory(new PropertyValueFactory<>("votes"));

        ObservableList<Res_Candidate> candidates = null;
        try {
            candidates = Database_Menu.loadResult(election_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*try {
           // candidates = Database_Menu.loadResult(election_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        System.out.println(candidates);
        //result = new TableView<>();

        result.setItems(candidates);

        //loadData();
    }

    private void loadData()  {
        int election_id = 1;



        fname.setCellValueFactory(new PropertyValueFactory<>("name"));
        party.setCellValueFactory(new PropertyValueFactory<>("party"));
        vote.setCellValueFactory(new PropertyValueFactory<>("votes"));

        System.out.println("work");

        result.getColumns().addAll(fname,party,vote);


    }


}
