This project is a RESTful Web API built using spring boot. 
This Retailer application calculates reward points for customers based on their transaction amounts during the last three months.
Reward points are earned as follows
i) A cutomer receives 2 points for every dollar sprnt over $100 in each transaction and
ii) A cusotmer receives 1 point for every dollar spent between $50 and $100 in each transaction.

API's provided:
1) getCustomerRewards
    It will provide cutomer reward points for the last three months transactiopns
    Access the API : http://localhost:8080/retailer/rewards/{customerId}

2) createCustomer
   It will create new customers
   Access the API : http://localhost:8080/retailer/customer

3) createTransaction
   It will create new Transactions
   Access the API:  http://localhost:8080/retailer/transaction
