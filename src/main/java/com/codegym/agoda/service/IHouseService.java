package com.codegym.agoda.service;

import com.codegym.agoda.dto.HouseDto;
import com.codegym.agoda.dto.PaginateRequest;
import com.codegym.agoda.model.House;
import com.codegym.agoda.model.Room;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface IHouseService extends IGenerateService<House>{
    Page<House> findAll(HouseDto house, PaginateRequest paginateRequest);
    House saveHouse(HouseDto houseDto) throws IOException;
    void deleteHouse(int id);
}
