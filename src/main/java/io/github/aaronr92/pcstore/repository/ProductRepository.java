package io.github.aaronr92.pcstore.repository;

import io.github.aaronr92.pcstore.entity.Category;
import io.github.aaronr92.pcstore.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findProductsByCategory(Category category);

}
