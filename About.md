Using Spring Boot (https://spring.io/guides/gs/spring-boot/), develop a working prototype which satisfies the following criteria:  

A Retail Manager (using a RESTful client e.g Chrome's Postman), wanting to keep track of their shops, does a RESTful POST to /shops with a JSON of shopName, 
shopAddress.number and shopAddress.postCode to the Shops API (microservice) where shops are stored in memory. Permanent persistence of the data is not required.  
The application can be used by multiple users at a time (no login functionality is required). Shops are identified by their unique names. If user A adds a shop 
that was already added by user B, the service should replace previous version and REST response to user A should contain information about the version that was 
replaced. If two users submit a shop at the same time, exactly one of them should get information about replacing another version of the shop. Whenever a shop is 
added the service calls the Google Maps API. The Google Maps API responds with the longitude and latitude, which allows the shop data to be updates with 
longitude and latitude.  

A customer, using their geolocation on their phone, wants to find the store that is closest to them. The Shops API will have the customer’s longitude and 
latitude, but also the longitude and latitude of each shop to do the calculation.   
The customer does a RESTful GET to the Shops API, providing their current longitude and latitude (e.g. URL request params), and gets back the address, longitude 
and latitude of the shop nearest to them. 