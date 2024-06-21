package com.gabriel.picpaysimp.repository;

import com.gabriel.picpaysimp.domain.retailer.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Long> {
}
