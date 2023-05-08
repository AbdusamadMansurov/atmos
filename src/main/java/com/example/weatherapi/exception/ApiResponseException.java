package com.example.weatherapi.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseException extends Exception {
    private String message;
}
