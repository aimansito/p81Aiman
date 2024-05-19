drop database p81Aiman;
create database p81Aiman;
use p81Aiman;
create table donantes(
		id_paciente int,
        nombre varchar(30),
        fechaNacimiento date,
        grupoSanguineo varchar(30),
        rh varchar(30),
        numeroDonaciones int,
        Constraint pk_donantes PRIMARY KEY(id_paciente)
        );
        
-- alter table donantes modify grupoSanguineo char(1);