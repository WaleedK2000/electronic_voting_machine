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

public class NewPartyController {


    @FXML
    private TextField partyName;

    @FXML
    private TextField partyId;

    @FXML
    private Label error;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void insertParty(ActionEvent event) {
        try {
            Database_Menu.insertParty(partyName.getText(),partyId.getText());

            root = FXMLLoader.load(getClass().getResource("start_user.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e);
            error.setText("Party Already Exists");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
