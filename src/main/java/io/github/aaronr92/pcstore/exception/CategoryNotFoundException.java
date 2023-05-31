package io.github.aaronr92.pcstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Категория не найдена")
public class CategoryNotFoundException extends RuntimeException {}
