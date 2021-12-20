package com.quizz.evm.ballot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CastController {

    @FXML
    private ImageView candidate_pic;

    @FXML
    private ImageView candidate_party_pic;

    @FXML
    private Label candidate_name;

    @FXML
    private Label candidate_party;

    @FXML
    private void click(MouseEvent mouseEvent){
        voteListener.onClickListener(candidate);
    }

    private Candidate candidate;
    private VoteListener voteListener;

    public void setData(Candidate can, VoteListener voteListener){
        Image image = new Image(getClass().getResourceAsStream(can.getCandidate_src()));
        candidate_pic.setImage(image);

        image = new Image(getClass().getResourceAsStream(can.getParty_src()));
        candidate_party_pic.setImage(image);

        candidate_name.setText(can.getName());
        candidate_party.setText(can.getParty());

        this.voteListener = voteListener;
        this.candidate = can;
    }


}
