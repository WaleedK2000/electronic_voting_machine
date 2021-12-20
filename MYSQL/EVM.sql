SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE election;
DROP TABLE voter;
DROP TABLE party;
DROP TABLE candidate;
DROP TABLE candidate_election;
DROP TABLE vote;
SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE election (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(120),
    status boolean,
    
    PRIMARY KEY (id)
);

CREATE TABLE voter (
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    cnic VARCHAR(20),
    election_id int,
    
    PRIMARY KEY (cnic, election_id),
    FOREIGN KEY (election_id)
        REFERENCES election (id)
    
);

CREATE TABLE party (
    id VARCHAR(6) not null unique,
    name VARCHAR(100) NOT NULL unique,
    PRIMARY KEY (id)
);

CREATE TABLE candidate (
    cnic VARCHAR(20),
    party_id VARCHAR(6),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    PRIMARY KEY (cnic),
    FOREIGN KEY (party_id)
        REFERENCES party (id)
);

CREATE TABLE candidate_election (
    cnic VARCHAR(20) NOT NULL,
    election_id INT NOT NULL,
    FOREIGN KEY (cnic)
        REFERENCES candidate (cnic),
    FOREIGN KEY (election_id)
        REFERENCES election (id),
        
	PRIMARY KEY (cnic, election_id)
);


CREATE TABLE vote (
    id INT NOT NULL AUTO_INCREMENT,
    election_id INT,
    candidate_cnic VARCHAR(20),
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_cnic)
        REFERENCES candidate (cnic),
    FOREIGN KEY (election_id)
        REFERENCES election (id)
);