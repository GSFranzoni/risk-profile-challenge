package com.gsfranzoni.riskprofilechallenge.entities;

import com.gsfranzoni.riskprofilechallenge.enums.RiskProfile;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RiskCalculatorTest {

    @Mock
    private User user;

    @InjectMocks
    private RiskCalculator riskCalculator;

    @Test
    void applyRoleZero() {
        // Given
        given(user.getRiskQuestionsScore()).willReturn(1);
        // When
        riskCalculator.applyRoleZero();
        // Then
        assertThat(riskCalculator.getAutoRiskPoints()).isEqualTo(1);
        assertThat(riskCalculator.getDisabilityRiskPoints()).isEqualTo(1);
        assertThat(riskCalculator.getLifeRiskPoints()).isEqualTo(1);
        assertThat(riskCalculator.getLifeRiskPoints()).isEqualTo(0);
    }

    @Test
    void applyRoleOne() {
        // Given
        given(user.hasIncome()).willReturn(false);
        given(user.ownSomeHouse()).willReturn(false);
        given(user.ownSomeVehicle()).willReturn(false);
        // When
        riskCalculator.applyRoleOne();
        // Then
        assertThat(riskCalculator.getDisability()).isEqualTo(RiskProfile.ineligible);
        assertThat(riskCalculator.getHome()).isEqualTo(RiskProfile.ineligible);
        assertThat(riskCalculator.getAuto()).isEqualTo(RiskProfile.ineligible);
    }

    @Test
    void applyRoleTwo() {
        // Given
        given(user.getAge()).willReturn(61);
        // When
        riskCalculator.applyRoleTwo();
        // Then
        assertThat(riskCalculator.getDisability()).isEqualTo(RiskProfile.ineligible);
        assertThat(riskCalculator.getLife()).isEqualTo(RiskProfile.ineligible);
    }

    @Test
    void applyRoleThree() {
    }

    @Test
    void applyRoleFour() {
    }

    @Test
    void applyRoleFive() {
    }

    @Test
    void applyRoleSix() {
    }

    @Test
    void applyRoleSeven() {
    }

    @Test
    void applyRoleEight() {
    }
}