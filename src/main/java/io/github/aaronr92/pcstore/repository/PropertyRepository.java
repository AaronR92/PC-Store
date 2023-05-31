package io.github.aaronr92.pcstore.repository;

import io.github.aaronr92.pcstore.entity.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
    Optional<Property> findPropertyByNameIgnoreCase(String name);
}
