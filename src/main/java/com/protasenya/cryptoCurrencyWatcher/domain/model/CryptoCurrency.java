package com.protasenya.cryptoCurrencyWatcher.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "cryptocurrencies")
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {

    @Id
    private Long id;

    @Column(name = "symbol", nullable = false, length = 60)
    private String symbol;

    @Column(name = "price_usd", nullable = false)
    private BigDecimal priceUSD;

    @OneToMany(mappedBy = "cryptoCurrency", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
