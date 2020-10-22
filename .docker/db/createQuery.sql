CREATE DATABASE image_gallery;

CREATE TABLE image(
     uuid uuid not null,
     id varchar(255),
     camera varchar(255),
     author varchar(255),
     tags varchar(255),
     cropped_picture varchar (255),
     full_picture varchar(255),
     page int
);