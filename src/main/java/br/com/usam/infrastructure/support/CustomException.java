package br.com.usam.infrastructure.support;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomException {
    private final HttpStatus httpStatus;
    private final String message;
}
