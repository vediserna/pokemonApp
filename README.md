#Pokemon App

Pokemon app! See information about every pokemon there is ⭐️
This app uses resources fetched from https://pokeapi.co.

This source code can be found on [Github](https://github.com/vediserna/pokemonApp).

The App consists of a React frontend, and a backend built with Spring Boot, Kotlin & gradle.

#### Clone with SSH:

1. `git@github.com:vediserna/pokemonApp.git`
2. cd pokemonApp

### Clone with HTTPS:

1. `https://github.com/vediserna/pokemonApp.git`
2. cd pokemonApp

## How to run locally

# Set up:

- Java 11
  - (macos) `brew install java11`
  - (windows) [Oracle jdk11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- Node 16
  - (macos)
    - `brew install nvm`
    - `nvm install 16.4.1`
    - `nvm use 16`
  - (windows)
    - Download from [Nodejs.org](https://nodejs.org/en/download/)

# Run

`./run-locally`
The front- and backend should be built and started up. When the front end starts up it will open up the page in your browser.
Note: The server will use port 8080 and the front end will run on port 3000. If those ports are not available, the script will fail to start the services.

The back end can of course be opened in an IDE and run from there.

# Tests

To run backend tests, run `./gradlew test` from the server folder `/pokemonApp/pokemonApp`.
