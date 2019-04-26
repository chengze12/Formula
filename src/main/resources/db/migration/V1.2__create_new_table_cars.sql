CREATE SEQUENCE car_id_seq;
CREATE TABLE cars (
    id bigint not null DEFAULT NEXTVAL('car_id_seq'),
    make varchar(255) not NULL,
    price varchar(255) not NULL,
    model varchar(255) not NULL,
    year varchar(255) not NULL,
    user_id bigint DEFAULT NULL,
    primary key (id),
    CONSTRAINT fk_car_user
      FOREIGN KEY (user_id)
      REFERENCES users (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);
ALTER SEQUENCE car_id_seq OWNED BY cars.id;