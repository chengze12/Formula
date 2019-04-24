CREATE SEQUENCE car_id_seq;
create table Cars (
    id bigint not null DEFAULT NEXTVAL('car_id_seq'),
    primary key (id)
);
ALTER SEQUENCE car_id_seq OWNED BY Cars.id;
