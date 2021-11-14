# <center> Comp2511 Java & O-O Design Basic </center>

## Java Language
- Object-Oriented
- Simple
- Multi-threaded
- Platform-Independent
- Secure
- Memory Management

## Object Collaboration
- Objects are real word entities.
- Objects have state (**Attributes**) and behaviour (**Methods**).
- Defining a class does not actually create an object.
   + An object is instantiated from a class.
   + The object is said to be an inst ance of the class.
   + An object has state but a class doesn't.
   + Two object instances from the same class share the same attributes and methods, but have their own object identity and are independent of each other.

&nbsp;
~~~java
    public class Circle {

        public static final double PI = 3.14159;

        int x = 0, y = 0;
        int r = 6;

        static int no_circles = 0;

        public Circle() {
            super();
            no_circles++;
        }

        public Circle(int x, int y, int r) {
            this();
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public Circle(int x, int y) {
            this(x, y, 5);
        }

        public double getArea() {
            return PI * r * r;
        }

    }
~~~

&nbsp;
## UML Diagram - Unified Modelling Language
- As a design that communicates aspects of your system.
- As a software blue print.
- Sometimes, used for auto code-generation.

## Key principles of OO
- Abstraction
   - Good abstraction help us to accurately represent the knowledge we gather about the problem domain(discard anythingunimportant or irrelevant).
   - write one class called Car and abstract;
      - focus on the common essential qualities of the object.
      - focus on the application domain.
   - Advantages of Abstraction: 
      1. we can declare classes that define only part of an `implementation`,
      2. leaving extended classes to provide specific implementation of some or all the methods.
      3. methods may be declared such that the programmer knows the `interface` definition of an object. 
      4. however, methods can be implemented differently in different subclasses of the abstract class.
   - Some rules about abstract classes:
      1. An abstract class is a class that is declared abstract.
      2. If a class includes abstract methods, then the class itself must be declared abstract.
      3. An abstract class `cannot` be instantiated.
      4. A subclass of an abstract class can be instantiated if it overrides each of the abstract methods of its superclass and provides an implementation for all of them.
      5. If a subclass of an abstract class does not implement all the abstract methods it. inherits, that subclass is itself abstract.

&nbsp;
~~~java
public abstract class Shape {

	public abstract double area();
	public abstract double circumference();

	protected static int count_shapes = 0;

	public void printArea(){
		System.out.println(" (from Shape abstract class, method printArea)> area is: " + this.area());
	}

}
~~~

&nbsp;
~~~java
public class Circle  extends Shape {

	protected static final double pi = 3.14159; 
	protected int p = 0;
	int  x, y;
	protected  int  r;
	protected static int count_circle = 0;

	/**
	 * No-arg constructor
	 */
	public Circle(){
		this.x = 1;
		this.y = 1;
		this.r = 1;

		count_circle++; 
		count_shapes++;
	}
	
	public Circle(int r){
		this.x = 1;
		this.y = 1;
		this.r = r;

		count_circle++; 
		count_shapes++;
	}
	
	/**
	 * Constructor with 2 args 
	 * @param x x axis
	 * @param y y axis
	 */
	public Circle(int x, int y){
		this.setX(x);
		this.setY(y);

		count_circle++; 
		count_shapes++;
	}	

	/**
	 * The method returns circumference of the circle
	 * @return circumference of the circle
	 */
	public double circumference( ) {  
		return 2 * pi * r ; 
	}

	/**
	 * The method returns area of the circle
	 * @return  area of the circle
	 */
	public  double area( ) {
		return  pi * r * r ; 
	}

	
}

~~~
&nbsp;

- Encapsulation
   - Encapsulation of object state implies hiding the object's attributes.
   - An object's attributes represent its individual characteristics or properties, so access to the object's data must be restricted
   - Methods provide explicit access to the object
      - use of getter and setter methods to access or modify the fields

   - Advantages of Encapsulation: 
      1. Encapsulation ensures that an obiects state is in a consistent state.
      2. Encapsulation increases usability.
         - Keeping the data private and exposing the object only through its interface (public methods) provides a clear view of the role of theobject and increases usability 
         - Clear contract between the invoker and the provider, where the client agrees to invoke an object's method adhering to the methodsignature and provider guarantees consistent behaviour of the method invoked (if the client invoked the method correctly)

      3. Encapsulation abstracts the implementation, reduces the dependencies so that a change to a class does not cause a rippling effect on the system.

&nbsp;
~~~java
    public Class Person{
        private String name;
        private int age;
        private float weight;
    }

    public String getName(){ return name; }
    
    public void setName(String name){ this.name = name; }
    
    public int getAge(){ return age; }

    public void setAge(int age){ this.age = age; }

    public float getWeight(){ return weight; }
    
    public void setWeight(float weight){ this.weight = weight; }
~~~
&nbsp;

- Inheritance

   - Inheritance-models a relationship between classes in which one class represents a more general concept (parent or base class) and another a more specialised class (sub-class)
   - Inheritance models a **"is-a"** type of relationship
   - Assume B extends A, B could use all the non-private methods in A
- Association

   - In a **“has-a”** relationship, a class object has an object of another class to store its state or do its work.
   - For example, a Rectangle Is-NOT-a Line. However, we may use a Line to draw a Rectangle.
   - “Has-a” relationships are examples of creating new classes by composition of existing classes (as oppose to extending classes).
   - Two types:
      - `Aggregation` relationship (hollow diamond symbol ◊): The contained item is an element of a collection but it can also exist on its own, e.g., a lecturer in a university or a student at a university
      - `Composition` relationship (filled diamond symbol ♦ in UML diagrams): The contained item is an integral part of the containing item, such as a leg in a desk, or engine in a car


## Designing a Class

- Think carefully about the functionality (methods) a class should offer.
- `Always try to keep data private (local).`
- Consider different ways an object may be created.
- Creating an object may require different actions such as initializations.
- Always initialize data.
- If the object is no longer in use, free up all the associated resources.
- Break up classes with too many responsibilities.
- In OO, classes are often closely related. “Factor out” common attributes and behaviours and place these in a class. Then use suitable relationships between classes (for example, “is-a” or “has-a”).


## Override Methods
- The methods defined by Object can be called by any Java object (instance).
- Often we need to override the following methods:
   - toString()
   - equals()
   - hasCode()

- When a class defines a method using the same name, return type, and by the number, type, and position of its arguments as a method in its superclass, the method in the class overrides the method in the superclass.
- If a method is invoked for an object of the class, it’s the new definition of the method that is called, and not the superclass’s old definition.
- **Polymorphism**
   - An object’s ability to decide what method to apply to itself, depending on where it is in the inheritance hierarchy, is usually called polymorphism.
- A sub-class can override methods in the parent class with its own specialised behaviour.
&nbsp;
~~~java
public class Test {
    
    static class A {
        int i = 4;
        int f() {return i;}
    }

    static class B extends A {
        int i;
        int f() {
            i = super.i + 1;
            return super.f() + i;
        }
    }

    public static void main(String[] args) {
        B newB = new B();
        System.out.println(newB.f());
    }
}
~~~



- However,
   1. if all the three classes define f(), then calling super.f() in class C invokes class B’s definition of the method.
   2. Importantly, in this case, there is no way to invoke A.f() from within class C.
   3. Note that `super.super.f()` is `NOT` legal Java syntax.

## Interfaces in Java
- Interfaces are like abstract classes, but with few important differences.
- All the methods defined within an interface are implicitly `abstract`. (We don’t need to use abstract keyword, however, to improve clarity one can use abstract keyword).
- Variables declared in an interface must be static and final, that means,
they must be constants.
- Just like a class extends its superclass, it also can optionally implements an interface.
- In order to implement an interface, a class must first declare the interface in an implements clause, and then it must provide an implementation for all of the abstract methods of the interface.
- `A class can “implements” more than one interfaces.`
- Interfaces can have `sub-interfaces`, just like classes can have subclasses.
- A sub-interface inherits all the abstract methods and constants of its super-interface, and may define new abstract methods and constants.
- Interfaces can extend more than one interface at a time.

## Visibility Modifiers
Java provides five access modifiers (for variables/methods/classes),
- **`public`** - visible to the world
- **`private`** - visible to the class only
- **`protected`** - visible to the package and all subclasses
- No modifier (**`default`**) - visible to the package

## Constructors
- Good practice to define the required constructors for all classes.
- If a constructor is not defined in a class,
   - `no-argument` constructor is implicitly inserted.
   - this no-argument constructor invokes the superclass’s no-argument constructor.
   - if the parent class (superclass) doesn’t have a visible constructor with no-argument, it results in a compilation error.
- If the first statement in a constructor is not a call to super() or this(), a call to super () is implicitly inserted.
- If a constructor is defined with one or more arguments, no-argument constructor is not inserted in that class.
- A class can have `multiple` constructors, with different signatures.
- The word “this” can be used to call another constructor in the same class.
- To create an instance of a subclass, there are two options:
   1. Use the default “no-arg” constructor.
      - This default constructor will make a default call to super(), which is the constructor in the parent class.
   2. Define a constructor in the sub-class.
      - then, a default constructor is no longer provided.
      - use super() to invoke the parent’s constructor e.g., SavingsAccount calls the constructor of the Account as super (bsb, accountNo, salary).
      - Call to super() must be the first statement of the constructor. If this call is not made, a default call to super() is inserted Constructors are not inherited.

## **`Exceptions`** in Java

**An exception is an event**, which occurs during the execution of a program, that disrupts the normal flow of the program's instructions.

When error occurs, an exception object is created and given to the runtime system, this is called **throwing an exception**.

The runtime system searches the call stack for a method that contains a block of code that can handle the exception.

The exception handler chosen is said to catch the exception.

The Three Kinds of Exceptions
- **`Checked`** exception (IOException, SQLException, etc.)
- **`Error`** (VirtualMachineError, OutOfMemoryError, etc.)
- **`Runtime`** exception (ArrayIndexOutOfBoundsExceptions, ArithmeticException, etc.)

Checked vs. Unchecked Exceptions
- An exception’s type determines whether it’s checked or unchecked.
- All classes that are subclasses of RuntimeException (typically caused by defects in your program’s code) or Error (typically ‘system’ issues) are unchecked exceptions.
- All classes that inherit from class Exception but not directly or indirectly from class RuntimeException are considered to be checked exceptions.

## Collections in Java
A collections framework is a unified architecture for representing and manipulating collections. A collection is simply an object that groups multiple elements into a single unit.

All collections frameworks contain the following:
- Interfaces: allows collections to be manipulated independently of the details of their representation.
- Implementations: concrete implementations of the collection interfaces.
- Algorithms: the methods that perform useful computations, such as searching and sorting, on objects that implement collection interfaces.
   - The algorithms are said to be polymorphic: that is, the same method can be used on many different implementations of the appropriate collection interface.

|Interface|Hash Table|Resizable Array|Balanced Tree|Linked List|Hash Table + Linked List|
|-|-|-|-|-|-|
Set|HashSet||TreeSet||LinkedHashSet|
List||ArrayList||LinkedList
Deque||ArrayDeque||LinkedList
Map|HashMap||TreeMap||LinkedHashMap

&nbsp;
~~~java
public class Customer implements Comparable<Customer>{
	
	String name;
	int rewardsPoints;
	int postcode;
	
	public Customer(String name, int rewardsPoints, int postcode) {
		super();
		this.name = name;
		this.rewardsPoints = rewardsPoints;
		this.postcode = postcode;
   }
   
	public int getPostcode() {
		return postcode;
	}

	@Override
	public String toString() {
		String s1 = "[" + getName() + "," + getRewardsPoints() + "," + getPostcode() + "]";
		
		return s1;
	}
	
	
	@Override
	public int compareTo(Customer other) {

		/*
		 * if( this.getPostcode() < other.getPostcode()) { return -1; } else if (
		 * this.getPostcode() == other.getPostcode()) { return 0; } else { return 1; }
		 */
	
		return this.getPostcode() - other.getPostcode() ;
		//return this.getName().compareTo(other.getName()); 
	}	

}
~~~

~~~java
public class MyPostcodeRewardspointCmp implements Comparator<Customer>{

	@Override
	public int compare(Customer o1, Customer o2) {
		
		if(o1.getPostcode() != o2.getPostcode()) {
			return o1.getPostcode() -  o2.getPostcode() ; 
		}
		
		// now postcodes are same .. 
		return o1.getRewardsPoints() -  o2.getRewardsPoints() ; 	
	}

}
~~~