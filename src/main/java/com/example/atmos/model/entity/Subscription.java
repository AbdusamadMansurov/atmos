package com.example.atmos.model.entity;

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
