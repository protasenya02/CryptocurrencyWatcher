package com.protasenya.cryptoCurrencyWatcher.service.impl;

import com.protasenya.cryptoCurrencyWatcher.domain.dto.CryptoCurrencyDto;
import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserCreateDto;
import com.protasenya.cryptoCurrencyWatcher.domain.dto.UserDto;
import com.protasenya.cryptoCurrencyWatcher.domain.mapper.CryptoCurrencyMapper;
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
    private final CryptoCurrencyMapper currencyMapper;

    @Override
    public UserDto notifyUser(UserCreateDto userDto) {
        if (!userRepository.existsByUsername(userDto.getUsername())) {
            log.debug("Create user: " + userDto);
            User user = userMapper.fromDto(userDto);
            String symbol = userDto.getCoinSymbol();
            CryptoCurrencyDto currencyDto = cryptoCurrencyService.findBySymbol(symbol);
            user.setCryptoCurrency(currencyMapper.fromDto(currencyDto));
            BigDecimal price = cryptoCurrencyService.getPriceFromCoinLore(symbol);
            user.setCoinPricePerRegistration(price);
            User createdUser = userRepository.save(user);

            return userMapper.fromDto(createdUser);
        } else {
            throw new ResourceAlreadyExistsException("User " + userDto.getUsername() + " already exists.");
        }
    }
}
