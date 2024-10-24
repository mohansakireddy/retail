package com.infosys.retailer.controller;

import com.infosys.retailer.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/retailer/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;
    @GetMapping("/{customerId}")
    public ResponseEntity<Map<String, Object>> getCustomerRewards(@PathVariable Long customerId){
        Map<String,Object> rewards = rewardService.getCustomerRewards(customerId);
        return ResponseEntity.ok(rewards);
    }


}
