package io.github.aaronr92.pcstore.controller;

import io.github.aaronr92.pcstore.entity.Manufacturer;
import io.github.aaronr92.pcstore.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/manufacturer")
@RestController
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return ResponseEntity.ok(manufacturerService.getAllManufacturers());
    }

    @PostMapping
    public ResponseEntity<Manufacturer> addNewManufacturer(
            @RequestBody Manufacturer manufacturer
    ) {
        manufacturer = manufacturerService.save(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturer);
    }

}
