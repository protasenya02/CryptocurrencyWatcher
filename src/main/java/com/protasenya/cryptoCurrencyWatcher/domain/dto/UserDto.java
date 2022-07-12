package com.protasenya.cryptoCurrencyWatcher.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String coinSymbol;
    private BigDecimal coinPricePerRegistration;
}
