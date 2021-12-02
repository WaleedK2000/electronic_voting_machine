package com.quizz.evm.ballot;

public class Candidate {
    private String name;
    private String candidate_src;
    private String party;
    private String party_src;
    private final String cnic;
    private int vote_count;

    public Candidate(String name, String candidate_src, String party, String party_src, String cnic) {
        this.name = name;
        this.candidate_src = candidate_src;
        this.party = party;
        this.party_src = party_src;
        this.cnic = cnic;

        System.out.println("XD "+cnic);

        vote_count = 0;
    }

    public Candidate(String name, String party, String cnic) {
        this.name = name;
        this.candidate_src = "img/person-icon.png";
        this.party = party;
        this.cnic = cnic;
        this.party_src = "img/logo.png";
        vote_count = 0;
    }

    public Candidate(String name, String party, String cnic, int vote_count) {
        this.name = name;
        this.candidate_src = "img/person-icon.png";
        this.party = party;
        this.cnic = cnic;
        this.party_src = "img/logo.png";
        this.vote_count = vote_count;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCandidate_src() {
        return candidate_src;
    }

    public void setCandidate_src(String candidate_src) {
        this.candidate_src = candidate_src;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getParty_src() {
        return party_src;
    }

    public void setParty_src(String party_src) {
        this.party_src = party_src;
    }

    public void vote() {
        ++vote_count;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public String getCnic(){
        return cnic;
    }
}
