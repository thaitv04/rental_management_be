package com.codegym.agoda.dto;

import com.codegym.agoda.model.House;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HouseSpec implements Specification<House> {
    private HouseDto dto;

    public HouseSpec(HouseDto dto) {
        this.dto = dto;
    }

    @Override
    public Predicate toPredicate(Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // chứa danh sách các điều kiện
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if (dto.getName() != null) {
            predicateList.add(criteriaBuilder.like(root.get("name"),"%" + dto.getName() + "%"));
        }

        if (dto.getAddress() != null) {
            predicateList.add(criteriaBuilder.like(root.get("address"), "%" + dto.getAddress() + "%"));
        }

        if (dto.getNumberOfBedRoom() != 0) {
            predicateList.add(criteriaBuilder.equal(root.get("numberOfBedRoom"), dto.getNumberOfBedRoom()));
        }
        if (dto.getNumberOfBathRoom() != 0) {
            predicateList.add(criteriaBuilder.equal(root.get("numberOfBathRoom"), dto.getNumberOfBathRoom()));
        }

        if (dto.getPriceForm() != 0) {
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), dto.getPriceForm()));
        }

        if (dto.getPriceTo() != 0) {
            predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), dto.getPriceTo()));
        }

        if (dto.getStatus() != 0) {
            predicateList.add(criteriaBuilder.equal(root.join("status").get("id"), dto.getStatus()));
        }

        if (!predicateList.isEmpty()) {
            query.where(predicateList.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
