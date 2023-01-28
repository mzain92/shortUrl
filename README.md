# Assumption


- The current implementation utilizes an in-memory database.
- No test cases have been written at this time. Additional information is needed to determine if unit testing or integration testing is required. 
- The structure of the endpoint for obtaining the shortened URL has been modified in order to avoid the need for URL encoding. The endpoint is currently defined as

 ` @GetMapping("{protocol}://{url}/short") `
