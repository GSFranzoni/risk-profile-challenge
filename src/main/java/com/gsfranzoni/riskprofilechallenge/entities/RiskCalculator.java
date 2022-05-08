package com.gsfranzoni.riskprofilechallenge.entities;

import com.gsfranzoni.riskprofilechallenge.enums.MaritalStatus;
import com.gsfranzoni.riskprofilechallenge.enums.OwnershipStatus;
import com.gsfranzoni.riskprofilechallenge.enums.RiskProfile;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.time.temporal.ValueRange;

@Component
@Data
public class RiskCalculator {
    private User user;
    private RiskProfile auto;
    private RiskProfile disability;
    private RiskProfile home;
    private RiskProfile life;
    private Integer disabilityRiskPoints;
    private Integer autoRiskPoints;
    private Integer homeRiskPoints;
    private Integer lifeRiskPoints;

    public RiskCalculator(User user) {
        this.user = user;
        this.disabilityRiskPoints = 0;
        this.autoRiskPoints = 0;
        this.homeRiskPoints = 0;
        this.lifeRiskPoints = 0;
    }

    public void applyRoleZero()
    {
        Integer initialRiskPoint = this.user.getRiskQuestionsScore();
        this.setDisabilityRiskPoints(initialRiskPoint);
        this.setLifeRiskPoints(initialRiskPoint);
        this.setAutoRiskPoints(initialRiskPoint);
        this.setLifeRiskPoints(initialRiskPoint);
        this.updateAllRiskProfiles();
    }

    public void applyRoleOne()
    {
        if (!this.user.hasIncome()) {
            this.setDisability(RiskProfile.ineligible);
        }
        if (!this.user.ownSomeHouse()) {
            this.setHome(RiskProfile.ineligible);
        }
        if (!this.user.ownSomeVehicle()) {
            this.setAuto(RiskProfile.ineligible);
        }
        this.updateAllRiskProfiles();
    }

    public void applyRoleTwo()
    {
        if (this.user.getAge() > 60) {
            this.setDisability(RiskProfile.ineligible);
            this.setLife(RiskProfile.ineligible);
        }
        this.updateAllRiskProfiles();
    }

    public void applyRoleThree()
    {
        if (this.user.getAge() < 30) {
            this.decreaseDisabilityRiskPoints(2);
            this.decreaseLifeRiskPoints(2);
            this.decreaseAutoRiskPoints(2);
            this.decreaseHomeRiskPoints(2);
        }
        if (ValueRange.of(30, 40).isValidValue(this.user.getAge())) {
            this.setDisabilityRiskPoints(1);
            this.setLifeRiskPoints(1);
            this.setAutoRiskPoints(1);
            this.setLifeRiskPoints(1);
        }
        this.updateAllRiskProfiles();
    }

    public void applyRoleFour()
    {
        if (user.getIncome() > 200_000) {
            this.setDisabilityRiskPoints(1);
            this.setLifeRiskPoints(1);
            this.setAutoRiskPoints(1);
            this.setLifeRiskPoints(1);
        }
        this.updateAllRiskProfiles();
    }

    public void applyRoleFive()
    {
        if (this.user.ownSomeHouse() && this.user.getHouse().getOwnershipStatus() == OwnershipStatus.mortgaged) {
            this.increaseHomeRiskPoints(1);
            this.decreaseDisabilityRiskPoints(1);
        }
        this.updateAllRiskProfiles();
    }

    public void applyRoleSix()
    {
        if (this.user.hasDependents()) {
            this.increaseDisabilityRiskPoints(1);
            this.increaseLifeRiskPoints(1);
        }
        this.updateAllRiskProfiles();
    }

    public void applyRoleSeven()
    {
        if (this.user.getMaritalStatus() == MaritalStatus.married) {
            this.increaseLifeRiskPoints(1);
            this.decreaseDisabilityRiskPoints(1);
        }
        this.updateAllRiskProfiles();
    }

    public void applyRoleEight()
    {
        Year fiveYearsAgo = Year.now().minusYears(5);
        if (this.user.ownSomeVehicle() && this.user.getVehicle().getYear().isAfter(fiveYearsAgo)) {
            this.increaseAutoRiskPoints(1);
        }
        this.updateAllRiskProfiles();
    }

    private void increaseDisabilityRiskPoints(Integer points) {
        this.disabilityRiskPoints += points;
    }

    private void increaseAutoRiskPoints(Integer points) {
        this.autoRiskPoints += points;
    }

    private void increaseHomeRiskPoints(Integer points) {
        this.homeRiskPoints += points;
    }

    private void increaseLifeRiskPoints(Integer points) {
        this.lifeRiskPoints += points;
    }

    private void decreaseDisabilityRiskPoints(Integer points) {
        this.disabilityRiskPoints += points;
    }

    private void decreaseAutoRiskPoints(Integer points) {
        this.autoRiskPoints += points;
    }

    private void decreaseHomeRiskPoints(Integer points) {
        this.homeRiskPoints += points;
    }

    private void decreaseLifeRiskPoints(Integer points) {
        this.lifeRiskPoints += points;
    }

    public void updateAllRiskProfiles() {
        this.auto = this.calculateRiskProfile(this.auto, this.autoRiskPoints);
        this.disability = this.calculateRiskProfile(this.disability, this.disabilityRiskPoints);
        this.home = this.calculateRiskProfile(this.home, this.homeRiskPoints);
        this.life = this.calculateRiskProfile(this.life, this.lifeRiskPoints);
    }

    private RiskProfile calculateRiskProfile(RiskProfile riskProfile, Integer riskPoints) {
        if (riskProfile == RiskProfile.ineligible) {
            return RiskProfile.ineligible;
        }
        if (riskPoints <= 0) {
            return RiskProfile.economic;
        }
        if (ValueRange.of(1, 2).isValidValue(riskPoints)) {
            return RiskProfile.regular;
        }
        if (riskPoints >= 3) {
            return RiskProfile.responsible;
        }
        return null;
    }
}
