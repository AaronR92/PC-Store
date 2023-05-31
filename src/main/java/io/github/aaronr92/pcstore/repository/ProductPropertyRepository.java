package io.github.aaronr92.pcstore.repository;

import io.github.aaronr92.pcstore.entity.Product;
import io.github.aaronr92.pcstore.entity.ProductProperty;
import io.github.aaronr92.pcstore.entity.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPropertyRepository extends CrudRepository<ProductProperty, Long> {
    Optional<ProductProperty> findProductPropertyByPropertyAndProduct(Property property, Product product);

    List<ProductProperty> findProductPropertiesByProduct(Product product);
}
