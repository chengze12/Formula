CREATE SEQUENCE user_id_seq;
create table Users (
    id bigint not null DEFAULT NEXTVAL('user_id_seq'),
    primary key (id)
);
ALTER SEQUENCE user_id_seq OWNED BY Users.id;
