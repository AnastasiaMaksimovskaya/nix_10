insert into shops
values (1, CURRENT_TIMESTAMP(),'shop1', CURRENT_TIMESTAMP(), 'address1','18:00','10:00');
insert into shops
values (2, CURRENT_TIMESTAMP(),'shop2', CURRENT_TIMESTAMP(), 'address2','18:00','10:00');
insert into shops
values (3, CURRENT_TIMESTAMP(),'shop3', CURRENT_TIMESTAMP(), 'address3','18:00','10:00');
insert into shops
values (4, CURRENT_TIMESTAMP(),'shop4', CURRENT_TIMESTAMP(), 'address4','18:00','10:00');

insert into brands
values (1,CURRENT_TIMESTAMP(),'Gan',CURRENT_TIMESTAMP(),'CN');
insert into brands
values (2,CURRENT_TIMESTAMP(),'MoYo',CURRENT_TIMESTAMP(),'CN');

insert into cubes
values (1, CURRENT_TIMESTAMP(),'Gan 12 M Maglev 3x3x3', CURRENT_TIMESTAMP(),10,'THREE',
        'В сентябре 2021 года компания Gan выпустила два обновленных флагманских куба 3х3х3: Gan 12 M Maglev и Gan 12 M Leap. Создателям пазлов в очередной раз удалось приятно удивить спидкуберов.
Gan 12 M Maglev 3x3x3 стал самым дорогим кубиком 3х3 без электронной начинки. Но его цена обусловлена применением инновационных решений, которые ранее не встречались в головоломках Ган или любых других кубах.',
        'https://m.media-amazon.com/images/I/51sq3XiMRdL._AC_SX425_.jpg',
        500, true,1);
insert into cubes
values (2, CURRENT_TIMESTAMP(),'MoYu WeiLong WR MagLev 3х3 stickerless', CURRENT_TIMESTAMP(),10,'THREE',
        'MoYu WeiLong WR MagLev 3х3 - это усовершенствованный кубик мирового рекорда. Мягкий, плавный, легкий, быстрый и удобный при скоростных сборках и не только.',
        'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcSs1mXqpspeWwKmFNkci6UhvJlYsTK0CEM7JfFIuUFPq8LoSsfRDROMIub9SA5VB1Wecd9VSmkNNw&usqp=CAc',
        1199, true,2);

insert into shop_product
values (1, 1);
insert into shop_product
values (2, 1);
insert into shop_product
values (3, 1);

