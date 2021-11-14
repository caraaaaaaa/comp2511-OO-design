# <center> Strategy Pattern </center>

This pattern defines a family of algorithms, encapsulates each one.

## Motivation
- Need a way to adapt the behaviour of an algorithm at runtime
## Intent
- Define a family of algorithms, encapsulate each one, and make them **interchangeable**.
- Strategy pattern is a behavioural design pattern that lets the algorithm vary independently from the context class using it.

## Applicability
- Many related classes differ in their behaviour.
- A context class can benefit from different variants of an algorithm.
- A class defines many behaviours, and these appears as multiple conditional statements (e.g., if or switch). Instead, move each conditional branch into their own concrete strategy class.

## Benefits
- Uses composition over inheritance which allows better decoupling between the behaviour and context class that uses the behaviour

## Drawbacks
- Increases the number of objects
- Client must be aware of different strategies

## Pseudocode here ~
~~~ java
// The strategy interface declares operations common to all
// supported versions of some algorithm. The context uses this
// interface to call the algorithm defined by the concrete
// strategies.
interface Strategy is
    method execute(a, b)
~~~
~~~ java
// Concrete strategies implement the algorithm while following
// the base strategy interface. The interface makes them
// interchangeable in the context.
class ConcreteStrategyAdd implements Strategy is
    method execute(a, b) is
        return a + b
~~~
~~~ java
class ConcreteStrategySubtract implements Strategy is
    method execute(a, b) is
        return a - b
~~~
~~~ java
class ConcreteStrategyMultiply implements Strategy is
    method execute(a, b) is
        return a * b
~~~
~~~ java
// The context defines the interface of interest to clients.
class Context is
~~~
~~~ java 
    // The context maintains a reference to one of the strategy
    // objects. The context doesn't know the concrete class of a
    // strategy. It should work with all strategies via the
    // strategy interface.
    private strategy: Strategy
~~~
~~~ java
    // Usually the context accepts a strategy through the
    // constructor, and also provides a setter so that the
    // strategy can be switched at runtime.
    method setStrategy(Strategy strategy) is
        this.strategy = strategy

    // The context delegates some work to the strategy object
    // instead of implementing multiple versions of the
    // algorithm on its own.
    method executeStrategy(int a, int b) is
        return strategy.execute(a, b)
~~~
~~~ java
// The client code picks a concrete strategy and passes it to
// the context. The client should be aware of the differences
// between strategies in order to make the right choice.
class ExampleApplication is
    method main() is
        Create context object.

        Read first number.
        Read last number.
        Read the desired action from user input.

        if (action == addition) then
            context.setStrategy(new ConcreteStrategyAdd())

        if (action == subtraction) then
            context.setStrategy(new ConcreteStrategySubtract())

        if (action == multiplication) then
            context.setStrategy(new ConcreteStrategyMultiply())

        result = context.executeStrategy(First number, Second number)

        Print result.
~~~