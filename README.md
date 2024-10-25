<h1 align="center">Retailer</h1>
<p>This project is a RESTful Web API built using spring boot. </p>
<p>This Retailer application calculates reward points for customers based on their transaction amounts during the last three months.</p>
<h3>Reward points are earned as follows</h3>
<ul>
<li> A cutomer receives 2 points for every dollar sprnt over $100 in each transaction plus </li>
<li> A cusotmer receives 1 point for every dollar spent between $50 and $100 in each transaction.</li>
</ul>

<h2>API Endpoins:</h2>

    <h3>1) getCustomerRewards:</h3>
    <ul>
    <li><strong>Description:</strong> It will provide cutomer reward points for the last three months transactions.</li>
    <li><strong>HTTP Method:</strong> GET </li>
    <li><strong>Access URL :</strong> http://localhost:8080/retailer/rewards/{customerId}</li>

2) createCustomer:
   <li><strong>Description:</strong>It will create new customers.</li>
   <li><strong>HTTP Method:</strong> POST</li>
   <li><strong>Access the API :</strong> http://localhost:8080/retailer/customer</li>

3) createTransaction:
   <li><strong>Description:</strong>It will create new Transactions.</li>
   <li><strong>HTTP Method:</strong> POST</li>
   <li><strong>Access the API:</strong>  http://localhost:8080/retailer/transaction</li>

