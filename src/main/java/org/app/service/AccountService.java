package org.app.service;

import org.app.Repository.AccountRepository;
import org.app.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Collection<Account> getAccounts() {
        List<Account> accounts = new LinkedList<>();
        for(Account account : accountRepository.findAll())
            accounts.add(account);
        return accounts;
    }

    public Account getAccountById(Long userId) {
        return accountRepository.findOne(userId);
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccountById(Long userId) {
        accountRepository.delete(userId);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    /**
     * Check if username already exists before adding
     * @param account
     * @return
     */
    public boolean exists(Account account) {
        return accountRepository.findAccountByUserName(account.getUserName()) != null;
    }
}
