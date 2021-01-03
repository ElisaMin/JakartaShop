-- 用户表
create table users (
    id int(4) primary key autoincrement not null ,
    name text not null ,
    password text not null ,
    gender int check ( gender=1 or gender=0 ) default 0,
    phone text not null ,
    image text,
    credit int default 0 ,
    reg_time datetime,
    isAdmin int default 0
);
-- 商品类型
create table product_type (
    id int primary key  autoincrement ,
    name text not null
);

-- 商品消息表
create table products (
    id int primary key autoincrement ,
    name text not null ,
    price numeric(6,2) not null  default 0,
    quantity int default  0,
    sold int default 0,
    image text,
    info text,
    create_time datetime,
    isHit int default 0,
    type_id int ,
    foreign key(type_id) references  product_type(id)
);
