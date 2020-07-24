##storeservice
Restful web-services for Grocer and consumer registration


# Register Grocer

### Request

`POST` : `/mystore/register/grocer`

    {"name":"walmart","items":["eggs","sugar","cookies","icecream","bread"],"location":"denver"}


### Response

    HTTP/1.1 200 
	Content-Type: application/json
	Content-Length: 47
	Date: Fri, 24 Jul 2020 14:17:07 GMT
	

	{"id":"BOOUhhi61F6UHQCS","statusCode":"200 OK"}

# Register Consumer

### Request

`POST` : `mystore/register/consumer`

    {"name":"walmart","location":"denver"}


### Response

    HTTP/1.1 200 
	Content-Type: application/json
	Content-Length: 47
	Date: Fri, 24 Jul 2020 14:17:07 GMT
	

	{"id":"BOOUhhi61F6UHQCS","statusCode":"200 OK"}
	
# Get list of Grocers 

### Request

`GET /location/{location}`
 
 - Returns a list of grocers with available stock in a particular location.

### Response
	HTTP/1.1 200 
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Fri, 24 Jul 2020 14:54:58 GMT


	[{"name":"walmart","items":["["eggs","sugar","cookies","icecream","bread"],"location":"denver"}]
    

### Request

`GET /item/{item}`
- Return a list of grocers who sells a particular item 

### Response
	HTTP/1.1 200 
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Fri, 24 Jul 2020 14:54:58 GMT


	[{"name":"walmart","items":["["eggs","sugar","cookies","icecream","bread"],"location":"denver"}]
    




