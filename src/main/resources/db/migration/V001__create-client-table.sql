create table client (
	client_id char(36) not null,
	name varchar(80) not null,
	cpf varchar(11) unique not null,
	email varchar(35) unique not null,
	password varchar(30) not null,
	phone_number varchar(15) not null,
	creation_date datetime not null,
	update_date datetime not null,


	zip_code varchar(13),
	street varchar(100),
	number varchar(20),
	complement varchar(60),
	neighborhood varchar(60),
	city varchar(30),

	primary key (client_id)
) engine=InnoDB default charset=utf8;