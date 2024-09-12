package com.codegym.agoda.controller;

import com.codegym.agoda.model.TypeRoom;
import com.codegym.agoda.service.impl.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiTypeRoomController")
@RequestMapping("/api/type-room")
@CrossOrigin
public class    TypeRoomController {
    @Autowired
    private TypeRoomService typeRoomService;

    @GetMapping
    public ResponseEntity<Iterable<TypeRoom>> findAll() {
        List<TypeRoom> typeRooms = (List<TypeRoom>) typeRoomService.findAll();
        if (typeRooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeRooms, HttpStatus.OK);
    }
}
