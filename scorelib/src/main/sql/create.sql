create table party (
	id integer,
	name varchar(64) unique not null,
	primary key (id)
);

create table candidate (
	id integer,
	name varchar(64) unique not null,
	current_party_id integer,
	primary key (id),
	foreign key (current_party_id) references party(id) on delete cascade
);

create table voting (
	id integer,
	name varchar(64) unique not null,
	description varchar(256) not null,
	scoring enum ('exact', 'distance') not null,
	primary key (id)
);

create table voting_option (
	id integer,
	voting_id integer not null,
	name varchar(64) not null,
	position integer not null,
	primary key (id),
	foreign key (voting_id) references voting(id) on delete cascade,
	unique key voting_option_idx (voting_id, name),
	constraint unique (voting_id, name)
);

create table vote (
	id integer,
	voting_id integer not null,
	candidate_id integer not null,
	option_id integer not null,
	primary key (id),
	foreign key (voting_id) references voting(id) on delete cascade,
	foreign key (candidate_id) references candidate(id) on delete cascade,
	foreign key (option_id) references voting_option(id) on delete cascade
);
