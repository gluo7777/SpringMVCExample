package daoImpl;

import dao.AccountDao;
import entity.Account;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAccountDaoImpl implements AccountDao {

    private static Map<Integer,Account> accounts;

    private static final AtomicInteger counter = new AtomicInteger(0);

    static{
        accounts = new HashMap<>();
        accounts.put(counter.incrementAndGet(),new Account("willk777","112233"));
        accounts.put(counter.incrementAndGet(),new Account("ice777","1231"));
        accounts.put(counter.incrementAndGet(),new Account("joe743","sdfs423"));
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    public Account getAccountById(int userId) {
        return accounts.get(userId);
    }

    public void addAccount(Account account) {
        accounts.put(counter.incrementAndGet(),account);
    }

    public void deleteAccountById(int userId) {
        if(accounts.containsKey(userId))
            accounts.remove(userId);
    }

    public void updateAccount(int userId, Account account) {
        accounts.put(userId,account);
    }
}
