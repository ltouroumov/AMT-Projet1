# AMT-Projet1

## Building

The project uses gradle as a build system (which has the advantage of having readable configuration files)

    ./gradlew clean #optional
    ./gradlew war

## Local Server

Install a local distribution of wildfly in wildfly/server

    # Start Server
    ./gradlew wildfly
    
    # Redeploy application
    ./gradlew war

## Deploying

    docker-compose up --build
