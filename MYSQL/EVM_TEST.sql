-- view all tables

select * from election;
select * from party;
select * from candidate;
select * from voter;
select * from vote;
select * from candidate_election;

-- new election
INSERT INTO election  (name, status) VALUES ('Test', true);

-- new party
INSERT INTO party (id,name) VALUES ('TesT' , 'Test of Software');
INSERT INTO party (id,name) VALUES ('PTI' , 'Pakistan Tehreek-e-Insaf');
INSERT INTO party (id,name) VALUES ('PPP' , 'Pakistan Peoples Party');
INSERT INTO party (id,name) VALUES ('PML-N' , 'Pakistan Muslim League N');
-- new candidate
insert into candidate (cnic, party_id, first_name, last_name) values ('4987369258147', 'TesT', 'Waleed', 'Kayani');
insert into candidate (cnic, party_id, first_name, last_name) values ('4214369258147', 'TesT', 'Jhon', 'Doe');

-- insert candidate into an election
insert into candidate_election(cnic, election_id) values ('4987369258147' , 1);
insert into candidate_election(cnic, election_id) values ('4214369258147' , 1);


-- new voter
INSERT INTO voter (first_name, last_name, cnic, election_id) values ('Waleed', 'Kayani', '4987369258147', 1);

-- new vote
INSERT INTO vote( election_id, candidate_cnic) values (1, "4987369258147");
INSERT INTO vote( election_id, candidate_cnic) values (1, "4987369258147");

-- get election results
SELECT candidate.first_name, candidate.last_name, candidate.party_id, COUNT(vote.candidate_cnic)  AS 'Votes', candidate.cnic, candidate_election.election_id
FROM candidate
inner JOIN candidate_election
	ON candidate.cnic = candidate_election.cnic
LEFT JOIN vote
	ON candidate.cnic = vote.candidate_cnic
WHERE candidate_election.election_id = 1
GROUP BY candidate.cnic
ORDER BY COUNT(vote.candidate_cnic) desc;

-- candidates in election 1
SELECT candidate.first_name, candidate.last_name, candidate.party_id, candidate_election.election_id
FROM candidate
inner JOIN candidate_election
	ON candidate.cnic = candidate_election.cnic
WHERE candidate_election.election_id = 1
ORDER BY candidate.first_name;

-- Party list

Select id,name from party;

SELECT party_id, first_name, last_name
FROM candidate
WHERE cnic = '2345';

UPDATE election SET status = 0 WHERE id = 3;



    
    

