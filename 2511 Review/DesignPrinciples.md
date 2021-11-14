# <center> Comp2511 Design Principles </center>

“Design principles are key notions considered fundamental to many different software design approaches and concepts.”

"The critical design tool for software development is a mind well educated in design principles."

## Good Software Design
Aims: Highly cohesive and Loosely coupled systems

- SOLID Principles
- Separation of Concerns (SOC) (Djikstra, 1974)
- Less Fragile Systems - (Maintainable, Reusable, Extensible Code)
- Design Patterns (GOF)
   - Program to an interface, not to an implementation.
   - Object composition over class inheritance.
- Pragmatic Programming
   - **DRY**
(Don’t Repeat Yourself)
   - **KISS**
(keep it simple, stupid!)

## Bad Code
- Reasons **`why`** we write bad code:
   1. Requirements change in ways that original design did not anticipate.
   2. But changes are not the issue
      -  changes requires **`refactoring`** and refactoring requires **`time`** and we say we do not have the time.
      -  **`Business pressure`** - changes need to be made quickly – “quick and dirty solutions”.
      -  changes may be made by developers not familiar with the original design philosophy.
- Duplicated Code
   - Same code structure in more than one place or
   - Same expression in two sibling classes
- Long Method
- Large Class (when a class is trying to do too much, it often shows up as too many instance variables)
- Long Parameter List
- Divergent Change (when one class is commonly changed in different ways for different reasons)
- Shotgun Surgery (The opposite of divergent change, when you have to make a lot of little changes to a lot of different classes)

## Design Smell
A design smell is a symptom of **poor design** often caused by violation of key design principles. It has structures in software that suggest refactoring. 

1. **`Rigidity & Fragility`**
   - Rigidity
      - Tendency of the software being too difficult to change even in simple ways.
      - A single change causes a cascade of changes to other dependent modules. 
   - Fragility
      - Tendency of the software to break in many places when a single change is made.
   - Rigidity and fragility complement each other – aim towards minimal impact, when a new feature or change is needed.

2. **`Immobility, Viscosity & Opacity`**
   - Immobility
      - Design is hard to reuse.
      - Design has parts that could be useful to other systems, but the effort needed and risk in disentangling the system is too high.
   - Viscosity
      - Software viscosity – changes are easier to implement through ‘hacks’ over ‘design preserving methods’.
      - Environment viscosity – development environment is slow and in-efficient.
   - Opacity
      - Tendency of a module to be difficult to understand.
      - Code must be written in a clear and expressive manner.

3. **`Needless complexity & Needless repetition`**
   - Needless complexity
      - Contains constructs that are not currently useful.
      - Developers ahead of requirements.
   - Needless repetition
      - Design contains repeated structures that could potentially be unified under a single abstraction.
      - Bugs found in repeated units have to be fixed in every repetition.

## Design Quality
The design quality of software is characterised by

- **`Coupling`**
   - Is defined as the **degree of interdependence** between components or classes.
   - High coupling occurs when one component A depends on the internal workings of another component B and is affected by internal changes to component B.
   - High coupling leads to a complex system, with difficulties in maintenance and extension…eventual software rot.
   - Aim for loosely coupled classes - allows components to be used and modified independently of each other.
   - But **“zero-coupled”** classes are not usable – striking a balance is an art!

- **`Cohesion`**
   - The degree to which all elements of a component or class or module
**work together** as a functional unit.
   - Highly cohesive modules are:
      - much easier to maintain and less frequently changed and have
higher probability of reusability
   - Think about
      - How well the lines of code in a method or function work together
to create a sense of purpose?
      - How well do the methods and properties of a class work together
to define a class and its purpose?
      - How well do the classes fit together to create modules?
   - Again, just like zero-coupling, **do not put all the responsibility into a
single class** to avoid low cohesion!

Good software aims for building a system with loose coupling and high cohesion among its components so that software entities are:
- **Extensible**
- **Reusable**
- **Maintainable**
- **Understandable**
- **Testable**


## **SOLID** Principals
- **S**ingle responsibility principle: A class should only have a single responsibility.
- **O**pen–closed principle: Software entities should be open for extension, but closed for modification.
- **L**iskov substitution principle: Objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program.
- **I**nterface segregation principle: Many client-specific interfaces are better than one general-purpose interface.
- **D**ependency inversion principle: One should "depend upon abstractions, not concretions.”

## **DESIGN PRINCIPALS**
**Design Principle #1** -- The Principle of Least Knowledge or Law of Demeter

   - Classes should know about and **interact with as few classes as possible**.
   - Reduce the interaction between objects to just a few close “friends”.
   - These friends are **“immediate friends” or “local objects”**.
   - Helps us to design “loosely coupled” systems so that changes to one part of the system does not cascade to other parts of the system.
   - The principle **limits interaction** through a set of rules.

A method in an object should only invoke methods of:

   - The object itself
   - The object passed in as a parameter to the method
   - Objects instantiated within the method
   - Any component objects
   - And not those of objects returned by a method

e.g. **DONT DO** 
~~~java
  object.get(name).get(thing).remove(node)
~~~

- ***RULE 1*** --  A method M in an object O can call on any other method within O itself.

~~~java
    public class M {
        public void methodM() {
            this.methodN();
        }
        public void methodN() {
            // do something
        }
    }
~~~

- ***RULE 2*** --  A method M in an object O can call on any methods of
parameters passed to the method M. 

~~~java
    public class O {
        public void M(Friend f) {
            // Invoking a method on a parameter passed to the method is
            // legal
            f.N();
        }
    }
    public class Friend {
        public void N() {
            // do something
        }
    }
~~~

- ***RULE 3*** --  A method M can call a method N of another object, if that
object is instantiated within the method M.

~~~java
    public class O {
        public void M() {
            Friend f = new Friend();
            // Invoking a method on an object created within the
            // method is legal
            f.N();
        }
    }
    public class Friend {
        public void N() {
            // do something
        }
    }
~~~

- ***RULE 4*** --  Any method M in an object O can call on any methods of any
type of object that is a direct component of O.

~~~java
    public class O {
        public Friend instanceVar = new Friend();
        public void M4() {
            // Any method can access the methods of the friend class
            // F through the instance variable "instanceVar"
            instanceVar.N();
        }
    }
    public class Friend {
        public void N() {
            // do something
        }
    }
~~~

**Design Principle #2** -- Liskov Substitution Principle

If for each object o1 of type S there is an object o2 of type T such that for all programs P defined in terms of T, the behavior of P is unchanged when o1 is substituted for o2 then S is a subtype of T. Subtypes must be substitutable for their base types.

**LSP** states that subtypes must be substitutable for their base types.
~~~
Board board = new Board3D()
~~~
But, when you start to use the instance of Board3D like a Board, things go wrong.
~~~
Artillery unit = board.getUnits(8,4)
~~~
Inheritance and LSP indicate that any method on Board should be able to use on a Board3D, and that Board3D can stand in for Board without any problems, so the above example clearly violates LSP.

Solving this problem:
   - Delegation – delegate the functionality to another class
   - Composition – reuse behaviour using one or more classes with composition

**Design Principle #3**

Identify aspects of your code that varies and “encapsulate” and separate it from code that stays the same, so that it won’t affect your real code.

**Design Principle #4**

Program to a an interface, not to an implementation.

e.g.,
~~~java 
Dog d = new Dog(); d.bark(); // programming to an implementation
Animal a = new Dog(); a. makeSound(); // programming to an interface
~~~

**Design Principle #5**

Favour composition over inheritance.
## Rules for Method Overriding

- The argument list should be exactly the **`same`** as that of the overridden method.
- The access level cannot be more **`restrictive`** than the overridden method’s access level. E.g. if the super class method is declared public then the overriding method in the sub class cannot be either private or protected.
- A method declared **`final`** cannot be overridden.
- **`Constructors`** cannot be overridden.

## Refactoring
The process of restructuring (changing the internal structure of software) software to make it easier to understand and cheaper to modify without changing its external, observable behaviour.

- Refactoring improves design of software.
- Refactoring Makes Software Easier to Understand.
- Refactoring Helps You Find Bugs.
- Refactoring Helps You Program Faster.
- Refactoring helps you to conform to design principles and avoid design smells.

## Improving the Design

- **Technique #1** -- Extract Method
   - Find a logical clump of code and use Extract Method. Like the switch statement.
   - Scan the fragment for any variables that are local in scope to the method we are looking at. (Rental r and thisAmount)
   - Identify the changing and non-changing local variables.
   - Non-changing variable can be passed as a parameter.
   - Any variable that is modified needs more care, if there is only one, you could simply do a return.

- **Technique #2** -- Rename variable
   - Is renaming worth the effort? Absolutely
   - Good code should communicate what it is doing clearly, and variable names are a key to clear code. Never be afraid to change the names of things to improve clarity.

- **Technique #3** -- Move method
   - Re-examine method calculateRental() in class Customer
   - Method uses the Rental object and not the Customer object
   - Method is on the wrong object

- **Technique #4** --  Replace Temp With Query
   - A technique to remove unnecessary local and temporary variables
   - Temporary variables are particularly insidious in long methods and you can loose track of what they are needed for
   - Sometimes, there is a performance price to pay

- **Technique #5** -- Replacing conditional logic with Polymorphism
   - The switch statement – an obvious problem, with two issues
   - class Rental Is tightly coupled with class Movie - a switch statement based on the data of another object – not a good design
   - There are several types of movies with its own type of charge, hmm… sounds like inheritance

## Code Smells 
Structures in implementation of code such as large methods, classes with multiple responsibilities, complex conditional statements that lead to poor code.

- **`Bloaters`**: Code, Methods and classes that have grown in size, that they are hard to work with Long Method, Large Class, Long Parameter List, Data Clumps
- **`OO Abusers`**: Result from incorrect or incomplete application of OO principles. (Switch statements, Refused Bequest)
- **`Change Preventers`**: Code changes are difficult (rigid code)(Divergent change, Shot Gun Surgery)
- **`Dispensables`**: Code that is pointless and unnecessary(Comments, Data Class, Lazy Class, Duplicate code)
- **`Couplers`**: Excessive coupling between classes (Feature Envy, Inappropriate intimacy, Message Chains)

- Long Method Problem Solutions
   - Refactoring Techniques – Extract Method (new Methods)

      - More readable code ( The new method name should describe the method's purpose )
      - Less code duplication, more reusability
      - Isolates independent parts of code, meaning that errors are less likely
      - A very common refactoring technique for code smells
   
   - Refactoring Techniques: Introduce Parameter Object (new Object)

      - Methods contain a repeating group of parameters, causing code duplication
      - Consolidate these parameters into a separate class. it also helps to move the methods for handling this data. Beware, if only data is moved to a new class and associated behaviours are not moved, this begins to smell of a Data Class
  
   - Refactoring Technique: Replace Temp With Query
      - Move the entire expression to a separate method and return the result from it.
      - Query the method instead of using a variable
      - Reuse the new method in other methods 
  
   - Refactoring Technique: Extract Class
      - Having all the phone details in class Customer is not a good OO design and also breaks SRP
      - Refactor into two separate classes, each with its appropriate responsibility

- Large Class Problem Solutions

   - May have
      - A large number of instance variables
      - Several methods
   - Typically lacks cohesion and potential for duplicate code smell

   - Solution:
      - Bundle group of variables via Extract Class or Extract Sub-Class

- Long Parameter List

   - Too many parameters to remember
   - Bad for readability, usability and maintenance
   - Solutions: try placing a query call inside the method body via replace

- Data Clumps

   - Different parts of the code contain identical groups of variables e.g., fields in many classes, parameters in many method signatures
   - Can lead to code smell Long Parameter List
   - Solution: Move the behaviour to the data class via Move Method
      - If repeating data comprises the fields of a class, use Extract Class to move the fields to their own class.
      - If the same data clumps are passed in the parameters of methods, use Introduce Parameter Object to set them off as a class.
      - If some of the data is passed to other methods, think about passing the entire data object to the method instead of just individual fields Preserve Whole Object will help with this.

- Refused Bequest

   - A subclass uses only some of the methods and properties inherited from its parents
   - The unneeded methods may simply go unused or be redefined and give off exceptions
   - Often caused by creating inheritance between classes only by the desire to reuse the code in a super-class
   - Solution:
      - If inheritance makes no sense and the subclass really does have nothing in common with the superclass, eliminate inheritance in favour of Replace Inheritance with Delegation
      - If inheritance is appropriate, but super class contains fields and methods not applicable to all classes, then consider the following options
         - Create a new subclass
         - Apply Push Down Field to move field relevant only to subclass from superclass
         - Apply Push Down Method to move behaviour from super class to sub class, as behaviour makes sense only to sub class
         - Often, you may apply an Extract Sub-Class Class to combine the above steps

- Duplicate Code
- Feature Envy
   - A method that is more interested in a class other than the one it actually is
   - Invokes several methods on another object to calculate some value
   - Creates unnecessary coupling between the classes

   - Solution: A goal of OO design is to put the methods with its associated data
      - So the method must moved to the relevant class via Move Method
      - If only part of a method accesses the data of another object, use Extract Method followed by Move Method to move the part in question
      - If a method uses functions from several other classes, first determine which class contains most of the data used. Then place the method in this class along with the other data.

- Divergent Change: One class is changed in different ways for different reasons
   - Solution: Any change to handle a variation should change a single class, and all the typing in the new class should express the variation.
   - To clean this up you identify everything that changes for a  particular cause and use Extract Class to put them all together

- Shot Gun Surgery: A small change in the code forces lots of little changes to different classes
   - Solution:
      - Use Move Method or Move Field to put all the changes into a single class
      - Often you can use Inline Class to bring a whole bunch of behaviour together.
      - Divergent change is one class that suffers many kinds of changes, and shotgun surgery is one change that alters many classes.

- Data Classes

   - Problem: Classes that just have attributes with setters and getters and no behaviour. One of the goals of OO design is to put behaviour where the data is.
   - Solution: Move the behaviour to the data class via Move Method.

- Lazy Classes
  
   - Problem: Classes that aren’t doing much to justify their existence (maintenance overhead).
   - Solution:
      - Move the data (postcode) from lazy class PostCode to the class Address
      - Delete the lazy class

- Switch Statements

   -  Problem: Switch statements are bad from an OO design point of view
   -  Solution: Replace switch statements with a polymorphic solution based on Strategy Pattern applying a series of refactoring techniques