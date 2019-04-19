CREATE SEQUENCE users_id_seq;
create table Users (
    id bigint not null DEFAULT NEXTVAL('users_id_seq'),
    primary key (id)
);
ALTER SEQUENCE users_id_seq OWNED BY Users.id;
