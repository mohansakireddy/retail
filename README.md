# Retalier
This project is a RESTful Web API built using spring boot.

## Overview
This Retailer application calculates reward points for customers based on their transaction amounts during the last three months.

### Reward points are earned as follows
- A cutomer receives 2 points for every dollar sprnt over $100 in each transaction plus
- A cusotmer receives 1 point for every dollar spent between $50 and $100 in each transaction.

## Table of Contents

- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Accessing MySQL Database](#accessing-mysql-database)
- [Project Structure](#project-structure)
- [Installation Guides](#installation-guide)
- [Flow Diagram](images/flowdiagram.png)
- [List of Softwares Used](#software-used)
- [API Testing Screenshots](testingdocs/getRewardsForRecentTransactions.png)


## Features
- Calculates rewards points for transactions of last three months for each and every customer.
- provides REST endpoints to fetch rewards.
- Uses MySQL as database for storing data, initialized via data.sql.


## API Endpoints

### 1. Get Rewards for transactions of three months
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
```
### 2. Get Customer Rewards

- **Description**: Provides customer reward points for the last three months of transactions.
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8080/retailer/rewards/{customerId}`
  
#### Request

- **Path Parameter**:
  - `customerId`: ID of the customer.

#### Response Example

```json
{
    "Customer": "mohan",
    "Rewards Per Month": {
        "OCTOBER": 200,
        "SEPTEMBER": 25,
        "AUGUST": 0
    },
    "Total Rewards": 225
}
```
### 2. create Customer

- **Description**: It will create new customers.
- **HTTP Method**: `POST`
- **URL**: ` http://localhost:8080/retailer/customer`
  
#### Request

- **Request Body**:
```json
{
    "name" : "mahesh"
}
```

#### Response Example

```json
{
    "id": 3,
    "name": "mahesh"
}
```

### 2. create Transaction

- **Description**: It will create new Transactions.
- **HTTP Method**: `POST`
- **URL**: ` http://localhost:8080/retailer/transaction`
  
#### Request

- **Request Body**:
```json
{
    "customerId" : "3",
    "amount" : "555.00",
    "transactionDate" : "2024-10-22"
}
```

#### Response Example

```json
{
    "id": 7,
    "customer": {
        "id": 3,
        "name": "mahesh"
    },
    "transactionDate": "2024-10-22",
    "amount": 555.0
}
```
## Accessing MySQL Database
- **MySQL Console URL:** `You can access the MySQL database using prefferred MySQL clinet (eg: MySQL Workbench) `
- **JDBC URL:** `jdbc:mysql://localhost:3306/retailer`
- **username:** `your username ` example: root
- **password:** `you password ` example: root


## Project Structure

```plaintext
src
├───main
│   ├───java
│   │   └───com
│   │       └───infosys
│   │           └───retailer
│   │               │   RetailerApplication.java
│   │               │
│   │               ├───controller
│   │               │       CustomerController.java
│   │               │       RewardController.java
│   │               │       TransactionController.java
│   │               │
│   │               ├───dto
│   │               │       CustomerDto.java
│   │               │       TransactionDto.java
│   │               │
│   │               ├───entity
│   │               │       Customer.java
│   │               │       Transaction.java
│   │               │
│   │               ├───exception
│   │               │       CustomerNotFoundException.java
│   │               │       GlobalExceptionHandler.java
│   │               │       NoTransactionsFoundException.java
│   │               │       NoTransactionsFoundForCustomerException.java
│   │               │
│   │               ├───repository
│   │               │       CustomerRepository.java
│   │               │       TransactionRepository.java
│   │               │
│   │               ├───service
│   │               │   │   CustomerService.java
│   │               │   │   RewardService.java
│   │               │   │   TransactionService.java
│   │               │   │
│   │               │   └───impl
│   │               │           CustomerServiceImpl.java
│   │               │           RewardServiceImpl.java
│   │               │           TransactionServiceImpl.java
│   │               │
│   │               └───variables
│   │                       Constants.java
│   │
│   └───resources
│       │   application.properties
│       │   data.sql
│       │
│       ├───static
│       └───templates
└───test
    └───java
        └───com
            └───infosys
                └───retailer
                    │   RetailerApplicationTests.java
                    │
                    ├───Controller
                    │       RewardControllerTest.java
                    │
                    └───service
                            RewardServiceImplTest.java
```

## Installation Guide

### 1. Clone the Repository
```
git clone https://github.com/mohansakireddy/retail

```

### 2. Navigate to the project Repository
```
cd retail
```

### 3. Build the project

```
mvn clean install

```

### 4. Run the Application

```
mvn spring-boot:run
```

### 5.Access MySQL Database
 You can access the MySQL database using prefferred MySQL clinet (eg: MySQL Workbench)



## Software Used
- **JAVA** 
- **Spring Boot**
- **MySQL:** Relational database management system 
- **Maven:** Build Automation Tool
