package com.protasenya.cryptoCurrencyWatcher.integration.Service;

import com.protasenya.cryptoCurrencyWatcher.integration.CoinDto;

import java.math.BigDecimal;
import java.util.List;

public interface CoinLoreService {

    List<CoinDto> getCoinsById(List<Long> requestId);

    BigDecimal getCoinPriceById(Long id);
}
