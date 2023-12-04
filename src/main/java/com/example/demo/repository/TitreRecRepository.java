package com.example.demo.repository;

import com.example.demo.entity.rec.TitreRecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitreRecRepository extends JpaRepository<TitreRecEntity, Long>{
}
