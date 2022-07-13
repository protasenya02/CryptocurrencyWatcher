package com.protasenya.cryptoCurrencyWatcher.repository;

import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    Optional<CryptoCurrency> findBySymbol(String symbol);
}
