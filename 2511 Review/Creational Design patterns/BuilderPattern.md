# <center> Builder Pattern </center>

## Builder Pattern Intent 
Builder is a creational design pattern that lets you construct ***complex objects step by step***. The pattern allows you to produce different types and representations of an object using the **same construction code**.

## Problem
- Imagine a complex object that requires laborious, **step-by-step** initialization/construction of many fields and nested objects.
- Such initialization/construction code is usually buried inside a monstrous constructor with **lots of parameters**.
- Or even worse: scattered all over the client code.

## Solutions
- The Builder pattern suggests that you extract the object construction code out of its own class and move it to separate objects called builders.
- The Builder pattern lets you construct complex objects step by step.
- The Builder doesn’t allow other objects to access the product while it’s being built.
- Director: The director class defines the order in which to execute the building steps, while the builder provides the implementation for those steps.

## Structure
- The Builder interface declares product construction steps that are common to all types of builders.
- Concrete Builders provide different implementations of the construction steps. Concrete builders may produce products that don’t follow the common interface.
- Products are resulting objects. Products constructed by different builders don’t have to belong to the same class hierarchy or interface.
- The Director class defines the order in which to call construction steps, so you can create and reuse specific configurations of products.
- The Client must associate one of the builder objects with the director.