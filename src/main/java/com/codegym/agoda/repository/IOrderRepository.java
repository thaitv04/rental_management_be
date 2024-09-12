package com.codegym.agoda.repository;

import com.codegym.agoda.model.HouseAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<HouseAccount,Integer> {
    @Query("from HouseAccount as h where h.account.id = ?1")
    List<HouseAccount> findAllHistory(int id);

    @Query("from HouseAccount  as h where h.house.account.id = ?1")
    List<HouseAccount> findAllByIdHost(int id);

    @Query("from HouseAccount as h where h.house.id = ?1")
    List<HouseAccount> findTimes(int id);
    @Query("from HouseAccount  as h where h.house.id = ?1")
    List<HouseAccount> findAllByIdHouse(int id);
}
