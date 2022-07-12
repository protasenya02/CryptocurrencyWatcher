package com.protasenya.cryptoCurrencyWatcher.service.impl;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.mapper.CryptoCurrencyMapper;
import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.Coin;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreRequest;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreResponse;
import com.protasenya.cryptoCurrencyWatcher.integration.Service.CoinLoreService;
import com.protasenya.cryptoCurrencyWatcher.repository.CryptoCurrencyRepository;
import com.protasenya.cryptoCurrencyWatcher.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;
    private final CoinLoreService coinLoreService;

    @Override
    public List<CryptoCurrencyDto> getAll() {
        List<CryptoCurrency> currencyList = cryptoCurrencyRepository.findAll();
        return cryptoCurrencyMapper.toDto(currencyList);
    }

    @Override
    public CryptoCurrencyDto getPrice(String symbol) {
        log.debug("Get cryptocurrency price with symbol={}", symbol);
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(symbol);
        return cryptoCurrencyMapper.toDto(cryptoCurrency);
    }

    public Coin updateCryptocurrenciesPrice() {
        List<Integer> ids = new ArrayList<>();
        ids.add(80);
        ids.add(90);

        CoinLoreRequest request = new CoinLoreRequest(ids);
        List<Coin> coins = coinLoreService.getCoinsById(request);

        return null;
        //return coinLoreService.getCoinsById(request);
    }
}