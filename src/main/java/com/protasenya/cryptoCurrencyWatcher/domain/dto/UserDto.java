package com.protasenya.cryptoCurrencyWatcher.domain.dto;

import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDto {
    private Long id;
    private String username;
    private BigDecimal coinPricePerRegistration;
    private CryptoCurrency cryptoCurrency;
}
