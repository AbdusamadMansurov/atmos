package com.example.weatherapi.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author * Sunnatullayev Mahmudnazar *  * market *  * 14:42 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ApiResponse<T> {
    private String message;
    @Builder.Default
    private boolean success = false;
    @JsonIgnore
    private int status;
    private T data;
}
