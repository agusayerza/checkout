package com.acs.mercadopaginho.mercadopaginhobackend.service;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test-stub")
public class StubPaymentService extends AbstractServiceTest{

    @Test
    public void test() throws Exception {
        String uri = "/payment";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    }
}