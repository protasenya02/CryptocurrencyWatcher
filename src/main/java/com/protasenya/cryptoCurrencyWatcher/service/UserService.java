package com.protasenya.cryptoCurrencyWatcher.service;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserCreateDto;
import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserDto;

public interface UserService {

    UserDto notifyUser(UserCreateDto userDto);
}
