package com.hande.RestfulDemo.controller;

import com.hande.RestfulDemo.model.Beer;
import com.hande.RestfulDemo.model.BeerStyle;
import com.hande.RestfulDemo.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.HyperlinkEvent;
import java.util.List;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
@Slf4j
@RequiredArgsConstructor
@RestController //convert to json body
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService; //no need to generate constructor for DI, lombok did it with AllArgs

    @PatchMapping("{beerId}")
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.patchBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{beerId}")
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.updateBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/beer" + savedBeer.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId);
    }


}
