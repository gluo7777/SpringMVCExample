package org.app.service;

import org.app.dao.AccountDao;
import org.app.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {

    @Qualifier("TestAccountDaoImpl")
    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public Collection<Account> getAccounts() {
        return accountDao.getAccounts();
    }

    public Account getAccountById(int userId) {
        return accountDao.getAccountById(userId);
    }

    public void addAccount(Account account) {
        if (!exists(account.getUserId()))
            accountDao.addAccount(account);
    }

    public void deleteAccountById(int userId) {
        if (exists(userId))
            accountDao.deleteAccountById(userId);
    }

    public void updateAccount(Account account) {
        if (exists(account.getUserId()))
            accountDao.updateAccount(account);
    }

    private boolean exists(int userId) {
        return getAccountById(userId) != null;
    }
}
