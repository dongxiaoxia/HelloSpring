package xyz.dongxiaoxia.hellospring.account.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2015/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class UserControllerTest {

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get("/api/user/get")).andExpect(status().is(200));
    }

    @Test
    public void getCountTest() throws Exception {
        mockMvc.perform(get("/api/user/count")).andExpect(status().is(200));
    }

}
