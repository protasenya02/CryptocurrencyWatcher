package com.protasenya.cryptoCurrencyWatcher.integration.Service.impl;

import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreApiClient;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.Coin;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreApi;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreRequest;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreResponse;
import com.protasenya.cryptoCurrencyWatcher.integration.Service.CoinLoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinLoreServiceImpl implements CoinLoreService {

    private final CoinLoreApiClient apiClient;

    @Override
    public List<Coin> getCoinsById(CoinLoreRequest apiRequest) {
        log.debug("Integration with Coin Lore API was started. Get coins with id={}",apiRequest.getIds());
        List<Coin> resultCoins = new ArrayList<>();
        CoinLoreResponse response = apiClient.findCoinsById(apiRequest.getIds());

//        if (coinsById == null) {
//            return resultCoins;
//        }
//        CoinLoreResponse response = coinsById.getResponse();
//        if (response == null) {
//            return resultCoins;
//        }
//        return response.getCoins();
        return null;
    }
}
