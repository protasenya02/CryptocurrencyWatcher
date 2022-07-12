package com.protasenya.cryptoCurrencyWatcher.service.impl;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.mapper.CryptoCurrencyMapper;
import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinDto;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreRequest;
import com.protasenya.cryptoCurrencyWatcher.integration.Service.CoinLoreService;
import com.protasenya.cryptoCurrencyWatcher.repository.CryptoCurrencyRepository;
import com.protasenya.cryptoCurrencyWatcher.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    public CryptoCurrencyDto getPrice(String symbol) {
        log.debug("Get cryptocurrency price with symbol={}", symbol);
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(symbol);
        return cryptoCurrencyMapper.toDto(cryptoCurrency);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void updateCryptocurrenciesPrice() {
        List<Long> id = cryptoCurrencyRepository.findAll().stream()
                .map(CryptoCurrency::getId).collect(Collectors.toList());

        CoinLoreRequest request = new CoinLoreRequest(id);
        List<CoinDto> resultCoins = coinLoreService.getCoinsById(request);
        cryptoCurrencyRepository.saveAll(cryptoCurrencyMapper.fromCoinDto(resultCoins));
    }
}
