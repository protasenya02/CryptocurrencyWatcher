package com.protasenya.cryptoCurrencyWatcher.domain.mapper;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CryptoCurrencyMapper {

    CryptoCurrencyDto toDto(CryptoCurrency cryptoCurrency);

    CryptoCurrency fromDto(CryptoCurrencyDto cryptoCurrencyDto);

    List<CryptoCurrencyDto> toDto(List<CryptoCurrency> shops);
}
