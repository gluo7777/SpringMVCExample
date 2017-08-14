package org.app.Repository;

import org.app.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findAccountByUserName(String userName);
    String findPasswordByUserName(String userName);
}
