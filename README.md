# 🛠️Computer Repair Services Software System🛠️

This software system, part of a Java-based Client-Server System with MySQL Integration project, manages records of computer repair service operations. It facilitates client-server communication with a MySQL database backend, incorporating an In-between Communication Side known as Zajednicki. This system handles spare computer parts, enabling seamless replacement in case of malfunctions. It meticulously tracks part availability, quantities, and evaluates service feasibility based on component availability. The project structure includes three main components: ServerskiProgram (Server-side), KlijentskiProgram (Client-side), and Zajednicki (In-between Communication Side).

## Key Components

- **👨‍🔧Service Personnel👨‍🔧**: Individuals who interact with the system to manage repair operations.
- **📑Fault Reports📑**: Instances where clients report issues with their computers.
- **👨‍💼Clients👨‍💼**: Individuals seeking repair services.
- **📝Service Reports📝**: Documentation of serviced computers.

## Functionality

1. **Service Personnel Login**: Authentication for service personnel.
2. **Spare Part Creation**: Adding new spare parts to the system.
3. **Computer Fault Reporting**: Logging computer issues reported by clients.
4. **Fault Report Search**: Finding specific fault reports.
5. **Client Creation**: Adding new clients to the system.
6. **Client Search**: Locating clients within the system.
7. **Client Information Update**: Modifying client details.
8. **Service Report Generation**: Creating reports for completed service operations (Complex Use Case).
9. **Service Report Search**: Finding specific service reports.
10. **Service Report Update**: Modifying service report details (Complex Use Case).

## Use Cases

Identified nine use cases for this application, where the service personnel is the primary actor:

1. Service personnel login to the system.
2. Creating spare parts.
3. Reporting computer faults.
4. Searching fault reports.
5. Creating clients.
6. Searching clients.
7. Modifying client information.
8. Generating service reports (Complex Use Case).
9. Searching service reports.
10. Modifying service reports (Complex Use Case).


### SOFTWARE ARCHITECTURE FOR A SOCKET-BASED CLIENT-SERVER APPLICATION
<p align="center">
  <img width="80%" src="https://ibb.co/FwYzrrY">
</p>

## Use of Server Side(ServerskiProgram)

The server side hosts the main application logic and database interactions. It manages user authentication, data storage, and processing of service requests.

## Use of Client Side(KlijentskiProgram)

The client side provides the interface for service personnel, allowing them to interact with the system functionalities. It offers a user-friendly environment for personnel to manage repair operations, report faults, and generate service reports.

## Use of In-between Communication Side(Zajednicki) with Sockets and Serialization/Deserialization

The in-between communication side facilitates communication between the client and server using sockets. It handles data transmission between the client and server, utilizing serialization for converting objects into a array of bytes(format suitable for transmission) and deserialization for reconstructing objects at the receiving end. This process ensures efficient and reliable communication between the client and server components of the system.
