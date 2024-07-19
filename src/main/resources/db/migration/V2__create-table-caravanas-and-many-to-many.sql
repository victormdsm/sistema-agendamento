create table caravanas (
	id bigint not null primary key auto_increment,
    data_caravana bigint not null,
    hora time not null,
    templo varchar(45)
);

create table usuarios_has_caravana (
	id bigint not null primary key auto_increment,
    usuario_id bigint not null,
    caravana_id bigint not null,
    comprovante varchar(255) not null
);

