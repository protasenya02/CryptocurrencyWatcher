package com.protasenya.cryptoCurrencyWatcher.repository;

import com.protasenya.cryptoCurrencyWatcher.domain.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    CryptoCurrency findBySymbol(String symbol);

    boolean existsBySymbol(String symbol);
}
