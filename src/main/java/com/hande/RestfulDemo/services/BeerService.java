package com.hande.RestfulDemo.services;

import com.hande.RestfulDemo.model.BeerDTO;

import java.util.List;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
public interface BeerService {
    List<BeerDTO> listBeers();

    BeerDTO getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    void updateBeerById(UUID beerId, BeerDTO beer);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}
