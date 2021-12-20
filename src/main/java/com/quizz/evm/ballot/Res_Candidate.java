package com.quizz.evm.ballot;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Res_Candidate {
    public SimpleStringProperty name;
    public SimpleStringProperty party;
    public SimpleIntegerProperty votes;

    public Res_Candidate(SimpleStringProperty name, SimpleStringProperty party, SimpleIntegerProperty votes) {

        this.name = new SimpleStringProperty();
        this.party = new SimpleStringProperty();
        this.votes = new SimpleIntegerProperty();

        this.name = name;
        this.party = party;
        this.votes = votes;
    }

    public Res_Candidate(String name, String party, int votes) {

        this.name = new SimpleStringProperty();
        this.party = new SimpleStringProperty();
        this.votes = new SimpleIntegerProperty();

        this.name.set(name);
        this.party.set(party);
        this.votes.set(votes);

    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getParty() {
        return party.get();
    }

    public SimpleStringProperty partyProperty() {
        return party;
    }

    public int getVotes() {
        return votes.get();
    }

    public SimpleIntegerProperty votesProperty() {
        return votes;
    }
}
