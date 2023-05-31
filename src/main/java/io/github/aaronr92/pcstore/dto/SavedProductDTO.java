package io.github.aaronr92.pcstore.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.aaronr92.pcstore.entity.Category;
import io.github.aaronr92.pcstore.entity.Manufacturer;
import io.github.aaronr92.pcstore.entity.ProductProperty;

import java.util.List;

public record SavedProductDTO(
        Manufacturer manufacturer,
        Category category,
        float price,
        String serialNumber,
        long quantity,
        List<PropertyDTO> property
) {}
