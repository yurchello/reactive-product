--liquibase formatted sql
--changeset reactiveprod:create-tables

CREATE SCHEMA reactive_product AUTHORIZATION postgres;

CREATE TABLE reactive_product.product
(
    id BIGSERIAL,
    title VARCHAR,
    pr_group VARCHAR,
    PRIMARY KEY (id)
);
