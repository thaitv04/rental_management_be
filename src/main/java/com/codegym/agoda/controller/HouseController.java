package com.codegym.agoda.controller;

import com.codegym.agoda.dto.HouseDto;
import com.codegym.agoda.dto.PaginateRequest;
import com.codegym.agoda.model.*;
import com.codegym.agoda.repository.IHouseRepository;
import com.codegym.agoda.repository.IImageRepo;
import com.codegym.agoda.repository.IRoomRepo;
import com.codegym.agoda.repository.ITypeRoomRepo;
import com.codegym.agoda.service.impl.HouseService;
import com.codegym.agoda.service.impl.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController("apiHouseController")
@RequestMapping("/api/house")
@CrossOrigin
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private IImageRepo iImageRepo;
    @Autowired
    private IRoomRepo iRoomRepo;



    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ResponseEntity<List<House>> listHouse(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "numberOfBedRoom", required = false) String numberOfBedRoom,
            @RequestParam(name = "numberOfBathRoom", required = false) String numberOfBathRoom,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "priceFrom", required = false) String priceFrom,
            @RequestParam(name = "priceTo", required = false) String priceTo,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "100") int size
    ) {
        HouseDto dto = new HouseDto()
                .setName(name)
                .setAddress(address)
                .setNumberOfBedRoom(numberOfBedRoom)
                .setNumberOfBathRoom(numberOfBathRoom)
                .setStatus(status)
                .setPriceForm(priceFrom)
                .setPriceTo(priceTo)
                ;
        PaginateRequest paginateRequest = new PaginateRequest(page, size);
        Page<House> pages = houseService.findAll(dto, paginateRequest);
        return new ResponseEntity<>(pages.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> findHouseById(@PathVariable int id) {
        Optional<House> customerOptional = houseService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                // Lưu trữ từng file vào thư mục cụ thể
                file.transferTo(new File(fileUpload + file.getOriginalFilename()));
            }
            return ResponseEntity.ok("Files uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files");
        }
    }
    @PostMapping()
    public ResponseEntity<House> save(@ModelAttribute HouseDto houseDto) throws IOException {
        House house = houseService.saveHouse(houseDto);
        return new ResponseEntity<>(house, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<House> update(@ModelAttribute HouseDto dto, @PathVariable int id) throws IOException {
        if (houseService.findById(id).isPresent()) {
            House house = houseService.findById(id).get();
            house.setName(dto.getName());
            house.setAddress(dto.getAddress());
            house.setDescription(dto.getDescription());
            house.setRevenue(dto.getRevenue());
            house.setPrice(dto.getPrice());
            house.setNumberOfBedRoom(dto.getNumberOfBedRoom());
            house.setNumberOfBathRoom(dto.getNumberOfBathRoom());

            Iterable<Room> roomList = iRoomRepo.findAllByIdHouse(dto.getId());
            for (Room room1 : roomList) {
                iRoomRepo.deleteById(room1.getId());
            }
            List<Image> images = iImageRepo.findByIdHouse(id);
            for (Image img : images) {
                iImageRepo.delete(img);
            }
            return new ResponseEntity<>(houseService.saveHouse(dto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable int id) {
        // Xử lý logic xóa tài nguyên ở đây
        houseService.deleteHouse(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
