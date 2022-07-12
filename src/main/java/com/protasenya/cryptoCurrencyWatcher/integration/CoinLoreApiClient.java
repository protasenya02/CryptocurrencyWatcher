package com.protasenya.cryptoCurrencyWatcher.integration;

import com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto.CoinLoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${coinlore.feign.name}", url = "${coinlore.feign.url}")
public interface CoinLoreApiClient {

    @GetMapping
    CoinLoreResponse findCoinsById(@RequestParam(value = "id") List<Integer> ids);
}
