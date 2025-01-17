package com.hande.RestfulDemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CustomerDTO {
    private UUID customerId;

    @NotNull
    @NotBlank
    private String customerName;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
