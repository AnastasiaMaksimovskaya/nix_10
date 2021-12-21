drop database if exists shop_product;
create database shop_product;
use shop_product;
drop table if exists products;
drop table if exists shops;
drop table if exists products_shops;

create table products
(
    id           bigint auto_increment
        primary key,
    created      datetime(6)  null,
    updated      datetime(6)  null,
    visible      bit          null,
    product_name varchar(255) not null,
    brand        varchar(255) not null,
    price        bigint       not null
);

create table shops
(
    id        bigint auto_increment
        primary key,
    created   datetime(6)  null,
    updated   datetime(6)  null,
    visible   bit          null,
    shop_name varchar(255) not null,
    address   varchar(255) not null
);

create table product_shop
(
    product_id bigint not null,
    shop_id    bigint not null,
    primary key (product_id, shop_id),
    foreign key (product_id) references products (id) ON DELETE CASCADE,
    foreign key (shop_id) references shops (id) ON DELETE CASCADE

);
