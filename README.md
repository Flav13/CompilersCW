### Summary
The business requires a salary calculator to be built that allows users to calculate their tax liability and take home pay. Front end developers have created a UI and you are responsible for creating a back end service to support it.

### Requirements

1. The service should be built using Spring Boot. 

	This can be found at [http://projects.spring.io/spring-boot](http://projects.spring.io/spring-boot)

2. A REST endpoint should be created that accepts an email, a tax year and a gross salary.

3. All requests should be saved in a database along with the users IP.

4. The service should then calculate and return the users tax and take home pay for the given tax year.

5. The service needs to support data going back to the 2014/15 tax year, as well as the current 2015/16 tax year.

6. The business does not yet know what the 2016/17 tax years rates or thresholds look like.

7. The service should only accept salaries between £1 and £200,000. 

8. An appropriate error response should be returned if any validation fails.

9. Unit tests should be written to ensure correct functionality of code

### Calculating Take Home Pay
Tax is calculated based on how much a person earns. The following tables are the rates for 2014/15 and 2015/16

| 2014/15				|				|
| -------------			|-------------	|
| **Income**			| **Tax Rate**	|
| £0-£10,000			| 0				|
| £10,000- £41,865		| 20%			|
| £41,865 - £150,000 	| 40%			|
| £150,000+				| 45%			|

| 2015/16				|				|
| -------------			|-------------	|
| **Income**			| **Tax Rate**	|
| £0-£10,600			| 0				|
| £10,601- £42,385		| 20%			|
| £42,385- £150,000  	| 40%			|
| £150,000+				| 45%			|


If a person earned £80,000 in 2014/15 their tax would be calculated as follows

10,000 at 0%

31,865 at 20%

38,135 at 40%

For a total of £21,627 tax and £58, 373 take home pay




### In assessing the task, 

We will not focus at all on how the database is set up, it can be in-memory and not persistent.

We will focus on the business logic implementation.

We will look at the maintainability of code, from a future developers perspective.

We will focus less so on the use of the frameworks.
