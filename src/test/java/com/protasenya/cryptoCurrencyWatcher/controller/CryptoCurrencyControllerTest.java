package com.protasenya.cryptoCurrencyWatcher.controller;


import com.protasenya.cryptoCurrencyWatcher.CryptocurrencyWatcherApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CryptocurrencyWatcherApplication.class)
public class CryptoCurrencyControllerTest {

}
