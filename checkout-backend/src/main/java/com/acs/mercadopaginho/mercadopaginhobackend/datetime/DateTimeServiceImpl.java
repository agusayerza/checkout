package com.acs.mercadopaginho.mercadopaginhobackend.datetime;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTimeServiceImpl implements DateTimeService {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
