--liquibase formatted sql
--changeset reactiveprod:create-tables

INSERT INTO reactive_product.product(title, pr_group) VALUES
('Test Description 1', 'test group 1'),
('Test Description 2', 'test group 2'),
('Test Description 3', 'test group 3'),
('Test Description 4', 'test group 4'),
('Test Description 5', 'test group 5'),
('Test Description 6', 'test group 6'),
('Test Description 7', 'test group 7'),
('Test Description 8', 'test group 8');