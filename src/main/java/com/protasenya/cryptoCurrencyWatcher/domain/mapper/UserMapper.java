package com.protasenya.cryptoCurrencyWatcher.domain.mapper;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserCreateDto;
import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserDto;
import com.protasenya.cryptoCurrencyWatcher.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User fromDto(UserCreateDto userDto);

    UserDto fromDto(User user);
}
