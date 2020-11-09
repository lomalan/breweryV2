package com.lomalan.breweryv2.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
/**
 * Created by lomalan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable {

    private static final long serialVersionUID = 6673360992951551955L;

    @Null
    private UUID id;
    @Null
    private Long version;

    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyleEnum beerStyle;

    @Positive
    @NotNull
    private Long upc;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    private Integer quantityOnHand;

    @Positive
    private Integer quantityToBrew;
}
