package com.codegym.agoda.service.impl;

import com.codegym.agoda.model.Account;
import com.codegym.agoda.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private IAccountRepo iAccountRepo;

    public Account checkAccount(Account account){
        List<Account> accountList = iAccountRepo.findAll();
        for (Account acc : accountList){
            if (account.getUsername().equals(acc.getUsername()) && account.getPassword().equals(acc.getPassword())){
                return acc;
            }
        }

        return null;
    }
}
