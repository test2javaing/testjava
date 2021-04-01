CREATE USER "TestJava" WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD '0000';

CREATE DATABASE "Voiture"
    WITH 
    OWNER = "TestJava"
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE VOITURE (
	ID                  VARCHAR(10) 		not NULL,
	NOM  				VARCHAR(50) 		UNIQUE,
	PRIX 				NUMERIC 			CHECK (PRIX > 0),
	constraint PK_VOITURE primary key (ID)
);

CREATE TABLE UTILISATEUR (
   ID_USR               VARCHAR(10)          not null,
   NOM                  VARCHAR(50),
   LOGIN                VARCHAR(50)          UNIQUE,
   MDP                  VARCHAR(64)          not null,
   constraint PK_USER primary key (ID_USR)
);

CREATE TABLE COMMENTS (
	ID 					VARCHAR(10) not null,
	VALUE 				TEXT,
	ID_USR 				VARCHAR(10) not null,
	ID_VOITURE 			VARCHAR(10) not null,
	DATE 				TIMESTAMP,
	constraint PK_COMMENT primary key (ID)
);

create sequence utilisateur_seq start with 1;
create sequence voiture_seq start with 1;
create sequence comments_seq start with 1;

ALTER TABLE COMMENTS 
	ADD CONSTRAINT FK_COMMENTS_REF_USER FOREIGN KEY (ID_USR)
		REFERENCES UTILISATEUR (ID_USR);

ALTER TABLE COMMENTS 
	ADD CONSTRAINT FK_COMMENTS_REF_VOITURE FOREIGN KEY (ID_VOITURE)
		REFERENCES VOITURE (ID);