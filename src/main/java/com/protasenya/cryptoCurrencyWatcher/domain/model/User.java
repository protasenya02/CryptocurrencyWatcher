package com.protasenya.cryptoCurrencyWatcher.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "cryptocurrency_id",referencedColumnName = "id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    @Column(name = "coin_price_per_registration", nullable = false)
    private BigDecimal coinPricePerRegistration;
}
