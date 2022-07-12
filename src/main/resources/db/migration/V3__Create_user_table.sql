create table if not exists "users"
(
    id                                bigserial       not null,
    username                          varchar(60)     not null,
    coin_symbol                       varchar(60)     not null,
    coin_price_per_registration       numeric(9,2)    not null,
    primary key (id)
);
