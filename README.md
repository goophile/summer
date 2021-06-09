# Summer

Demo project for Spring Boot.

- Spring Boot
- MyBatis
- MySQL
- REST API
- Docker


# Build and run

```sh
$ git clone https://github.com/goophile/summer.git
$ cd summer
$ docker-compose up --build -d
```


# Database migration

```sh
$ cd summer
$ docker pull flyway/flyway:7.9.2
$ docker run --rm --net summer_default --link mysql:mysql -v `pwd`/src/main/resources/migrations:/flyway/sql flyway/flyway:7.9.2 -user=spring -password=helehele -url=jdbc:mysql://mysql:3306/summer migrate
```

Note: the `mysql` link, user, password should be the same as in `docker-compose.yml`.


# Open API

Visit the following URL to test the APIs: `http://server-ip/swagger-ui.html`

