package com.gsfranzoni.riskprofilechallenge.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gsfranzoni.riskprofilechallenge.enums.OwnershipStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class House {
    @JsonProperty("ownership_status")
    private OwnershipStatus ownershipStatus;
}
