package com.hande.RestfulDemo.repositories;

import com.hande.RestfulDemo.entities.Beer;
import com.hande.RestfulDemo.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
 * 15/01/2025
 * handebarkan
 */
@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong() {
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("sddeffffsdsdsdsdmskdskdkdmkmfkmfkmekfmekmfkemfkmefekfekmfkefkemfkemfefefmefemfemfemfemmfemffem")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("232323")
                    .price(new BigDecimal("11.22"))
                    .build());
            beerRepository.flush();
            assertThat(savedBeer).isNotNull();
            assertThat(savedBeer.getId()).isNotNull();
        });
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My Beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("23232")
                        .price(new BigDecimal("12.44"))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

}