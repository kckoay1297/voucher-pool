# voucher-pool

Voucher Pool Microservices
Database Entities:
RECIPIENT: RECIPIENT_ID, NAME, EMAIL. Primary Key = RECIPIENT_ID, unique key = EMAIL
SPECIAL_OFFER: SPECIAL_OFFER_ID, NAME, FIXED_PERCENTAGE_DISCOUNT. Primary Key = SPECIAL_OFFER_ID
VOUCHER_CODE: VOUCHER_CODE_ID, RECIPIENT_ID, SPECIAL_OFFER_ID, EXPIRATION_DATE, USED_FREQUENCY, USAGE_DATE
Kindly refer data.sql for full SQL Scheme

RESTful API:
Create voucher code from existing special offer, and then distribute to all recipients:
URL: /create-voucher-code-on-each-recipient
Param: specialOfferId (int), expirationDate (date)
Method: POST
Result: String message about how many recipients updated
Flow: 
Will check if the current offer is already activated or not.
If it is not activated, distribute each voucher to all recipient
Code Example: 
Redeem voucher and update usage frequency and date:
URL: /voucher/rest-api/redeem-voucher
RequestBody: 
Method: PUT
Result: String message of about status after redeem voucher code
Flow: 
Will check that both recipient and voucher code exist or not
Do validation regarding to voucher code is expired or already used
If no, will return voucher code and update usage time and usage frequency respectively
Code Example: 
Get lists of voucher code by using email: 
URL: /voucher/rest-api/get-voucher-by-email
Param: email (string)
Method: GET
Result: JSON result : 
Flow: 
Write joined query to return data by email
Code Example: 

Configuration: After unzip, while refresh gradle, kindly help to import junit 4 in test units
