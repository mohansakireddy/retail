<h1 align="center">Retailer</h1>
<p>This project is a RESTful Web API built using spring boot. </p>
<p>This Retailer application calculates reward points for customers based on their transaction amounts during the last three months.</p>
<h3>Reward points are earned as follows</h3>
<ul>
<li> A cutomer receives 2 points for every dollar sprnt over $100 in each transaction plus </li>
<li> A cusotmer receives 1 point for every dollar spent between $50 and $100 in each transaction.</li>
</ul>

<h2>Features</h2>
<ul>
   <li>Calculates monthly and total reward points for each customer based on their transactions for last three months.</li>
   <li>provides REST endpoints to fetch customer rewards.</li>
   <li>Uses H2 in-memory database for test data, initialized via data.sql.</li>
</ul>
<h2>API Endpoins:</h2>

   

1)  getCustomerRewards:
   <ul>
   <li><strong>Description:</strong> It will provide cutomer reward points for the last three months transactions.</li>
   <li><strong>HTTP Method:</strong> GET </li>
   <li><strong>Access the API :</strong><code>http://localhost:8080/retailer/rewards/{customerId} </code></li>
   </ul>   
   
2) createCustomer:
   <ul>
   <li><strong>Description:</strong>It will create new customers.</li>
   <li><strong>HTTP Method:</strong> POST</li>
   <li><strong>Access the API :</strong><code> http://localhost:8080/retailer/customer</code></li>
   </ul>
   
3) createTransaction:
   <ul>
   <li><strong>Description:</strong>It will create new Transactions.</li>
   <li><strong>HTTP Method:</strong> POST</li>
   <li><strong>Access the API:</strong> <code> http://localhost:8080/retailer/transaction</code></li>
   </ul>

<h2>Accessing H2 console</h2>
 <ul>
   <li><strong>H2 console url:</strong> <code>http://localhost:8080/h2-console</li>
   <li><strong>JDBC URL:</strong> <code>jdbc:h2:mem:testdb</code></li>
    <li><strong>username:</strong> <code>sa</code> </li>
    <li><strong>password:</strong> (leave blank) </li>
   </ul> 
