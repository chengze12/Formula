CREATE SEQUENCE image_id_seq;
CREATE TABLE images (
    id bigint not null DEFAULT NEXTVAL('image_id_seq'),
    front varchar(255) not NULL,
    back varchar(255) not NULL,
    interior varchar(255) not NULL,
    car_id bigint DEFAULT NULL,
    primary key (id),
    CONSTRAINT fk_image_car
      FOREIGN KEY (car_id)
      REFERENCES cars (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);
ALTER SEQUENCE image_id_seq OWNED BY images.id;
