package com.example.weatherapi.model.entity;

import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Subscription {

    private Long id;

    private Users user;

    private City city;

    private LocalDateTime subscriptionTime;

    private boolean active = true;
}
