package com.protasenya.cryptoCurrencyWatcher.repository;

import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Validated
@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Integer> {

    CryptoCurrency findBySymbol(String symbol);
}
