create table cliente (
	cliente_id varchar(36) not null,
	nome varchar(80) not null,
	cpf varchar(11) unique not null,
	email varchar(35) unique not null,
	senha varchar(30) not null,
	contato varchar(15) not null,
	data_criacao datetime not null,
	data_atualizacao datetime not null,


	cep varchar(13),
	logradouro varchar(100),
	numero varchar(20),
	complemento varchar(60),
	bairro varchar(60),
	cidade varchar(30),

	primary key (cliente_id)
) engine=InnoDB default charset=utf8;