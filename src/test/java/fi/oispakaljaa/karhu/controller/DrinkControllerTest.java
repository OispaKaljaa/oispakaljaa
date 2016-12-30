package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.repository.AccountRepository;
import fi.oispakaljaa.karhu.repository.BarRepository;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import java.util.Random;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();

        String name = UUID.randomUUID().toString().substring(0, 6);
        String address = UUID.randomUUID().toString().substring(0, 6);

        mockMvc.perform(post("/api/bars/").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"" + name + "\",\n\"address\":\"" + address + "\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void apiWorks() throws Exception {
        mockMvc.perform(get("/api/bars/1/drinks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    public void postDrink() throws Exception {
        String drinkName = UUID.randomUUID().toString().substring(0, 6);
        Random random = new Random();
        Integer price = random.nextInt(5);
        Integer volume = random.nextInt(5);
        Integer alcoholPercentage = random.nextInt(500);

        mockMvc.perform(post("/api/bars/1/drinks").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"" + drinkName + "\",\n\"drinkType\":\"beer\",\n\"price\":\"" + price + "\",\n\"volume\":\"" + volume + "\",\n\"alcoholPercentage\":\"" + alcoholPercentage + "\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/bars/1/drinks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

}
