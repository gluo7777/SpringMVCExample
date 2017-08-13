package org.app.dao;

import org.app.entity.Account;

import java.util.Collection;

public interface AccountDao {

    Collection<Account> getAccounts();

    Account getAccountById(Long userId);

    void addAccount(Account account);

    void deleteAccountById(Long userId);

    void updateAccount(Long userId, Account account);

}
