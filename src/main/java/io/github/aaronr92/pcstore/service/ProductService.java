package io.github.aaronr92.pcstore.service;

import io.github.aaronr92.pcstore.dto.ProductDTO;
import io.github.aaronr92.pcstore.dto.PropertyDTO;
import io.github.aaronr92.pcstore.dto.SavedProductDTO;
import io.github.aaronr92.pcstore.entity.*;
import io.github.aaronr92.pcstore.exception.ProductNotFoundException;
import io.github.aaronr92.pcstore.repository.ManufacturerRepository;
import io.github.aaronr92.pcstore.repository.ProductPropertyRepository;
import io.github.aaronr92.pcstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;
    private final PropertyService propertyService;

    /**
     * Сохраняет продукт используя его Data-Transfer-Object
     * @param productDTO новый продукт для сохранения
     * @return сохраненный продукт
     */
    public SavedProductDTO save(ProductDTO productDTO) {
        Manufacturer manufacturer = manufacturerService
                .getManufacturerById(productDTO.manufacturerId());
        Category category = categoryService
                .getCategoryById(productDTO.categoryId());

        Product product = Product.builder()
                .manufacturer(manufacturer)
                .category(category)
                .price(productDTO.price())
                .serialNumber(productDTO.serialNumber())
                .quantity(productDTO.quantity())
                .build();

        product = productRepository.save(product);

        var property = propertyService.saveProperties(productDTO, product);

        return new SavedProductDTO(
                product.getManufacturer(),
                product.getCategory(),
                product.getPrice(),
                product.getSerialNumber(),
                product.getQuantity(),
                Collections.singletonList(new PropertyDTO(
                        property.getProperty().getName(),
                        property.getPropertyValue()
                ))
        );
    }

    /**
     * Обновляет товар в соответствии с новым ProductDTO
     * @param id товара для обновления
     * @param productDTO новый вид товара для обновления
     */
    public void update(long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        Manufacturer manufacturer = manufacturerService
                .getManufacturerById(productDTO.manufacturerId());
        Category category = categoryService
                .getCategoryById(productDTO.categoryId());

        product.setManufacturer(manufacturer);
        product.setCategory(category);
        product.setPrice(productDTO.price());
        product.setSerialNumber(productDTO.serialNumber());
        product.setQuantity(productDTO.quantity());

        propertyService.saveProperties(productDTO, product);
        productRepository.save(product);
    }

    /**
     * Находит товар в базе данных по идентификатору
     * @param id идентификатор для поиска
     * @return товар
     */
    public SavedProductDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        var properties = propertyService.getProductPropertiesDTO(product);

        return new SavedProductDTO(
                product.getManufacturer(),
                product.getCategory(),
                product.getPrice(),
                product.getSerialNumber(),
                product.getQuantity(),
                properties
        );
    }

    /**
     * Находит все товары в базе данных
     * @return все товары
     */
    public List<SavedProductDTO> getAll() {
        return toSavedProductDTOS((List<Product>) productRepository.findAll());
    }

    /**
     * Находит все товары по заданной категории
     * @param categoryId идентификатор категории
     * @return все товары в этой категории
     */
    public List<SavedProductDTO> getAllByCategory(long categoryId) {
        var products = productRepository.findProductsByCategory(
                categoryService.getCategoryById(categoryId)
        );

        return toSavedProductDTOS(products);
    }

    /**
     * Превращает список товаров в их представление для пользователя
     * @param products список товаров
     * @return товары для представления пользователю
     */
    private List<SavedProductDTO> toSavedProductDTOS(
            List<Product> products
    ) {
        List<SavedProductDTO> savedProductDTOS = new LinkedList<>();

        products.forEach(product -> {
            var productProperties = propertyService.getProductPropertiesDTO(product);
            savedProductDTOS.add(
                    new SavedProductDTO(
                            product.getManufacturer(),
                            product.getCategory(),
                            product.getPrice(),
                            product.getSerialNumber(),
                            product.getQuantity(),
                            productProperties
                    )
            );
        });

        return savedProductDTOS;
    }

}
