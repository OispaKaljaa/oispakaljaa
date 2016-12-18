package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.repository.AccountRepository;
import fi.oispakaljaa.karhu.repository.BarRepository;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created by julkku on 12/18/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarControllerTest {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private WebApplicationContext webAppContext;
    @Autowired
    private ListableBeanFactory listableBeanFactory;


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void apiWorks() throws Exception {
        mockMvc.perform(get("/api/bars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

//    @Test
//    public void list() throws Exception {
//    }
//
//    @Test
//    public void getBar() throws Exception {
//
//    }
//
//    @Test
//    public void postBar() throws Exception {
//
//    }
//
//    @Test
//    public void deleteBar() throws Exception {
//
//    }

}