CREATE SEQUENCE user_id_seq;
create table Users (
    id bigint not null DEFAULT NEXTVAL('user_id_seq'),
    username varchar(255) not NULL UNIQUE ,
    first_name varchar(255) not NULL  ,
    last_name varchar(255) not NULL  ,
    email varchar(255) not NULL UNIQUE ,
    user_id varchar(255) not NULL UNIQUE ,
    primary key (id)
);
ALTER SEQUENCE user_id_seq OWNED BY Users.id;
