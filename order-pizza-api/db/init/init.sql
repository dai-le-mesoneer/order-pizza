create database "OrderPizza";
\c OrderPizza;
create schema orderpizza;
alter database OrderPizza set search_path to orderpizza;
create extension if not exists pgcrypto;