## GL Java ProCamp 2018 Final task

### Develop application based on 'Entry task' project with Spring Core (or Boot) and REST WS. Use any DB
* Make RESTful web services for:
    * Add, edit, delete a aircraft,
    * Get calculated total passenger capacity and cargo capacity of all the aircrafts in the airline,
    * Get sorted list of all aircrafts in the airline. Sort the aircrafts by flight range (from smaller to larger),
    * Find aircraft corresponding to the specified range of fuel consumption parameters (liters per hour),
    * Find aircraft which corresponding to the specified passenger capacity and the flight range (strict conformity).
* Unit test coverage more then 65%.


## GL Java ProCamp 2018 Entry task

### Create console application “Airline”:
1. Define aircraft class hierarchy. There should be 3 levels of the hierarchy.
2. Create an airline company that stores airplanes.
3. Calculate total capacity and carrying capacity of all the aircraft in the airline.
4. Sort the aircrafts by flight range (from smaller to larger) and display it on screen.
5. Find aircraft corresponding to the specified range of fuel consumption parameters (liters per hour).
6. Console menu should contain following commands at bare minimum:
    * Calculate the total capacity and carrying capacity of all the aircraft in the airline.
    * Display the list of aircraft of the company sorted by flight range.
    * Find airplanes corresponding to a given range of fuel consumption parameters.

### Application must meet the requirements:
1. Stick to java code convention.
2. Use OOP : classes, inheritance, polymorphism, encapsulation, interfaces, etc.
3. Each class must have a meaningful name and reasonable composition.
4. Inheritance should only be applied when it makes sense.
5. Classes must be correctly decomposed into packages

### Requirements for task design:
1. Source code must be stored on GitHub, the applicant must provide read access to the repository.
2. The code should be compiled by Java 8.
3. The project should be assembled with maven install command or gradle build (optionally).
4. Repository must contain root file README.MD with instructions for launching the application and any other necessary documentation for the application (in English).
5. Running the application should not require an application server or installation of any software except for Java and maven / gradle.
6. Data can be stored in any format.  Files can be (but not necessarily) used to store aircraft.
7. Opensource libraries and frameworks are allowed.

### Evaluation criteria:
* functional correctness according to the technical requirements,
* application usability,
* readability, maintainability and compliance of the code with OOP and SOLID principles,
* documentation for the application and  the code,
* unit tests availability,
* any non-standard technical solutions,
* any additional features not specified in the technical requirements, but making the application more functional or convenient,
* task execution time.

Tasks that do not meet the requirements will not be considered!

### Refinement of the task:
By total capacity and carrying capacity of all the aircraft in the airline we mean total passenger capacity and cargo capacity of all the aircrafts in the airline. 

### Rewiew comments & feedback:
>_To switch to an **entry task stage** run **git checkout [c89cc42](https://github.com/Olezha/Airline/tree/c89cc42dcbb56e653b7e9fd9db0a72b4b08f07c2)**_

Score | functional correctness (1-5) | application usability (1-5) | OOP and SOLID (1-5) | documentation (1-5) | unit tests (1-5) | non-standard technical solutions (y/n) | additional features (y/n) | fast completion time (y/n)
----- | ---------------------------- | -------------------------- | ------------------- | ------------------- | --------------- | ------------------------------------- | ------------------------- | -------------------------
**4.11** | 5 | 3 | 2 | 3 | 4 | y | n | y

**Overall comment**: Difficult to assess application: difficult for user interface, bad understanding of OOP, a lot of frameworks, db with spring-data for storing data, but many decisions are controversial
