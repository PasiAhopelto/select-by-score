create table party (
	id integer,
	name varchar(64) unique not null,
	primary key (id)
);

create table voting (
	id integer,
	name varchar(64) unique not null,
	description varchar(256) not null,
	primary key (id)
);

create table voting_option (
	voting_id integer not null,
	name varchar(64) not null,
	position integer not null,
	primary key (voting_id, position),
	foreign key (voting_id) references voting(id) on delete cascade
);

create table vote (
	voting_id integer not null,
	option_position integer not null,
	primary key (voting_id, option_position),
	foreign key (voting_id) references voting(id) on delete cascade,
	foreign key (voting_id, option_position) references voting_option(voting_id, position) on delete cascade
);
