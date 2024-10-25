package com.infosys.retailer.service;

import java.util.List;
import java.util.Map;

public interface RewardService {
    Map<String, Object> getCustomerRewards(Long customerId);

    List<Map<String, Object>> getRewardsForRecentTransactions();
}
