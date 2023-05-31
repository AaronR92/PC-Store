package io.github.aaronr92.pcstore.repository;

import io.github.aaronr92.pcstore.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> { }
