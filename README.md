# Retalier
This project is a RESTful Web API built using spring boot.

## Overview
This Retailer application calculates reward points for customers based on their transaction amounts during the last three months.

### Reward points are earned as follows
- A cutomer receives 2 points for every dollar sprnt over $100 in each transaction plus
- A cusotmer receives 1 point for every dollar spent between $50 and $100 in each transaction.

## Features
- Calculates rewards points for transactions of last three months for each and every customer.
- provides REST endpoints to fetch rewards.
- Uses H2 in-memory database for test data, initialized via data.sql.

## API Endpoints

### 1) Get Rewards for transactions of three months
- **Description**: Provides reward points for the last three months of transactions of each and every customer.
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8080/retailer/rewards`

#### Request

- **No Parameters**.

#### Response Example

```json
[
    {
        "Customer ID": 1,
        "Customer": "mohan",
        "Rewards Per Month": {
            "OCTOBER": 200,
            "SEPTEMBER": 25,
            "AUGUST": 0
        },
        "Total Rewards": 225
    },
    {
        "Customer ID": 2,
        "Customer": "siva",
        "Rewards Per Month": {
            "OCTOBER": 200,
            "SEPTEMBER": 25
        },
        "Total Rewards": 225
    }
]
