package com.codegym.agoda.repository;

import com.codegym.agoda.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo  extends JpaRepository<Account,Integer> {
}
