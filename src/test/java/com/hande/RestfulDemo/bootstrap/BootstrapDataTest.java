package com.hande.RestfulDemo.bootstrap;

import com.hande.RestfulDemo.repositories.BeerRepository;
import com.hande.RestfulDemo.repositories.CustomerRepository;
import com.hande.RestfulDemo.services.BeerCsvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapDataTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootstrapData bootstrapData;
    @Autowired
    private BeerCsvService beerCsvService;

    @BeforeEach
    void setUp() {

        bootstrapData = new BootstrapData(beerRepository, customerRepository, beerCsvService);
    }

    @Test
    void testRun() throws Exception {
        bootstrapData.run(null);

        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}