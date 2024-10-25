<h1 align="center">Retailer</h1>
<p>This project is a RESTful Web API built using spring boot. </p>
<p>This Retailer application calculates reward points for customers based on their transaction amounts during the last three months.</p>
<p>Reward points are earned as follows <br>
i) A cutomer receives 2 points for every dollar sprnt over $100 in each transaction and <br>
ii) A cusotmer receives 1 point for every dollar spent between $50 and $100 in each transaction.</p>

API's Endpoins:
1) getCustomerRewards:
    It will provide cutomer reward points for the last three months transactions.
    HTTP Method: GET
    Access the API : http://localhost:8080/retailer/rewards/{customerId}

3) createCustomer:
   It will create new customers.
   HTTP Method: POST
   Access the API : http://localhost:8080/retailer/customer

5) createTransaction:
   It will create new Transactions.
   HTTP Method: POST
   Access the API:  http://localhost:8080/retailer/transaction
