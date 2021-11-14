# <center> Visitor Pattern </center>

Visitor is a behavioral design pattern that **adds new operations/behaviors to the existing objects**, without modifying them.

The visitor design pattern is a way of **`separating` an algorithm from an object structure** on which it operates.

A practical result of this separation is the ability to add new operations to existing object structures without modifying the structures.
 
It is one way to follow the open/closed principle.
 
A visitor class is created that implements all of the appropriate specializations of the virtual operation/method.

The visitor takes the instance reference as input, and implements the goal (additional behavior).

Visitor pattern can be added to public APIs, allowing its clients to perform operations on a class without having to modify the source.

## Problem
A geographic information structured as one colossal graph. Each node of the graph may represent a city, an industry, a sightseeing area, etc. Each node type is represented by its own class, while each specific node is an object. You want to export the graph into XML format.

## Solution
- The Visitor pattern suggests that you place the new behavior into a separate class called visitor, instead of trying to integrate it into existing classes.
- The original object that had to perform the behavior is now passed to one of the visitor’s methods as an argument, providing the method access to all necessary data contained within the object (see the example for more clarification).
- The visitor class need to define a set of methods, one for each type. For example, a city, a sightseeing place, an industry, etc.
- The visitor pattern uses a technique called “Double Dispatch” to execute a suitable method on a given object (of different types).
   - An object “accepts” a visitor and tells it what visiting method should be executed. See the example for more clarifications.
   - One additional method allows us to add further behaviors without further altering the code.

## Applicability:
Moving operations into visitor classes is beneficial when,
- many unrelated operations on an object structure are required,
- the classes that make up the object structure are known and not expected to change,
- new operations need to be added frequently,
- an algorithm involves several classes of the object structure, but it is desired to manage it in one single location,
- an algorithm needs to work across several independent class hierarchies.
## Limitation:
- extensions to the class hierarchy more difficult, as a new class typically require a new visit method to be added to each visitor.