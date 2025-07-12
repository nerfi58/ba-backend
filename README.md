# Better Achievements

## Requirements:

- Java 21
- Docker

## Setup

1. Setup database and local mail server. Specify username and password for your local database.

```shell script
.\gradlew setup -Pdbuser={user} -Pdbpassword={password}
```

2. Copy and rename `application-dev.example.properties` to `application-dev.properties`

```shell script
cp .\src\main\resources\application-dev.example.properties .\src\main\resources\application-dev.properties
```

3. Specify these values in `application-dev.properties`:
    * `quarkus.datasource.username` (username you provided in first step)
    * `quarkus.datasource.password` (password you provided in first step)
    * `security.password.pepper` (pepper used for password hashing, for local development any string is fine)

4. Generate public and private key

```shell script
openssl genpkey -algorithm RSA -out src/main/resources/private_key.pem -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in src/main/resources/private_key.pem -out src/main/resources/public_key.pem
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
.\gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```
