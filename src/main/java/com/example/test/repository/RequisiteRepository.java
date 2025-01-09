package com.example.test.repository;

import com.example.test.model.entity.Requisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequisiteRepository extends JpaRepository<Requisite, UUID> {
}
