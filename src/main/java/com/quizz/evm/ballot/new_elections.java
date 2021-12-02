package com.quizz.evm.ballot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class new_elections {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private GlobalVars globalVars;

    @FXML
    private TextField name;

    public new_elections(){
        globalVars = new GlobalVars();
    }

    @FXML
    public void proceed(MouseEvent event) throws IOException {

            try {
                int e_id = Database_Menu.newElection(name.getText());
                globalVars.setElectionId(e_id);

                System.out.println(e_id + " elec id");

                root = FXMLLoader.load(getClass().getResource("add_candidate.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }




    }

}
