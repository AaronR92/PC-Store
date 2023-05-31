package io.github.aaronr92.pcstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Продукт с таким идентификатором не существует")
public class ProductNotFoundException extends RuntimeException { }
