package com.hande.RestfulDemo.mappers;

import com.hande.RestfulDemo.entities.Beer;
import com.hande.RestfulDemo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
