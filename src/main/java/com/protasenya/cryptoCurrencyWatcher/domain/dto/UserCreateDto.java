package com.protasenya.cryptoCurrencyWatcher.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateDto {

    @NotBlank(message = "{user.username.blank.message}")
    private String username;

    @NotBlank(message = "{user.symbol.blank.message}")
    private String coinSymbol;
}
