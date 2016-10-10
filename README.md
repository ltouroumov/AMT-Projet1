# AMT-Projet1

## Building

The project uses gradle as a build system (which has the advantage of having readable configuration files)

    ./gradlew clean #optional
    ./gradlew war

## Local Server

The docker-compose.yml mounts the directory containing the build output directly
inside the wildfly container. As such, building the project (`./gradlew war`)
will automatically deploy on the application server.

## Deploying

    docker-compose up --build
