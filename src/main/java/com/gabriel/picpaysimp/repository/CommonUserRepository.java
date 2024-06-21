package com.gabriel.picpaysimp.repository;

import com.gabriel.picpaysimp.domain.common.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {
}
