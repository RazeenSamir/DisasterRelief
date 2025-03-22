# Disaster Relief Management System

## Overview
The **Disaster Relief Management System** is a Java-based application designed to streamline emergency response efforts. It enables efficient tracking of resources, volunteers, and affected areas to improve disaster relief coordination.

## Features
- **Resource Tracking**: Monitors available supplies and their distribution.
- **Volunteer Management**: Registers and assigns volunteers to specific tasks.
- **Emergency Response Coordination**: Helps allocate resources to affected areas efficiently.
- **Client Class**: Provides a command-line interface for managing disaster relief operations.
- **Testing Class**: Includes unit tests to ensure reliability and correctness.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/disaster-relief.git
   ```
2. Navigate to the project directory:
   ```sh
   cd disaster-relief
   ```

## Usage
Compile and run the application:
```sh
javac Client.java
java Client
```

### Basic Commands
- **Register a volunteer**:
  ```sh
  java Client registerVolunteer <name> <contact>
  ```
- **Add resources**:
  ```sh
  java Client addResource <resource-name> <quantity>
  ```
- **Allocate resources**:
  ```sh
  java Client allocate <resource-name> <affected-area>
  ```
- **View all active operations**:
  ```sh
  java Client status
  ```

## Testing
To run unit tests, execute:
```sh
javac Testing.java
java Testing
```

## Contributing
If you'd like to contribute:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Create a pull request.

## Contact
For questions or suggestions, reach out via GitHub Issues or contact me at rsamir9@hotmail.com.


