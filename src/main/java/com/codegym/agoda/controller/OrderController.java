package com.codegym.agoda.controller;

import com.codegym.agoda.dto.OrderDto;
import com.codegym.agoda.model.House;
import com.codegym.agoda.model.HouseAccount;
import com.codegym.agoda.model.TypeRoom;
import com.codegym.agoda.repository.IAccountRepo;
import com.codegym.agoda.repository.IHouseRepository;
import com.codegym.agoda.repository.IOrderRepository;
import com.codegym.agoda.repository.IStatusRepo;
import com.codegym.agoda.service.impl.HouseService;
import com.codegym.agoda.service.impl.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController("apiOrderController")
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IStatusRepo iStatusRepo;
    @Autowired
    private IHouseRepository iHouseRepository;
    @Autowired
    private IAccountRepo iAccountRepo;
    @Autowired
    private HouseService houseService;

    @PostMapping()
    public ResponseEntity<HouseAccount> saveOrder(@RequestBody OrderDto orderDto) throws ParseException {
        return new ResponseEntity<>(orderService.saveOrder(orderDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<HouseAccount>> findOrderById(@PathVariable int id) {
        List<HouseAccount> houseAccounts = orderService.findAllHistory(id);
        if (houseAccounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(houseAccounts, HttpStatus.OK);
    }

    @GetMapping("/time/{id}")
    public ResponseEntity<List<HouseAccount>> findTimes(@PathVariable int id) {
        List<HouseAccount> houseAccounts = orderService.findTimes(id);
        if (houseAccounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(houseAccounts, HttpStatus.OK);
    }

    @GetMapping("/host/{id}")
    public ResponseEntity<List<HouseAccount>> findById(@PathVariable int id) {
        List<HouseAccount> houseAccounts = orderService.findAllByIdHost(id);
        if (houseAccounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(houseAccounts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseAccount> updateStatus(@RequestBody OrderDto dto, @PathVariable int id) {
        HouseAccount houseAccount1 = iOrderRepository.findById(id).get();
        houseAccount1.setTimeEnd(dto.getTimeEnd());
        houseAccount1.setTimeStart(dto.getTimeStart());
        houseAccount1.setTotal(dto.getTotal());
        houseAccount1.setHouse(iHouseRepository.findById(dto.getIdHouse()).get());
        houseAccount1.setAccount(iAccountRepo.findById(dto.getIdAccount()).get());
        orderService.checkStatus(houseAccount1);
        return new ResponseEntity<>(houseAccount1, HttpStatus.OK);
    }

    @PutMapping("/yes/{id}")
    public ResponseEntity<HouseAccount> updateOrder(@RequestBody OrderDto orderDto, @PathVariable int id) {

        if (orderDto.getTotal() == 1) {
            HouseAccount houseAccount1 = iOrderRepository.findById(orderDto.getId()).get();
            houseAccount1.setStatus(iStatusRepo.findById(2).get());
            iOrderRepository.save(houseAccount1);

            House house = houseService.findById(houseAccount1.getHouse().getId()).get();
            house.setStatus(iStatusRepo.findById(2).get());
            houseService.save(house);

            return new ResponseEntity<>(houseAccount1, HttpStatus.OK);
        } else {
            HouseAccount houseAccount2 = iOrderRepository.findById(orderDto.getId()).get();
            houseAccount2.setStatus(iStatusRepo.findById(5).get());
            iOrderRepository.save(houseAccount2);


            return new ResponseEntity<>(houseAccount2, HttpStatus.OK);
        }
    }
}
