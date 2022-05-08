package com.gsfranzoni.riskprofilechallenge.dtos;

import com.gsfranzoni.riskprofilechallenge.enums.RiskProfile;

public record RiskResult(
        RiskProfile auto,
        RiskProfile disability,
        RiskProfile home,
        RiskProfile life
) {
}
