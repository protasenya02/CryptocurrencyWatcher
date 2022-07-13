create table if not exists "users"
(
    id                                bigserial       not null,
    username                          varchar(60)     not null,
    cryptocurrency_id                 int8            not null,
    coin_price_per_registration       numeric(9,2)    not null,
    primary key (id)
);

alter table if exists users
    add constraint FK1_users foreign key (cryptocurrency_id) references cryptocurrencies;

