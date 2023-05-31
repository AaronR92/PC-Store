package io.github.aaronr92.pcstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDTO(
        long manufacturerId,
        long categoryId,
        float price,
        String serialNumber,
        long quantity,
        String propertyName,
        String propertyValue
) {}
