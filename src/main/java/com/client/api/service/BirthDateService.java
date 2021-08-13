package com.client.api.service;

import java.time.LocalDate;

public interface BirthDateService {

    int getAge(final LocalDate birthDate);
}
