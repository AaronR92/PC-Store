package io.github.aaronr92.pcstore.service;

import io.github.aaronr92.pcstore.entity.Manufacturer;
import io.github.aaronr92.pcstore.exception.ManufacturerNotFoundException;
import io.github.aaronr92.pcstore.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    /**
     * Ищет в базе данных указанного Производителя по его идентификатору и возвращает
     * найденное значение
     * @param id идентификатор для поиска по
     * @return найденный в бд производитель
     * @throws ManufacturerNotFoundException если производитель не существует в базе данных
     */
    public Manufacturer getManufacturerById(long id) throws ManufacturerNotFoundException {
        return manufacturerRepository.findById(id).orElseThrow(
                ManufacturerNotFoundException::new
        );
    }

    /**
     * Находит всех производителей в базе данных
     * @return все производители в бд
     */
    public List<Manufacturer> getAllManufacturers() {
        return (List<Manufacturer>) manufacturerRepository.findAll();
    }

    /**
     * Сохраняет производителя в бд
     * @param manufacturer для сохранения
     * @return сохраненный производитель
     */
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }
}
