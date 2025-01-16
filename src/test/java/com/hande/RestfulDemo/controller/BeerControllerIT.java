package com.hande.RestfulDemo.controller;

import com.hande.RestfulDemo.entities.Beer;
import com.hande.RestfulDemo.model.BeerDTO;
import com.hande.RestfulDemo.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController controller;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            controller.getBeerById(UUID.randomUUID());
        });
    }

    @Test
    void testGetById() {
        Beer beer =beerRepository.findAll().get(0);
        BeerDTO dto = controller.getBeerById(beer.getId());

        assertThat(dto).isNotNull();
    }

    @Test
    void testListBeers() {
        List<BeerDTO> dtos = controller.listBeers();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback //return the databse, to original state, otherwise the other test fails
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();;
        List<BeerDTO> dtos = controller.listBeers();

        assertThat(dtos.size()).isEqualTo(0);
    }
}