create table party (
	id integer auto_increment,
	name varchar(64) unique not null,
	primary key (id)
);

create table voting (
	id integer auto_increment,
	name varchar(64) unique not null,
	description varchar(256) not null,
	primary key (id)
);

create table votes (
	id integer auto_increment,
	voting_id integer not null,
	party_id integer not null,
	votes integer not null,
	vote varchar(10),
	unique key (voting_id, party_id, vote),
	foreign key (voting_id) references voting(id) on delete cascade,
	foreign key (party_id) references party(id) on delete cascade
);
