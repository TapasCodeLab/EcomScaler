package org.scaler.usermicroservice.repositories;

import org.scaler.usermicroservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByValueAndDeleted(String value, boolean deleted);

    Optional<Token> findTokenByValueAndDeletedAndExpiryAtAfter(String value, boolean deleted, LocalDateTime expiry);

    @Query(value="select count(*) from userservice.token where user_id = :userid and deleted=0 and expiry_at>CURRENT_TIMESTAMP", nativeQuery = true)
    int countActiveTokenForUser(@Param("userid") Long id);

}
