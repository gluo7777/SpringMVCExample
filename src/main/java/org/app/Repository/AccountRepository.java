package org.app.Repository;

import org.app.entity.Account;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account,Long> {

}
