package io.github.aaronr92.pcstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Свойство не найдено")
public class PropertyNotFoundException extends RuntimeException {
}
