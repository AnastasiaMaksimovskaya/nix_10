insert into shops
values (1, CURRENT_TIMESTAMP(), 'shop1', CURRENT_TIMESTAMP(), 'address1', '18:00', '10:00');
insert into shops
values (2, CURRENT_TIMESTAMP(), 'shop2', CURRENT_TIMESTAMP(), 'address2', '18:00', '10:00');
insert into shops
values (3, CURRENT_TIMESTAMP(), 'shop3', CURRENT_TIMESTAMP(), 'address3', '18:00', '10:00');
insert into shops
values (4, CURRENT_TIMESTAMP(), 'shop4', CURRENT_TIMESTAMP(), 'address4', '18:00', '10:00');

insert into brands
values (1, CURRENT_TIMESTAMP(), 'Gan', CURRENT_TIMESTAMP(), 'CN');
insert into brands
values (2, CURRENT_TIMESTAMP(), 'MoYo', CURRENT_TIMESTAMP(), 'CN');
insert into brands
values (3, CURRENT_TIMESTAMP(), 'QiYi', CURRENT_TIMESTAMP(), 'CN');

insert into cubes
values (1, CURRENT_TIMESTAMP(), 'Gan 12 M Maglev 3x3x3', CURRENT_TIMESTAMP(), 10, 'THREE',
        'В сентябре 2021 года компания Gan выпустила два обновленных флагманских куба 3х3х3: Gan 12 M Maglev и Gan 12 M Leap. Создателям пазлов в очередной раз удалось приятно удивить спидкуберов.
Gan 12 M Maglev 3x3x3 стал самым дорогим кубиком 3х3 без электронной начинки. Но его цена обусловлена применением инновационных решений, которые ранее не встречались в головоломках Ган или любых других кубах.',
        'https://m.media-amazon.com/images/I/51sq3XiMRdL._AC_SX425_.jpg',
        500, true, 1);
insert into cubes
values (2, CURRENT_TIMESTAMP(), 'MoYu WeiLong WR MagLev 3х3 stickerless', CURRENT_TIMESTAMP(), 10, 'THREE',
        'MoYu WeiLong WR MagLev 3х3 - это усовершенствованный кубик мирового рекорда. Мягкий, плавный, легкий, быстрый и удобный при скоростных сборках и не только.',
        'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcSs1mXqpspeWwKmFNkci6UhvJlYsTK0CEM7JfFIuUFPq8LoSsfRDROMIub9SA5VB1Wecd9VSmkNNw&usqp=CAc',
        1199, true, 2);
insert into cubes
values (3, CURRENT_TIMESTAMP(), 'GAN 11 M Pro Duo', CURRENT_TIMESTAMP(), 10, 'THREE',
        'GAN 11 M Pro duo имеет ту же систему двойной регулировки. Используйте прилагаемый инструмент настройки, чтобы установить 6 различных уровней эластичности. Натяжение вручную до 4 различных настроек с помощью синей гайки. У куба новая крестовина, которая инновационно замагничена, примерно как у пирамидки Ган.',
        'https://content1.rozetka.com.ua/goods/images/big/201229521.jpg',
        1577, true, 1);
insert into cubes
values (4, CURRENT_TIMESTAMP(), 'QiYi WuXia 2x2 stickerless', CURRENT_TIMESTAMP(), 10, 'TWO',
        'Лучший кубик 2х2 в линейке QiYi MoFanGe. Классическая модификация без магнитов имеет очень хорошие скоростные показатели. Новый механизм ядра позволяет кубику избежать заеданий и локапов. Рекомендуем данную модель для спидкуберов и тех, кто учится собирать на скорость.',
        'https://kubik.in.ua/upload/resize_cache/webp/upload/iblock/d65/acaf9b91-86a8-11e8-8500-3a6561333536_50640054-c18f-11e8-a7d7-3a6561333536.webp',
        1199, true, 2);

insert into shop_product
values (1, 1);
insert into shop_product
values (2, 1);
insert into shop_product
values (3, 1);

