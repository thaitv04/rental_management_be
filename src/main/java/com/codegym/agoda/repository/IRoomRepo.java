package com.codegym.agoda.repository;

import com.codegym.agoda.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IRoomRepo extends JpaRepository<Room,Integer> {
    @Query("from Room as r where r.house.id = ?1")
    List<Room> findAllByIdHouse (int id);
}
