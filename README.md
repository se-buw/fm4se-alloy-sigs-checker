# fm4se-alloy-sigs-checker

This project and worksheet is part of the lecture Formal Methods for Software Engineering. We are using the light-weight formal methods tool Alloy Analyzer.

Usually we use formal methods to analyze software designs by translating analysis problems to formal methods tools. Now we will treat the quality of formal methods specifications as a software analysis problem. In this task we will implement a tool that analyzes properties of Alloy models (using Alloy itself).

## Project Setup

[📼 see the project setup video](https://www.youtube.com/watch?v=40MA55S3F8c&list=PLGyeoukah9NYNMJhcHXLjAGN294O2uXCB&index=6)

1. Install OpenJDK/JDK 
2. Clone the [repository](https://github.com/se-buw/fm4se-alloy-sigs-checker.git)
3. Open in any IDE of your choice (e.g. Eclipse, VS Code, etc.)
4. Run `src/main/java/de/buw/fm4se/ExampleCompilingFromSource.java`
You should see the output as - 
```
feature car is optional and has 2 children
    feature motor is mandatory and has 2 children in a XOR-group
        feature gasoline is optional and has 0 children
        feature electric is optional and has 0 children
    feature comfort is optional and has 2 children in a OR-group
        feature heating is optional and has 0 children
        feature entertainment is optional and has 0 children
electric REQUIRES heating
```

[📼 see the code walk-through and explanation of this task](https://www.youtube.com/watch?v=qa08IzWqSQs&list=PLGyeoukah9NYNMJhcHXLjAGN294O2uXCB&index=7)

## Task 1

Implement a method to determine a list of dead signatures of an Alloy model, i.e., list all signatures that do not have atoms in any instance.

## Task 2

Implement a method to determine a list of core signatures of an Alloy model, i.e., list all signatures that always have atoms except in the empty instance.

