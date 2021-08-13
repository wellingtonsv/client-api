package com.customer.api.service;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.client.api.service.impl.BirthDateServiceImpl;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@DisplayName("Test Service")
public class BirthDateServiceTest {

    @InjectMocks
    private BirthDateServiceImpl service;

    @Test
    public void whenAgeIsBirthTodaySuccess() {

        LocalDate birthDate = LocalDate.now();

        int result = service.getAge(birthDate);

        Assert.assertEquals(0, result);
    }

    @Test
    public void whenAgeIsBirthAfterSuccess() {

        LocalDate birthDate = LocalDate.of(1979, 02, 18);

        int result = service.getAge(birthDate);

        Assert.assertNotSame(0, result);
    }
}