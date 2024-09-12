package com.codegym.agoda.repository;

import com.codegym.agoda.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepo extends JpaRepository<Status,Integer> {
}
