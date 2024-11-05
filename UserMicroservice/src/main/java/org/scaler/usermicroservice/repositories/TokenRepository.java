package org.scaler.usermicroservice.repositories;

import org.scaler.usermicroservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
