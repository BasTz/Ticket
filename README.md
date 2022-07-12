# Ticket
## Docker Run Command

#### Pull image from Github
```
docker pull ghcr.io/bastz/ticket:master 
```

#### If you don't have network `ticket-api` please create it first
```
docker network create ticket-api  
```

#### Docker run image 
```
docker run --net ticket-api --name ticket -p 8080:8080 -it ghcr.io/bastz/ticket:master
```
### Absolutely do not change the code. Please run the code provided.
