module com.quizz.evm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.quizz.evm to javafx.fxml;
    exports com.quizz.evm;
    exports com.quizz.evm.ballot;
    opens com.quizz.evm.ballot to javafx.fxml;

}