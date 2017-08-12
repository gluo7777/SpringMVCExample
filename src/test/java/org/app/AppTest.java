package org.app;

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


import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.hasSize;
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

}
