package xyz.dongxiaoxia.hellospring.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2015/11/28.
 */
public class UserControllerTest extends BasicTest {

    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void addTest() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        mockMvc.perform(post("/api/user/add").param("username", "test")).andExpect(status().is2xxSuccessful());
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
