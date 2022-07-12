insert into cryptocurrencies select * from (
    select 90, 'BTC', 0 union
    select 80, 'ETH', 0 union
    select 48543, 'SOL', 0
) x where not exists(select * from cryptocurrencies);
