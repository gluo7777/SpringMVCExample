package org.app.daoImpl;

import org.app.dao.AccountDao;
import org.app.entity.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Qualifier("TestAccountDaoImpl")
public class TestAccountDaoImpl implements AccountDao {

    private static final Map<Long, Account> accounts;

    private static final AtomicLong counter = new AtomicLong(0);

    static {
        accounts = new HashMap<>();
        accounts.put(counter.incrementAndGet(), new Account("willk777", "112233"));
        accounts.put(counter.incrementAndGet(), new Account( "ice777", "1231"));
        accounts.put(counter.incrementAndGet(), new Account("joe743", "sdfs423"));
    }

    @Override
    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    @Override
    public Account getAccountById(Long userId) {
        return accounts.get(userId);
    }

    @Override
    public void addAccount(Account account) {
        accounts.put(account.getUserId(), account);
    }

    @Override
    public void deleteAccountById(Long userId) {
        accounts.remove(userId);
    }

    @Override
    public void updateAccount(Long userId, Account account) {
        accounts.put(userId, account);
    }
}
