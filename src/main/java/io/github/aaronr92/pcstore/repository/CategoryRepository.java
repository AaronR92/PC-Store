package io.github.aaronr92.pcstore.repository;

import io.github.aaronr92.pcstore.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> { }
