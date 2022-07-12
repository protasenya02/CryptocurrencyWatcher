package com.protasenya.cryptoCurrencyWatcher.service;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.Coin;

import java.util.List;

public interface CryptoCurrencyService {

    List<CryptoCurrencyDto> getAll();

    CryptoCurrencyDto getPrice(String symbol);

    Coin updateCryptocurrenciesPrice();
}
