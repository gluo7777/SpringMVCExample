package dao;

import entity.Account;

import java.util.Collection;

public interface AccountDao {

    Collection<Account> getAccounts();

    Account getAccountById(int userId);

    void addAccount(Account account);

    void deleteAccountById(int userId);

    void updateAccount(int userId,Account account);

}
