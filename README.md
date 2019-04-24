# Overview

Purpose of this Generator is to ease development of Spring Cloud Streams microservices by supporting defining them in AsyncAPI. The problem was decomposed into the following parts:
1) Generate Project Structure 
Project structure and build is taken care of by the Spring Initializr project, and thus was reused. 
2) Generate POJO's based off of the input/output messages 
Since AsyncAPI supports defining the message payloads as JSON Schema, this enables code generators to generate the POJOs which represent these data structures. Since I am not a code generator wizard, I leveraged dentrassi's java generator and commented out a few lines so that it only generated the message payload objects. That is why this code contains those packages.
3) Generate SCS application 
In order to generate the SCS Application, the associated java class must have the proper annotations specifying whether it is a source, sink or processor. In addition the method must so that it has a method signature which either sink, source or processor (@EnableBinding) and matches the method to the appropriate @InboundChannelAdapter, @StreamListener or @Output. This enables the user to simply add their business logic vs having to understand the mechanics of SCS
4) Generate Application.YAML - binds the application to the runtime though binder interface 
The Application.yaml file purpose is to map the INPUTS/OUTPUTS to the underling binding implementation. In this case, this generator works with Solace's but could easily be extended to support others. 

# Quick Setup
## Building the Project
1. Clone the Github repository
1. `cd AsyncAPI-Spring-Cloud-Streams-Generator`
1. Update the [application.propertes](./src/main/resources/application.properties) to have suitable defaults
1. `mvn clean package`

## Generating an Application
1. `java -jar target/AsyncAPI-SpringCloudStreams-Generator-0.0.1-SNAPSHOT.jar`

   You may also override your default application properties using the `-D<name>=<value>` Java CLI parameters.
   
   For example, you can force the generation of a source application with:
   ```bash
   java -Dscs.scsType=SOURCE -jar target/AsyncAPI-SpringCloudStreams-Generator-0.0.1-SNAPSHOT.jar
   ```
   
   Please refer to [SpringCloudStreamsGeneratorProperties.java](./src/main/java/com/solace/events/asyncapi/spring/cloud/streams/generator/SpringCloudStreamsGeneratorProperties.java) for the most up-to-date set of configuration properties.
2. The application is now generated into the `initializr` directory relative to where you run it (`AsyncAPI-Spring-Cloud-Streams-Generator/tmp{{Random Numbers}}/{{scs.baseDir}}` in our case).

## Running the Generated Application
1. `cd AsyncAPI-Spring-Cloud-Streams-Generator/tmp{{Random Numbers}}/{{scs.baseDir}}`
2. Update `src/main/resources/application.yml` with the connection credentials to your messaging infrastructure
3. `mvn spring-boot:run`