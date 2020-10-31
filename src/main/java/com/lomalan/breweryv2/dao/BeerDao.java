package com.lomalan.breweryv2.dao;

import com.lomalan.breweryv2.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerDao extends PagingAndSortingRepository<Beer, UUID> {

}
