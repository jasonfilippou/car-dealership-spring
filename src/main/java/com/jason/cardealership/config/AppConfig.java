package com.jason.cardealership.config;

import com.jason.cardealership.DealershipService;
import com.jason.cardealership.persistence.model.repository.CarRepository;
import com.jason.cardealership.persistence.model.repository.impl.CarRepositoryImpl;
import com.jason.cardealership.service.impl.DealershipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private static final long DEFAULT_REVENUE = 1000000L;
    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context){
        this.context = context;
    }
    @Bean
    public CarRepository carRepository(){
        return new CarRepositoryImpl();
    }

    @Bean
    public DealershipService dealershipService(){
        return new DealershipServiceImpl(DEFAULT_REVENUE, context.getBean(CarRepository.class));
    }

}
