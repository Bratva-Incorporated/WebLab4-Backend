package com.porunit.l4.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shot {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotNull @Min(value = -5) @Max(value = 5)
    private Double y;

    @NotNull
    @Min(value = -5)
    @Max(value = 5)
    private Double x;

    @NotNull
    @Min(value = -5)
    @Max(value = 5)
    private Integer r;

    @NotNull
    private Boolean isHit;
}
