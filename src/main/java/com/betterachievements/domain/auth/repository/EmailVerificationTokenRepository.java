package com.betterachievements.domain.auth.repository;

import com.betterachievements.domain.auth.model.EmailVerificationToken;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@Repository
@ApplicationScoped
public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, Long> {
    @Query("SELECT t FROM EmailVerificationToken t JOIN FETCH t.userAuth WHERE t.token = :token")
    Optional<EmailVerificationToken> findByTokenWithUserAuth(@Param("token") String token);

    @Query("DELETE FROM EmailVerificationToken t WHERE t.token = :token")
    void deleteByToken(@Param("token") String token);
}
