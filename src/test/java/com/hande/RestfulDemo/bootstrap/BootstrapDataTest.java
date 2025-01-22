package com.hande.RestfulDemo.bootstrap;

import com.hande.RestfulDemo.repositories.BeerRepository;
import com.hande.RestfulDemo.repositories.CustomerRepository;
import com.hande.RestfulDemo.services.BeerCsvService;
import com.hande.RestfulDemo.services.BeerCsvServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BeerCsvServiceImpl.class)
class BootstrapDataTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private BeerCsvService beerCsvService;

    BootstrapData bootstrapData;


    @BeforeEach
    void setUp() {

        bootstrapData = new BootstrapData(beerRepository, customerRepository, beerCsvService);
    }

    @Test
    void testRun() throws Exception {
        bootstrapData.run(null);

        assertThat(beerRepository.count()).isEqualTo(2413);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}