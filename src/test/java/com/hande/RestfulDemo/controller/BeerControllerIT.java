package com.hande.RestfulDemo.controller;

import com.hande.RestfulDemo.entities.Beer;
import com.hande.RestfulDemo.mappers.BeerMapper;
import com.hande.RestfulDemo.model.BeerDTO;
import com.hande.RestfulDemo.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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

    @Autowired
    BeerMapper beerMapper;

    @Test
    void testDeleteByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            controller.deleteById(UUID.randomUUID());
        });
    }

    @Transactional
    @Rollback
    @Test
    void testDeleteById() {
        Beer beer = beerRepository.findAll().get(0);

        ResponseEntity responseEntity = controller.deleteById(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(beerRepository.findById(beer.getId()).isEmpty());

    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            controller.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Transactional
    @Rollback
    @Test
    void updateExistingBeerTest() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);

        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = controller.updateById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Transactional
    @Rollback
    @Test
    void saveNewBeerTest() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();

        ResponseEntity responseEntity = controller.handlePost(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();
    }

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