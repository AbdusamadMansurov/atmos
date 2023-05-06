package com.example.atmos.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseException extends Exception {
    private String message;
}
