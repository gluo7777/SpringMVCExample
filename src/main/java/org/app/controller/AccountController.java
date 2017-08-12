package org.app.controller;

import org.app.entity.Account;
import org.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("userId") int id) {
        return accountService.getAccountById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAccountById(@PathVariable("userId") int id) {
        accountService.deleteAccountById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
    }
}
