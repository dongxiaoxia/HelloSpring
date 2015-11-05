package xyz.dongxiaoxia.hellospring.account.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.dongxiaoxia.hellospring.BasicTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2015/11/5.
 */
public class UserControllerTest extends BasicTest {

    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get("/api/user/get").param("id", "123")).andExpect(status().is(200));
    }

    @Test
    public void getCountTest() throws Exception {
        mockMvc.perform(get("/api/user/count")).andExpect(status().is(200));
    }

}
