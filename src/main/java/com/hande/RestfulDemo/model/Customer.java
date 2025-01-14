package com.hande.RestfulDemo.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
@Data
@Builder
public class Customer {
    private UUID customerId;
    private String customerName;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
