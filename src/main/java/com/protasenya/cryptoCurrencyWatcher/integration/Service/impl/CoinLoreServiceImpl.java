package com.protasenya.cryptoCurrencyWatcher.integration.Service.impl;

import com.protasenya.cryptoCurrencyWatcher.exception.ResourceNotFoundException;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreApiClient;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinDto;
import com.protasenya.cryptoCurrencyWatcher.integration.Service.CoinLoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinLoreServiceImpl implements CoinLoreService {

    private final CoinLoreApiClient apiClient;

    @Override
    public List<CoinDto> getCoinsById(List<Long> requestId) {
        try {
            log.info("Integration with Coin Lore API was started. Get coins with id={}", requestId);
            String id = requestId.stream().map(String::valueOf)
                    .collect(Collectors.joining(","));
            List<CoinDto> resultCoins = apiClient.findCoinsById(id);
            if (resultCoins.isEmpty()) {
                throw new ResourceNotFoundException
                        ("Coin Lore did not find response for request " + requestId + ".");
            }
            log.info("Coin Lore response={}", resultCoins);
            return resultCoins;
        } catch (RuntimeException e)  {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal getCoinPriceById(Long id) {
        List<CoinDto> resultCoins = getCoinsById(Collections.singletonList(id));
        return resultCoins.get(0).getPriceUSD();
    }
}
