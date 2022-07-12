# Ticket
## Docker Run Command

### //If you don't have network ticket-api please create it first//
docker network create ticket-api  

docker run --net ticket-api --name ticket -p 8080:8080 -it ghcr.io/bastz/ticket:master
