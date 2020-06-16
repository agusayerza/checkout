package com.acs.mercadopaginho.mercadopaginhobackend.service;

import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPayerDto;
import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test-fake")
public class FakePaymentService extends AbstractServiceTest{

    @Test
    public void test() throws Exception {
        String uri = "/payment";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    }
}
