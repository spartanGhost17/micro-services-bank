# Understand how microservices work with a fictional bank

This project provides a look at best practices in microservices, from dynamically loading configuration from remote repos with webhook refreshes via spring cloud monitor with /monitor,
to best practices in microservices resiliency patterns such as:

- Circuit breakers
- Fallback (applied at the Gateway level, OpenFeign resources as well as at some service endpoints)
- Retry
- Rate Limiting (for preventing DDos Attacks)

It also applies inter-service communication in a decalarative manner using OpenFeign, leverages RabbitMQ for event streaming and use Spring Cloud functions with Cloud Stream for an easy FaaS integration with cloud-stream-binder-rabbit.

## Security

This Project also made use OAuth2 server thourough kecloak by creating both a Authorization Code Grant Type client and a Client Credentials Grant Type client.

# Grafana DashBoard (Loki + Tempo)

Here you can see the Grafana dashboard showing service logs with Loki and distributed traces paths with Tempo

![Grafana dashboard showing logs with Loki and traces with Tempo](./assets/Loki-tempo-dashboard-grafana.png)

# Keycloak OAuth2 Server

Keycloak was used in this project to offer greater security at the gateway level by leveraging both Authorization Code & Client Credentials Grant Types available with OAuth2 and OpenID Connect.

## Client View

Here you can see a few clients including:

- eazybank-callcenter-cc (Client Credentials Grant Type)
- eazybank-callcenter-ac (Authorization Code Grant Type)

![View of clients in Keycloak](./assets/keycloak-clients.png)

## View of Client Credentials Grant Types

![View of Client](./assets/client-credential-grant-client.png)
