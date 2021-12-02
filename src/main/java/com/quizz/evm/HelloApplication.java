package com.quizz.evm;


import com.quizz.evm.ballot.Database_Ballot;
import com.quizz.evm.ballot.Database_Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ballot/start_user.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("EVM");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static int  main(String[] args) {

        //ObservableList<Candidate> er = Database.fetchCandidates(1);





    launch();

       // System.out.println("Created " + d );


        return 0;

    }
}