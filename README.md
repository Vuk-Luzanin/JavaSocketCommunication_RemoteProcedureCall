# Client-Server Application in Java

This project implements a simple client-server application in Java, where the client sends requests to the server for performing operations like setting a variable and adding numbers. The server handles the requests and responds with the results. The communication is done using TCP sockets.

## Overview

The project consists of three main components:
1. **Client**: Sends requests to the server to perform operations and waits for the server's response.
2. **Server**: Receives requests from the client, processes them using specific services, and sends back the results.
3. **Common**: Contains shared code used by both the client and server for communication (sending and receiving messages via sockets).

### Key Operations:
- **setI(int ni)**: Sets the value of a variable `i` on the server and returns the old value of `i`.
- **add(int a, int b)**: Adds two integers, `a` and `b`, along with the current value of `i` and returns the result.

## Project Structure

The project is divided into the following packages:

### 1. **client** 
- **Client.java**: Main class that connects to the server and communicates using `ClientService`.
- **ClientService.java**: Handles communication with the server, sends requests, and receives responses.

### 2. **server** 
- **Server.java**: Main class that listens for incoming client connections, processes requests in separate threads using `RequestHandler`.
- **RequestHandler.java**: Handles the client's request in a separate thread, calling methods from `ServerService`.
- **ServerService.java**: Contains business logic for handling requests like `setI` and `add`.
  
### 3. **common** 
- **Service.java**: A shared class for both the client and server to handle socket communication (sending and receiving messages).

## Requirements

- JDK 8 or higher
- Basic knowledge of Java and socket programming

## Usage

### Running the Server
1. Open a terminal and navigate to the server directory.
2. Compile and run the `Server.java` file.
   ```bash
   javac server/Server.java
   java server.Server
   
The server will start listening for client connections on port 5555. The server will handle each incoming request from a client in a separate thread.

### Running the Client
1. Open a terminal and navigate to the client directory.
2. Compile and run the `Client.java` file.
   ```bash
   javac client/Client.java
   java client.Client

The client will connect to the server on localhost at port 5555 and start sending requests for operations (such as setting a variable or performing addition).

### Communication Protocol
The client and server communicate using a custom protocol, where messages are formatted as:
   ```bash
  #<command>#<parameter1>#<parameter2>#
