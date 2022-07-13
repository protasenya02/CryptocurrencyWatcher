package com.protasenya.cryptoCurrencyWatcher.service.impl;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.mapper.CryptoCurrencyMapper;
import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import com.protasenya.cryptoCurrencyWatcher.exception.ResourceNotFoundException;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinDto;
import com.protasenya.cryptoCurrencyWatcher.integration.Service.CoinLoreService;
import com.protasenya.cryptoCurrencyWatcher.repository.CryptoCurrencyRepository;
import com.protasenya.cryptoCurrencyWatcher.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    public BigDecimal getPrice(String symbol) {
        CryptoCurrencyDto currencyDto = findBySymbol(symbol);
        return currencyDto.getPriceUSD();
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void updateCryptocurrenciesPrice() {
        List<Long> id = cryptoCurrencyRepository.findAll().stream()
                .map(CryptoCurrency::getId).collect(Collectors.toList());
        List<CoinDto> resultCoins = coinLoreService.getCoinsById(id);
        cryptoCurrencyRepository.saveAll(cryptoCurrencyMapper.fromCoinDto(resultCoins));
    }

    @Override
    public CryptoCurrencyDto findBySymbol(String symbol) {
        if (cryptoCurrencyRepository.existsBySymbol(symbol)) {
            log.debug("Get cryptocurrency with symbol={}", symbol);
            CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(symbol);
            return cryptoCurrencyMapper.toDto(cryptoCurrency);
        } else {
            throw new ResourceNotFoundException("CryptoCurrency with symbol " + symbol + " not found.");
        }
    }

    @Override
    public BigDecimal getPriceFromCoinLore(String symbol) {
        Long cryptocurrencyId = findBySymbol(symbol).getId();
        return coinLoreService.getCoinPriceById(cryptocurrencyId);
    }
}
