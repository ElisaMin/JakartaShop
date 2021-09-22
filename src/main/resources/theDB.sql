-- 用户表
create table users (
    id int(4) primary key auto_increment not null ,
    name text not null ,
    password text not null ,
    gender int
--         check ( gender=1 or gender=0 )
        default 0,
    phone text not null ,
    image text,
    credit int default 0 ,
    reg_time datetime,
    isAdmin int default 0,
    last_login datetime
);
-- 商品类型
create table product_type (
    id int primary key  auto_increment ,
    name text not null
);

-- 商品消息表
create table products (
    id int primary key auto_increment ,
    name text not null ,
    price numeric(6,2) not null  default 0,
    quantity int default  0,
    sold int default 0,
    image text,
    info text,
    create_time varchar,
    isHit int default 0,
    is_using int default 1,
    type_id int ,
    foreign key(type_id) references  product_type(id)
);

-- 购物车
create table pre_orders(
    id int primary key auto_increment ,
    user_id int not null ,
    product_id int not null ,
    counts  int default 0,
    foreign key(product_id) references products(id),
    foreign key (user_id) references users(id)
);
-- 子订单
-- auto-generated definition
create table SUB_ORDERS (
    ID         INT auto_increment primary key ,
    ORDER_ID   INT,
    PRODUCT_ID INT not null,
    AMOUNT     INT default 0,
    foreign key (ORDER_ID) references ORDERS (ID),
    foreign key (PRODUCT_ID) references PRODUCTS (ID)
);


-- 订单表
create table orders(
    id int primary key auto_increment ,
    user_id int not null,
    generate_time datetime not null,
    comment text, -- 评论
    comment_generate_time datetime,
    done_yet int default 0,
    foreign key (user_id) references users(id)
);

insert into users(name, password, phone,isAdmin) VALUES ('admin','admin','12312341234',1  );
insert into product_type(name) values ( 'default' );
