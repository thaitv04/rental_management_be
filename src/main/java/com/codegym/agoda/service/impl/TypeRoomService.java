package com.codegym.agoda.service.impl;

import com.codegym.agoda.model.Room;
import com.codegym.agoda.model.TypeRoom;
import com.codegym.agoda.repository.ITypeRoomRepo;
import com.codegym.agoda.service.ITypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TypeRoomService implements ITypeRoomService {
    @Autowired
    private ITypeRoomRepo iTypeRoomRepo;

    @Override
    public Iterable<TypeRoom> findAll() {
        return iTypeRoomRepo.findAll();
    }

    @Override
    public Optional<TypeRoom> findById(int id) {
        return Optional.empty();
    }

    @Override
    public TypeRoom save(TypeRoom typeRoom) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
