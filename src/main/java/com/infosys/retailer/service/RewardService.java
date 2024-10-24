package com.infosys.retailer.service;

import java.util.Map;

public interface RewardService {
    Map<String, Object> getCustomerRewards(Long customerId);
}
