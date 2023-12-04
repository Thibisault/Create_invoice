package com.example.demo.repository;

import com.example.demo.entity.rnf.TitreRnfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitreRnfRepository extends JpaRepository<TitreRnfEntity, Long> {
}
