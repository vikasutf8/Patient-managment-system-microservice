### GRPC

- Request
```
localhost:9090/BillingService/CreateBillingService
{
    "patientId": "1234",
    "email": "vikas@gmail.com", 
    "name": "vikas"
}

```

- Response
```
{
  "accountId": "acct-1234",
  "status": "CREATED"
}
```