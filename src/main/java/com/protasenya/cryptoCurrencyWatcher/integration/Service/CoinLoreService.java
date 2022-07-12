package com.protasenya.cryptoCurrencyWatcher.integration.Service;

import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.Coin;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreRequest;

import java.util.List;

public interface CoinLoreService {

    List<Coin> getCoinsById(CoinLoreRequest apiRequest);
}
