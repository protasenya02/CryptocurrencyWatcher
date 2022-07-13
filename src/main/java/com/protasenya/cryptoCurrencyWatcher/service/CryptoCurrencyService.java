package com.protasenya.cryptoCurrencyWatcher.service;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;

import java.math.BigDecimal;
import java.util.List;

public interface CryptoCurrencyService {

    List<CryptoCurrencyDto> getAll();

    BigDecimal getPrice(String symbol);

    void updateCryptocurrenciesPrice();

    CryptoCurrencyDto findBySymbol(String symbol);

    BigDecimal getPriceFromCoinLore(String symbol);
}
