package com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoinDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("price_usd")
    private BigDecimal priceUSD;
}
