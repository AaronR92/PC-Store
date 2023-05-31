package io.github.aaronr92.pcstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Производитель не найден")
public class ManufacturerNotFoundException extends RuntimeException {}
