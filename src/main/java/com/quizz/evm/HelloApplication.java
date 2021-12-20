package com.quizz.evm;


import com.quizz.evm.ballot.Database_Ballot;
import com.quizz.evm.ballot.Database_Menu;
import com.quizz.evm.ballot.GlobalVars;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class HelloApplication extends Application {



    @Override
    public void start(Stage stage)  {

        int check = recovery();
        System.out.println(check);
        FXMLLoader fxmlLoader;

        if(check == -1){
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ballot/start_user.fxml"));
        }
        else {
             fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ballot/admin_vote.fxml"));
            GlobalVars globalVars = new GlobalVars();
            globalVars.setElectionId(check);
        }

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Loading");
            System.out.println(e);
        }
        stage.setTitle("EVM");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static int recovery(){

        try {
          return Database_Menu.checkIncompleteElection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int  main(String[] args) {

        //ObservableList<Candidate> er = Database.fetchCandidates(1);




       launch();

       // System.out.println("Created " + d );


        return 0;

    }
}