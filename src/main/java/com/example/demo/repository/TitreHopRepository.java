package com.example.demo.repository;
import com.example.demo.entity.hop.TitreHopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitreHopRepository extends JpaRepository<TitreHopEntity, Long> {
}
