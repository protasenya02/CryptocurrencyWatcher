package com.protasenya.cryptoCurrencyWatcher.service;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;

import java.util.List;

public interface CryptoCurrencyService {

    List<CryptoCurrencyDto> getAll();

    CryptoCurrencyDto getPrice(String symbol);
}
