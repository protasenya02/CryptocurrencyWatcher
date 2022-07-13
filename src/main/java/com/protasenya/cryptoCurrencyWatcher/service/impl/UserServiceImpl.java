package com.protasenya.cryptoCurrencyWatcher.service.impl;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserCreateDto;
import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserDto;
import com.protasenya.cryptoCurrencyWatcher.domain.mapper.UserMapper;
import com.protasenya.cryptoCurrencyWatcher.domain.model.User;
import com.protasenya.cryptoCurrencyWatcher.exception.ResourceAlreadyExistsException;
import com.protasenya.cryptoCurrencyWatcher.repository.UserRepository;
import com.protasenya.cryptoCurrencyWatcher.service.CryptoCurrencyService;
import com.protasenya.cryptoCurrencyWatcher.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CryptoCurrencyService cryptoCurrencyService;

    @Override
    public UserDto notifyUser(UserCreateDto userDto) {
        if (!userRepository.existsByUsername(userDto.getUsername())) {
            User user = userMapper.fromDto(userDto);
            BigDecimal price = cryptoCurrencyService.getPriceFromCoinLore(userDto.getCoinSymbol());
            user.setCoinPricePerRegistration(price);
            User createdUser = userRepository.save(user);

            return userMapper.fromDto(createdUser);
        } else {
            throw new ResourceAlreadyExistsException("User " + userDto.getUsername() + " already exists.");
        }
    }
}
