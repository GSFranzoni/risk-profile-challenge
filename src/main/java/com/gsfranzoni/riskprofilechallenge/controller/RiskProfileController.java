package com.gsfranzoni.riskprofilechallenge.controller;

import com.gsfranzoni.riskprofilechallenge.dtos.RiskResult;
import com.gsfranzoni.riskprofilechallenge.entities.User;
import com.gsfranzoni.riskprofilechallenge.services.RiskProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/riskprofile")
@AllArgsConstructor
public class RiskProfileController {

    @Autowired
    private final RiskProfileService service;

    @PostMapping
    @RequestMapping
    public ResponseEntity<RiskResult> calculateRiskProfile(@RequestBody User user) {
        try {
            return ResponseEntity.ok(this.service.calculateRiskProfile(user));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}