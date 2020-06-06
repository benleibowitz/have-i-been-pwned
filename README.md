[![Build Status](https://travis-ci.com/benleibowitz/have-i-been-pwned.svg?token=JS1fknxqjKGbtzSjiKMq&branch=master)](https://travis-ci.com/benleibowitz/have-i-been-pwned)

## Overview

This is a Java client for the [haveibeenpwned.com API](https://haveibeenpwned.com/API/v3).

## Usage
To use the publicly available APIs that do not require an API key:
```java
APIConfiguration config = new APIConfiguration();
APIClient client = new APIClient(config);
Breaches breaches = client.getBreaches();
```


To use endpoints that require an API key (such as the Get Breaches For Account endpoint), setup the `APIConfiguration` with your key:
```java
APIConfiguration config = new APIConfiguration("my-api-key");
APIClient client = new APIClient(config);
Breaches breaches = client.getBreachesForAccount("foo@bar.com");
```

For more info on authorization, see https://haveibeenpwned.com/API/v3#Authorisation.

### TODO

- [ ] Create release and deploy to central Maven repository
- [ ] Support other endpoints for the API