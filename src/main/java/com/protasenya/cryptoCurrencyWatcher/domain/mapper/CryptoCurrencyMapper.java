package com.protasenya.cryptoCurrencyWatcher.domain.mapper;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import com.protasenya.cryptoCurrencyWatcher.integration.CoinDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CryptoCurrencyMapper {

    CryptoCurrencyDto toDto(CryptoCurrency cryptoCurrency);

    List<CryptoCurrencyDto> toDto(List<CryptoCurrency> coins);

    List<CryptoCurrency> fromCoinDto(List<CoinDto> coinDtos);

    CryptoCurrency fromDto(CryptoCurrencyDto currencyDto);
}
