package com.codegym.agoda.repository;

import com.codegym.agoda.model.Image;
import com.codegym.agoda.model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IImageRepo extends JpaRepository<Image, Integer> {
    @Query("from Image as i where i.house.id = ?1")
    List<Image> findByIdHouse(int id);
}
