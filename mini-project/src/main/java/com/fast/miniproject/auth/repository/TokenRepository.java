package com.fast.miniproject.auth.repository;

import com.fast.miniproject.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,String> {

    boolean existsByToken(String token);

}
