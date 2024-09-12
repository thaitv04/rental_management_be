package com.codegym.agoda.dto;

import com.codegym.agoda.model.*;
import com.codegym.agoda.repository.IAccountRepo;
import com.codegym.agoda.repository.IStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class HouseDto {

    private int id;
    private String name;
    private String address;
    private String description;
    private double price;
    private String numberOfBedRoom;
    private String numberOfBathRoom;
    //doanh thu
    private List<RoomDto> rooms;
    private double revenue;

    private int accountId;

    private String priceForm;
    private String priceTo;
    private String status;
    private LocalDateTime createdAt;

    public int getStatus() {
        if (status == null || status.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(status);
    }

    public HouseDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getPriceForm() {
        if (priceForm == null || priceForm.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(priceForm);
    }

    public HouseDto setPriceForm(String priceForm) {
        this.priceForm = priceForm;
        return this;
    }

    public int getPriceTo() {
        if (priceTo == null || priceTo.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(priceTo);
    }

    public HouseDto setPriceTo(String priceTo) {
        this.priceTo = priceTo;
        return this;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    private MultipartFile[] image;

    public MultipartFile[] getImage() {
        return image;
    }

    public void setImage(MultipartFile[] image) {
        this.image = image;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> roomDto) {
        this.rooms = roomDto;
    }

    public HouseDto() {
    }

    public House toHouse() {
        House house = new House();
        house.setId(id);
        house.setName(name);
        house.setAddress(address);
        house.setDescription(description);
        house.setPrice(price);
        house.setRevenue(revenue);
        house.setNumberOfBedRoom(getNumberOfBedRoom());
        house.setNumberOfBathRoom(getNumberOfBathRoom());
        house.setCreatedAt(createdAt);
        return house;

    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public HouseDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    public HouseDto(int id, String name, String address, String description, double price, int numberOfBedRoom, int numberOfBathRoom, double revenue) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.price = price;
        this.revenue = revenue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public HouseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public HouseDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBedRoom() {
        if (numberOfBedRoom == null || numberOfBedRoom.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(numberOfBedRoom);
    }

    public HouseDto setNumberOfBedRoom(String numberOfBedRoom) {
        this.numberOfBedRoom = numberOfBedRoom;
        return this;
    }

    public int getNumberOfBathRoom() {
        if (numberOfBathRoom == null || numberOfBathRoom.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(numberOfBathRoom);
    }

    public HouseDto setNumberOfBathRoom(String numberOfBathRoom) {
        this.numberOfBathRoom = numberOfBathRoom;
        return this;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }


}
