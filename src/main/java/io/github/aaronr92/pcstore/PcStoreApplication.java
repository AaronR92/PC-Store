package io.github.aaronr92.pcstore;

import io.github.aaronr92.pcstore.dto.ProductDTO;
import io.github.aaronr92.pcstore.entity.Category;
import io.github.aaronr92.pcstore.entity.Manufacturer;
import io.github.aaronr92.pcstore.entity.Product;
import io.github.aaronr92.pcstore.service.CategoryService;
import io.github.aaronr92.pcstore.service.ManufacturerService;
import io.github.aaronr92.pcstore.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PcStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner saveDefaultValues(
            ManufacturerService manufacturerService,
            CategoryService categoryService,
            ProductService productService
    ) {
        return args -> {
            /* Добавление производителей */
            manufacturerService.save(
                    Manufacturer.builder()
                            .name("Asus")
                            .address("ул. Первая 21")
                            .build()
            );

            manufacturerService.save(
                    Manufacturer.builder()
                            .name("Gigabyte")
                            .address("ул. Третья 15")
                            .build()
            );

            /* Добавление категорий */
            categoryService.save(
                    Category.builder()
                            .name("Настольный ПК")
                            .build()
            );

            categoryService.save(
                    Category.builder()
                            .name("Ноутбук")
                            .build()
            );

            categoryService.save(
                    Category.builder()
                            .name("Монитор")
                            .build()
            );

            categoryService.save(
                    Category.builder()
                            .name("Жесткий диск")
                            .build()
            );

            /* Добавление товара */
            productService.save(
                    new ProductDTO(
                        1,
                            1,
                            60000,
                            "A42B454",
                            10,
                            "форм-фактор",
                            "моноблок"
                    )
            );
        };
    }

}
