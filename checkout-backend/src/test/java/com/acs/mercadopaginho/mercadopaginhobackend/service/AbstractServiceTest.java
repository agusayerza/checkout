package com.acs.mercadopaginho.mercadopaginhobackend.service;


import com.acs.mercadopaginho.mercadopaginhobackend.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@WebAppConfiguration
public abstract class AbstractServiceTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mvc;

    @Before
    public void setUpMvcAndMapper() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}
