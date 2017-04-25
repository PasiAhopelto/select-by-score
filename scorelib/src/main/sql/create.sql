create table party (
	id integer,
	name varchar(64),
	primary key (id)
);

create table candidate (
	id integer,
	name varchar(64),
	current_party_id integer,
	primary key (id),
	foreign key (current_party_id) references party(id) on delete cascade
);

create table voting (
	id integer,
	official_name varchar(64),
	description varchar(64),
	scoring enum ('exact', 'distance'),
	primary key (id)
);

create table voting_option (
	id integer,
	voting_id integer,
	name varchar(64),
	position integer,
	primary key (id),
	foreign key (voting_id) references voting(id) on delete cascade,
	constraint unique (voting_id, name)
);

create table vote (
	id integer,
	voting_id integer,
	candidate_id integer,
	option_id integer,
	primary key (id),
	foreign key (voting_id) references voting(id) on delete cascade,
	foreign key (candidate_id) references candidate(id) on delete cascade,
	foreign key (option_id) references voting_option(id) on delete cascade
);
