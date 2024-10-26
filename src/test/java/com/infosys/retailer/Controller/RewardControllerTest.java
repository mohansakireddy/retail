package com.infosys.retailer.Controller;

import com.infosys.retailer.controller.RewardController;
import com.infosys.retailer.exception.NoTransactionsFoundException;
import com.infosys.retailer.service.RewardService;
import com.infosys.retailer.variables.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {
    @MockBean
    RewardService rewardService;
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    private RewardController rewardController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRewardsForRecentTransactions() throws Exception {

        Map<String, Object> customer1Data = new LinkedHashMap<>();
        customer1Data.put(Constants.RESPONSE_CUSTOMER_ID, 1L);
        customer1Data.put(Constants.RESPONSE_CUSTOMER_NAME, "mohan");
        customer1Data.put(Constants.RESPONSE_REWARDS_PER_MONTH, Map.of(LocalDate.now().minusMonths(0).getMonth().toString(), 200, LocalDate.now().minusMonths(1).getMonth().toString(), 25, LocalDate.now().minusMonths(2).getMonth().toString(), 0));
        customer1Data.put(Constants.RESPONSE_TOTAL_REWARDS, 225);

        Map<String, Object> customer2Data = new LinkedHashMap<>();
        customer2Data.put(Constants.RESPONSE_CUSTOMER_ID, 2L);
        customer2Data.put(Constants.RESPONSE_CUSTOMER_NAME, "siva");
        customer2Data.put(Constants.RESPONSE_REWARDS_PER_MONTH, Map.of(LocalDate.now().minusMonths(0).getMonth().toString(), 200, LocalDate.now().minusMonths(1).getMonth().toString(), 25));
        customer2Data.put(Constants.RESPONSE_TOTAL_REWARDS, 225);

        List<Map<String, Object>> mockRewardsList = Arrays.asList(customer1Data, customer2Data);

        when(rewardService.getRewardsForRecentTransactions()).thenReturn(mockRewardsList);

        mockMvc.perform(get("/retailer/rewards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['" + Constants.RESPONSE_CUSTOMER_ID + "']").value(1))
                .andExpect(jsonPath("$[0]['" + Constants.RESPONSE_CUSTOMER_NAME + "']").value("mohan"))
                .andExpect(jsonPath("$[0]['" + Constants.RESPONSE_REWARDS_PER_MONTH + "']." + LocalDate.now().minusMonths(0).getMonth().toString()).value(200))
                .andExpect(jsonPath("$[0]['" + Constants.RESPONSE_REWARDS_PER_MONTH + "']." + LocalDate.now().minusMonths(1).getMonth().toString()).value(25))
                .andExpect(jsonPath("$[0]['" + Constants.RESPONSE_REWARDS_PER_MONTH + "']." + LocalDate.now().minusMonths(2).getMonth().toString()).value(0))
                .andExpect(jsonPath("$[0]['" + Constants.RESPONSE_TOTAL_REWARDS + "']").value(225))
                .andExpect(jsonPath("$[1]['" + Constants.RESPONSE_CUSTOMER_ID + "']").value(2))
                .andExpect(jsonPath("$[1]['" + Constants.RESPONSE_CUSTOMER_NAME + "']").value("siva"))
                .andExpect(jsonPath("$[1]['" + Constants.RESPONSE_REWARDS_PER_MONTH + "']." + LocalDate.now().minusMonths(0).getMonth().toString()).value(200))
                .andExpect(jsonPath("$[1]['" + Constants.RESPONSE_REWARDS_PER_MONTH + "']." + LocalDate.now().minusMonths(1).getMonth().toString()).value(25))
                .andExpect(jsonPath("$[1]['" + Constants.RESPONSE_TOTAL_REWARDS + "']").value(225));


    }

    @Test
    public void testGetRewardsForRecentTransactions_NoTransactionsFound() throws Exception {
        // Arrange: mock exception
        doThrow(new NoTransactionsFoundException()).when(rewardService).getRewardsForRecentTransactions();

        // Act and Assert
        mockMvc.perform(get(Constants.REWARDS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No Transactions found in last three months"));
    }
}
