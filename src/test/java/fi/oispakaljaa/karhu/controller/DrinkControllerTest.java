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


/**
 * Created by julkku on 12/18/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrinkControllerTest {


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
//        mockMvc.perform(get("/api/drinks"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }
//
//    @Test
//    public void listDrinksByBar() throws Exception {
//
//    }
//
//@Test
//public void postDrink() throws Exception {
//    String name = "Karjala";
//
//
//    mockMvc.perform(post("/api/messages").content("{\"message\":\"" + message + "\",\"person\":\"" + person + "\"}"))
//            .andExpect(status().is2xxSuccessful());
//}
//
//    @Test
//    public void getDrink() throws Exception {
//
//    }
//
//    @Test
//    public void deleteDrink() throws Exception {
//
//    }

}