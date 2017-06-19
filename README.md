# Shop services (Assignment)
This application provides a shops-server micro service 

## Download
The source code locates at https://github.com/mli2017/assignment

## Configuration
The registration server is configured to run on port `13001`, shops-server on `13002`.
If these two ports are in use, then:	

 1. Edit `registration-server.yml`,  modify `port: 13001` to a different port;
 1. Edit `shops-server.yml`, modify `port: 13002`  and also `defaultZone: http://localhost:13001/eureka/` to the port number as above step.

 	
## Build
To build with **gradle**:
   `cd assignment`
   `gradle bootRepackage`
 
To build with **maven**:
    `cd assignment`
    `mvn clean package`  
 	 	
## Run
To run **registration-server**:
Open CMD windows, run `java -jar build/libs/retailservices-1.0.0.RELEASE.jar registration` if built with gradle, or `java -jar target/retailservices-1.0.0.RELEASE.jar registration` if built with maven
Go to browser and open link to [http://localhost:13001] or the port you have choosen, you may need to wait it to start up.
   
To run **shops-server**:
Open CMD window, run `java -jar build/libs/retailservices-1.0.0.RELEASE.jar shops` if built with gradle, or `java -jar target/retailservices-1.0.0.RELEASE.jar shops` if built with maven
Go to browser and open link to [http://localhost:13002] or the port you have choosen, you may need to wait it to start up.
   
## Test
 To test with browser and postman

 1. **Add a shop (POST)** 
On postman,put http://localhost:13002/shops/add/ in the url box, Set Headers `Content-Type` to `application/json` and post shop in json format, an example is :    `{"name":"Shop0098","address":{"number":158,"postCode":"UR16 6XJ"}}` 

 2. **Get shops by location (GET)** 
  On browser or postman,send a request as http://localhost:13002/shops/search?longitude=13.066696251423991&latitude=62.84786162523105 
   
 3. **Get a shop (GET)** [Demo purpose only]
   On browser put http://localhost:13002/shops/byname/Shop0001
   On postman send a get Request to http://localhost:13002/shops/byname/Shop0001
   
 4. **Get shops by location (POST)** [Demo purpose only]
  On postman,put http://localhost:13002/shops/bylocation/ set Headers `Content-Type` to `application/json` and post location in json format, an example is :    `{"longitude":13.066696251423991,"latitude":62.84786162523105}`
  
  **Note**: Replace 13002 to the port you configured for shops-server
  
   To test with builtin client:
   
   Open another CMD window, run `java -jar build/libs/retailservices-1.0.0.RELEASE.jar client` if built with gradle, 
  or `java -jar target/retailservices-1.0.0.RELEASE.jar client` if built with maven      
	   
   If shops-server is running a different port other than `13002`, then add `portnumber` as a start  argument, like `java -jar build/libs/retailservices-1.0.0.RELEASE.jar client port`

