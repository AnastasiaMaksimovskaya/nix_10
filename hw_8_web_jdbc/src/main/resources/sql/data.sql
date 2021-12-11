use shop_product;
insert into shops values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'shop1', 'address1');
insert into shops values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'shop2', 'address2');
insert into shops values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'shop3', 'address3');
insert into shops values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'shop4', 'address4');

insert into products values (default , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Bosh','Мясорубка', '1500' );
insert into products values (default , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Lg','Стиральная машина', '10500' );
insert into products values (default , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Samsung','Смартфон', '8300' );
insert into products values (default , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Asus','Нотбук', '13500' );
insert into products values (default , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Hp','Принтер', '5500' );
insert into products values (default , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Indesit','Холодильник', '10000' );
insert into products values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sony','Телевизор', '12500' );

insert into product_shop values (1, 1);
insert into product_shop values (2, 1);
insert into product_shop values (3, 1);
insert into product_shop values (4, 2);
insert into product_shop values (5, 3);
insert into product_shop values (6, 3);
insert into product_shop values (7, 3);
