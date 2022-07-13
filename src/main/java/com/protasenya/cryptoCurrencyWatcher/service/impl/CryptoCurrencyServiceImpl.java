package com.protasenya.cryptoCurrencyWatcher.service.impl;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.mapper.CryptoCurrencyMapper;
import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import com.protasenya.cryptoCurrencyWatcher.domain.model.User;
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
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private static final int PRICE_UPDATE_RATE = 60000;

    private static final float ALLOWABLE_PERCENTAGE_CHANGE = 1;

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
    @Scheduled(fixedRate = PRICE_UPDATE_RATE)
    public void updateCryptocurrenciesPrice() {
        List<Long> id = cryptoCurrencyRepository.findAll().stream()
                .map(CryptoCurrency::getId).collect(Collectors.toList());
        List<CoinDto> newCoins = coinLoreService.getCoinsById(id);
        List<CryptoCurrency> oldCoins = cryptoCurrencyRepository.findAll();

        for (int i = 0; i < newCoins.size(); i++) {
            BigDecimal oldPrice = oldCoins.get(i).getPriceUSD();
            BigDecimal newPrice = newCoins.get(i).getPriceUSD();
            float percentageChange = countPercentageChange(oldPrice, newPrice);

            if (Math.abs(percentageChange) >= ALLOWABLE_PERCENTAGE_CHANGE) {
                CryptoCurrency currentCoin = oldCoins.get(i);
                List<User> coinUsers = currentCoin.getUsers();

                for (User user : coinUsers) {
                    BigDecimal pricePerRegistration = user.getCoinPricePerRegistration();
                    float priceChangeFromRegistration = countPercentageChange(pricePerRegistration, newPrice);
                    log.warn(String.format("Username: %s, symbol: %s, percentage change since registration: %f%%",
                            user.getUsername(), currentCoin.getSymbol(), priceChangeFromRegistration));
                }
            }
        }
        cryptoCurrencyRepository.saveAll(cryptoCurrencyMapper.fromCoinDto(newCoins));
    }

    private float countPercentageChange(BigDecimal oldPrice, BigDecimal newPrice) {
        return ((newPrice.subtract(oldPrice)).divide(oldPrice, 4, RoundingMode.HALF_UP))
                .multiply(BigDecimal.valueOf(100)).floatValue();
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
