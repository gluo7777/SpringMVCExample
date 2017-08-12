package org.app.dao;

import org.app.entity.Account;

import java.util.Collection;

public interface AccountDao {

    Collection<Account> getAccounts();

    Account getAccountById(int userId);

    void addAccount(Account account);

    void deleteAccountById(int userId);

    void updateAccount(Account account);

}
