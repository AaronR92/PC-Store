package io.github.aaronr92.pcstore.service;

import io.github.aaronr92.pcstore.dto.ProductDTO;
import io.github.aaronr92.pcstore.dto.PropertyDTO;
import io.github.aaronr92.pcstore.entity.Product;
import io.github.aaronr92.pcstore.entity.ProductProperty;
import io.github.aaronr92.pcstore.entity.Property;
import io.github.aaronr92.pcstore.exception.ProductNotFoundException;
import io.github.aaronr92.pcstore.repository.ProductPropertyRepository;
import io.github.aaronr92.pcstore.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final ProductPropertyRepository productPropertyRepository;

    /**
     * Сохраняет новое свойство для товара с его значением, если свойста не существует
     * то создает новое свойство и так же добавляет значение
     * @param propertyDTO объект нового свойства
     * @param product товар
     * @return сохраненное свойство товара
     */
    public ProductProperty save(PropertyDTO propertyDTO, Product product) {
        Property property = propertyRepository
                .findPropertyByNameIgnoreCase(propertyDTO.name())
                .orElseGet(() -> propertyRepository
                        .save(Property.builder().name(propertyDTO.name()).build()));

        return saveProductProperty(product, property, propertyDTO.value());
    }

    /**
     * Сохраняет свойство для товара с его значением
     * @param product товар
     * @param property свойство
     * @param value значение
     * @return объект характеристики товара
     */
    private ProductProperty saveProductProperty(Product product, Property property, String value) {
        return productPropertyRepository.save(
                ProductProperty.builder()
                        .property(property)
                        .product(product)
                        .propertyValue(value)
                        .build()
        );
    }

    /**
     * Сохраняет новое свойство для товара с его значением, если свойста не существует
     * то создает новое свойство и так же добавляет значение
     * @param productDTO объект передачи данных для товара
     * @param product товар
     * @return объект характеристики товара
     */
    public ProductProperty saveProperties(ProductDTO productDTO, Product product) {
        PropertyDTO propertyDTO = new PropertyDTO(
                productDTO.propertyName(),
                productDTO.propertyValue()
        );

        return save(propertyDTO, product);
    }

    /**
     * Находит заданное свойство товара
     * @param product товар
     * @param property свойство
     * @return объект характеристики товара
     */
    public ProductProperty getProductProperty(Product product, Property property) {
        return productPropertyRepository.findProductPropertyByPropertyAndProduct(
                property,
                product
        ).orElseThrow(ProductNotFoundException::new);
    }

    /**
     * Находит все свойства товара
     * @param product товар
     * @return все объекты характеристик товара
     */
    public List<ProductProperty> getProductProperties(Product product) {
        return productPropertyRepository.findProductPropertiesByProduct(product);
    }

    /**
     * Находит все характеристики указанного товара в представлении объекта передачи данных
     * @param product товар
     * @return все характеристики товара в представлении объекта передачи данных
     */
    public List<PropertyDTO> getProductPropertiesDTO(Product product) {
        List<PropertyDTO> propertyDTOS = new LinkedList<>();

        getProductProperties(product).forEach(productProperty -> {
            propertyDTOS.add(new PropertyDTO(
                    productProperty.getProperty().getName(),
                    productProperty.getPropertyValue()
            ));
        });

        return propertyDTOS;
    }

}
