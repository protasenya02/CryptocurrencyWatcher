package com.protasenya.cryptoCurrencyWatcher.integration.CoinLoreDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CoinLoreRequest {
    private List<Long> id;
}



