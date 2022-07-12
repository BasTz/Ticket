# Ticket
Docker Run Command

docker network create ticket-api  

docker run --net ticket-api --name ticket -p 8080:8080 -it ghcr.io/bastz/ticket:master
