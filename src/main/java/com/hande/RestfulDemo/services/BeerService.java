package com.hande.RestfulDemo.services;

import com.hande.RestfulDemo.model.Beer;

import java.util.List;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
public interface BeerService {
    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, Beer beer);
}
