package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.domain.Account;
import fi.oispakaljaa.karhu.repository.AccountRepository;
import fi.oispakaljaa.karhu.repository.BarRepository;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    // Test that authorization is needed for posting.
    @Test
    public void testPostBar() throws Exception {
        String name = UUID.randomUUID().toString().substring(0, 6);
        String address = UUID.randomUUID().toString().substring(0, 6);

        mockMvc.perform(post("/api/bars/").contentType(MediaType.APPLICATION_JSON).content("{\"bar\": {\"name\":\"" + name + "\",\"address\":\"" + address + "\"}, \"drink\": {\"name\":\"" + name + "\"}}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/bars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }


}
