package com.gsfranzoni.riskprofilechallenge.services;

import com.gsfranzoni.riskprofilechallenge.dtos.RiskResult;
import com.gsfranzoni.riskprofilechallenge.entities.RiskCalculator;
import com.gsfranzoni.riskprofilechallenge.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RiskProfileService {

    public RiskResult calculateRiskProfile(User user)
    {
        RiskCalculator riskCalculator = new RiskCalculator(user);
        riskCalculator.applyRoleZero();
        riskCalculator.applyRoleOne();
        riskCalculator.applyRoleTwo();
        riskCalculator.applyRoleThree();
        riskCalculator.applyRoleFour();
        riskCalculator.applyRoleFive();
        riskCalculator.applyRoleSix();
        riskCalculator.applyRoleSeven();
        riskCalculator.applyRoleEight();
        return new RiskResult(
                riskCalculator.getAuto(),
                riskCalculator.getDisability(),
                riskCalculator.getHome(),
                riskCalculator.getLife()
        );
    }
}
