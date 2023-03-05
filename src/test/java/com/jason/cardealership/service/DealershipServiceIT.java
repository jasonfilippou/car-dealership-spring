package com.jason.cardealership.service;

import com.jason.cardealership.persistence.model.Car;
import com.jason.cardealership.spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
@SpringBootTest(classes =  TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DealershipServiceIT {

    @Autowired
    private  DealershipService dealershipService;

    @Test
    public void whenNoTransactions_TotalRevenueIsAMil(){
        assertThat(dealershipService.getRevenue(), is(1_000_000L));
    }

    @Test
    public void whenMakingTransactions_revenueIsAffected(){
        Car car = Car.builder().year(2006).vin("1234").costInUSD(10_000L).make("Nissan").model("Sentra").build();
        dealershipService.buy(car);
        assertThat(dealershipService.getRevenue(), is(990_000L));
    }
}
