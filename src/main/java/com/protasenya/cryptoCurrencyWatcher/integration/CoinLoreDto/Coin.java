package com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coin {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("symbol")
    private String symbol;

    private String name;
    private String nameid;
    private Integer rank;

    @JsonProperty("price_usd")
    private Float price;

    private Integer percent_change_24h;
    private Integer percent_change_1h;
    private Integer percent_change_7d;
    private Float market_cap_usd;
    private Float volume24h;
    private Float volume24_native;
    private Float csupply;
    private Float price_btc;
    private Integer tsupply;
    private Integer msupply;
}
