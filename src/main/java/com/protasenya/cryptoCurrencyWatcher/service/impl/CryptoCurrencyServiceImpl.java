package com.protasenya.cryptoCurrencyWatcher.service.impl;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.mapper.CryptoCurrencyMapper;
import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import com.protasenya.cryptoCurrencyWatcher.repository.CryptoCurrencyRepository;
import com.protasenya.cryptoCurrencyWatcher.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Override
    public List<CryptoCurrencyDto> getAll() {
        List<CryptoCurrency> currencyList = new ArrayList<>();
        CryptoCurrency cryptoCurrency = new CryptoCurrency(90l, "BTC", BigDecimal.ONE);
        currencyList.add(cryptoCurrency);

        return cryptoCurrencyMapper.toDto(currencyList);
    }

    @Override
    public CryptoCurrencyDto getPrice(String symbol) {
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(symbol);
        return cryptoCurrencyMapper.toDto(cryptoCurrency);
    }
}
