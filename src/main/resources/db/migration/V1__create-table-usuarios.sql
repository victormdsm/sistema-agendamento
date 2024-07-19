create table usuarios(
	id bigint not null primary key auto_increment,
    email varchar(255) unique not null,
    nome varchar(255) not null,
    senha varchar(255) not null,
    tipo_do_documento varchar(20) not null,
    numero_do_documento varchar(40) unique not null,
    data_de_nascimento date not null,
    data_da_vacina date not null,
    ala int not null,
    role int not null,
    index(email)
);