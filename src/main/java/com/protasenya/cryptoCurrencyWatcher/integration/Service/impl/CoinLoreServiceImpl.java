package com.protasenya.cryptoCurrencyWatcher.integration.Service.impl;

import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreApiClient;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinDto;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreRequest;
import com.protasenya.cryptoCurrencyWatcher.integration.Service.CoinLoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinLoreServiceImpl implements CoinLoreService {

    private final CoinLoreApiClient apiClient;

    @Override
    public List<CoinDto> getCoinsById(CoinLoreRequest apiRequest) {
        List<Long> requestId = apiRequest.getId();
        log.info("Integration with Coin Lore API was started. Get coins with id={}", requestId);
        String id = requestId.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
        List<CoinDto> resultCoins = apiClient.findCoinsById(id);
        log.info("Coin Lore response={}", resultCoins);

        return resultCoins;
    }
}
