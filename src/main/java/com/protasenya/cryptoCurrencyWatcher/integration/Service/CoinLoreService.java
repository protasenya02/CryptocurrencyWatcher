package com.protasenya.cryptoCurrencyWatcher.integration.Service;

import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinDto;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreRequest;

import java.util.List;

public interface CoinLoreService {

    List<CoinDto> getCoinsById(CoinLoreRequest apiRequest);
}
