create table roles (
    id bigserial primary key,
    role varchar(63) not null,
    description varchar(255)
);

create table users (
    id bigserial primary key,
    user_name varchar(255) not null,
    password text not null,
    role_id bigint not null references roles(id),
    first_name varchar(63),
    last_name varchar(63)
);

create table products (
    id bigserial primary key,
    name varchar(255) not null,
    type int not null,
    price bigint not null
);

create table orders (
    id bigserial primary key,
    customer_name varchar(255) not null,
    phone_number varchar(31) not null,
    deliver_to varchar(255) not null,
    status int not null,
    price bigint not null,
    created_date timestamp not null,
    modified_date timestamp
);

create table order_details (
    id bigserial primary key,
    product_id bigint references products(id),
    order_id bigint references orders(id),
    quantity int not null
);