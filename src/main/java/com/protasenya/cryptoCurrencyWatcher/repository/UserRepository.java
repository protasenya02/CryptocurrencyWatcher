package com.protasenya.cryptoCurrencyWatcher.repository;

import com.protasenya.cryptoCurrencyWatcher.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
