package com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto;

import lombok.Data;

import java.util.List;

@Data
public class CoinLoreResponse {
    private List<Coin> coins;
}
