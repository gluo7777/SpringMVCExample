package org.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.controller.AccountController;
import org.app.entity.Account;
import org.app.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AppTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .build();
    }

    @Test
    public void test_get_all() throws Exception {
        // create mock data
        List<Account> accounts = Arrays.asList(
                new Account(1,"willk777","1234"),
                new Account(2,"willk888","abcd")
        );
        // configure mock response
        when(accountService.getAccounts()).thenReturn(accounts);
        // verify success, json type, size, fields of each element
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[0].userName", is("willk777")))
                .andExpect(jsonPath("$[1].userName", is("willk888")))
                .andExpect(jsonPath("$[0].password", is("1234")))
                .andExpect(jsonPath("$[1].password", is("abcd")));
        // verify that getAccounts was only called once
        verify(accountService,times(1)).getAccounts();
        // verify no more interactions
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void test_get_by_id() throws Exception {
        Account account = new Account(1,"willk777","1234");

        when(accountService.getAccountById(1)).thenReturn(account);

        mockMvc.perform(get("/accounts/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userName", is("willk777")))
                .andExpect(jsonPath("$.password",is("1234")));

        verify(accountService, times(1)).getAccountById(1);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {

        when(accountService.getAccountById(1)).thenReturn(null);

        mockMvc.perform(get("/accounts/{id}", 1))
                .andExpect(status().isNotFound());

        verify(accountService, times(1)).getAccountById(1);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void test_create_user_success() throws Exception {
        Account account = new Account(1,"willk777","1234");

        when(accountService.exists(account.getUserId())).thenReturn(false);
        doNothing().when(accountService).addAccount(account);

        mockMvc.perform(
                post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(account)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/accounts/")));

        verify(accountService, times(1)).exists(account.getUserId());
        verify(accountService, times(1)).addAccount(account);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void test_update_user_success() throws Exception {
        Account account = new Account(1,"willk777","1234");
        when(accountService.getAccountById(account.getUserId())).thenReturn(account);
        doNothing().when(accountService).updateAccount(account);
        mockMvc.perform(
                put("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(account)))
                .andExpect(status().isOk());
        verify(accountService, times(1)).getAccountById(account.getUserId());
        verify(accountService, times(1)).updateAccount(account);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void test_delete_user_success() throws Exception {
        Account account = new Account(1,"willk777","1234");
        when(accountService.getAccountById(account.getUserId())).thenReturn(account);
        doNothing().when(accountService).deleteAccountById(account.getUserId());
        mockMvc.perform(
                delete("/accounts/{id}", account.getUserId()))
                .andExpect(status().isOk());
        verify(accountService, times(1)).getAccountById(account.getUserId());
        verify(accountService, times(1)).deleteAccountById(account.getUserId());
        verifyNoMoreInteractions(accountService);
    }

    /**
     * Converts a java object into its json representation
     * Source: http://memorynotfound.com/unit-test-spring-mvc-rest-service-junit-mockito/
     * @param obj java object
     * @return json stirng
     */
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
