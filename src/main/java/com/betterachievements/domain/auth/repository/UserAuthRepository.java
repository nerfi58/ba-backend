package com.betterachievements.domain.auth.repository;

import com.betterachievements.domain.auth.model.UserAuth;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@Repository
@ApplicationScoped
public interface UserAuthRepository extends CrudRepository<UserAuth, UUID> {

    @Query("select count(u) > 0 from UserAuth u where u.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
