package com.quizz.evm.ballot;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void newElection(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("new_elections.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void newParty(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("newParty.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
