package com.codegym.agoda.service.impl;

import com.codegym.agoda.dto.HouseDto;
import com.codegym.agoda.dto.HouseSpec;
import com.codegym.agoda.dto.PaginateRequest;
import com.codegym.agoda.dto.RoomDto;
import com.codegym.agoda.model.*;
import com.codegym.agoda.repository.*;
import com.codegym.agoda.service.IHouseService;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService implements IHouseService {
    @Autowired
    private IHouseRepository iHouseRepository;
    @Autowired
    private IRoomRepo iRoomRepo;
    @Autowired
    private ITypeRoomRepo iTypeRoomRepo;
    @Autowired
    private IImageRepo iImageRepo;
    @Autowired
    private IAccountRepo iAccountRepo;
    @Autowired
    private IStatusRepo iStatusRepo;
    @Autowired
    private IOrderRepository iOrderRepository;

    @Value("${file-upload}")
    private String fileUpload;

    @Override
    public Iterable<House> findAll() {
        return iHouseRepository.findAll();
    }

    @Override
    public Optional<House> findById(int id) {
        return iHouseRepository.findById(id);
    }

    @Override
    public House save(House house) {
        return iHouseRepository.save(house);
    }

    @Override
    public void delete(int id) {
        iHouseRepository.deleteById(id);
    }

    @Override
    public Page<House> findAll(HouseDto dto, PaginateRequest paginateRequest) {
        Specification<House> specification = new HouseSpec(dto);
        Pageable pageable = PageRequest.of(paginateRequest.getPage(), paginateRequest.getSize());
        return iHouseRepository.findAll(specification, pageable);
    }

    @Override
    public void deleteHouse(int id) {
//       House house=iHouseRepository.findById(id).get();
        List<Room> rooms=iRoomRepo.findAllByIdHouse(id);
        for (Room room : rooms) {
            iRoomRepo.deleteById(room.getId());
        }
        List<Image> images = iImageRepo.findByIdHouse(id);
        for (Image image : images) {
            iImageRepo.deleteById(image.getId());
        }
        List<HouseAccount> houseAccounts = iOrderRepository.findAllByIdHouse(id);
        for (HouseAccount houseAccount : houseAccounts) {
            iOrderRepository.deleteById(houseAccount.getId());

        }
        iHouseRepository.deleteById(id);

    }
    @Override
    public House saveHouse(HouseDto houseDto) throws IOException {
//        them thang nguoi dang nha
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowWithoutSeconds = now.truncatedTo(ChronoUnit.MINUTES);

// them nha
        House house = houseDto.toHouse();
        house.setAccount(iAccountRepo.findById(houseDto.getAccountId()).get());
        house.setStatus(iStatusRepo.findById(1).get());
        house.setCreatedAt(nowWithoutSeconds);
        house = iHouseRepository.save(house);

        //them phong

        for (RoomDto roomDto : houseDto.getRooms()) {
            Room room = new Room();
            room.setName(roomDto.getName());
//            lấy id thằng typeRoom
            room.setTypeRoom(iTypeRoomRepo.findById(roomDto.getTypeId()).get());
            room.setHouse(house);
            iRoomRepo.save(room);
        }
        if (houseDto.getImage() == null) {
            Image image = new Image();
            image.setNameImage("upload/default.jpg");
            image.setHouse(house);
            iImageRepo.save(image);
        }
        MultipartFile[] multipartFile = houseDto.getImage();

        for (MultipartFile file : multipartFile){
            String filename = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + filename));

            Image image = new Image();
            image.setNameImage(filename);
            image.setHouse(house);
            iImageRepo.save(image);
        }
        return house;

    }

}
