package com.jason.cardealership.service.impl;

import com.jason.cardealership.persistence.model.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DealershipServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private DealershipServiceImpl dealershipService;

    @BeforeAll
    public void setUp(){
        when(carRepository.findInCostRange())
    }
}
