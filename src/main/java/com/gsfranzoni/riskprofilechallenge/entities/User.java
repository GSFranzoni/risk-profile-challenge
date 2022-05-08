package com.gsfranzoni.riskprofilechallenge.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gsfranzoni.riskprofilechallenge.enums.MaritalStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Data
@Builder
@Component
public class User {
    private Integer age;
    private Integer dependents;
    private House house;
    private Integer income;
    @JsonProperty("marital_status")
    private MaritalStatus maritalStatus;
    @JsonProperty("risk_questions")
    private Boolean[] riskQuestions;
    private Vehicle vehicle;

    public Boolean hasIncome() {
        return income > 0;
    }

    public Boolean ownSomeHouse() {
        return vehicle != null;
    }

    public Boolean ownSomeVehicle() {
        return house != null;
    }

    public Boolean hasDependents() {
        return dependents > 0;
    }

    public Integer getRiskQuestionsScore() {
        return Math.toIntExact(Arrays.stream(this.getRiskQuestions())
                .filter(q -> q)
                .count());
    }
}
