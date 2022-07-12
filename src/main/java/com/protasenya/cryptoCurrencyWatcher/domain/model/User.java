package com.protasenya.cryptoCurrencyWatcher.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "users_seq", sequenceName = "SEQ_USER", allocationSize = 1)
    private Long id;

    @Column(name = "username", nullable = false, length = 60)
    private String username;

    @Column(name = "coin_symbol", nullable = false, length = 60)
    private String coinSymbol;

    @Column(name = "coin_price_per_registration", nullable = false)
    private BigDecimal coinPricePerRegistration;
}
