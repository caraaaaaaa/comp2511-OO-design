# <center> Decorator Pattern </center>

Java I/O Decorator Pattern
## Intent
"Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to sub-classing for extending functionality."
- Decorator design patterns allow us to selectively add functionality to an object (not the
class) at runtime, based on the requirements.
- Original class is not changed (Open-Closed Principle).
- Inheritance extends behaviors at compile time, additional functionality is bound to all the instances of that class for their life time.
- The decorator design pattern prefers a composition over an inheritance. Its a structural pattern, which provides a wrapper to the existing class.
- Objects can be decorated multiple times, in different order, due to the recursion involved
with this design pattern. See the example in the Demo.
- Do not need to implement all possible functionality in a single (complex) class.

## Structure
- Client : refers to the Component interface.
- Component: defines a common interface for Component1 and Decorator objects
- Component1 : defines objects that get decorated.
- Decorator: maintains a reference to a Component object, and forwards requests to this component object (component.operation())
- Decorator1, Decorator2, ... : Implement additional functionality (addBehavior()) to be performed before and/or after forwarding a request.
- Given that the decorator has the same supertype as the object it decorates, we can pass around a decorated object in place of the original (wrapped) object.
- The decorator adds its own behavior either before and/or after delegating to the object it decorates to do the rest of the job.

Example : Starbuzz Coffee!!!
~~~java 
public abstract class Beverage {
	String description = "Unknown Beverage";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
~~~

~~~ java
public class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "Dark Roast Coffee";
	}
 
	public double cost() {
		return .99;
	}
}

public class Decaf extends Beverage {
	public Decaf() {
		description = "Decaf Coffee";
	}
 
	public double cost() {
		return 1.05;
	}
}
public class Espresso extends Beverage {
  
	public Espresso() {
		description = "Espresso";
	}
  
	public double cost() {
		return 1.99;
	}
}
~~~

~~~java
public abstract class CondimentDecorator extends Beverage {
	public abstract String getDescription();
}
~~~
~~~java
public class Milk extends CondimentDecorator {
	Beverage beverage;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	public double cost() {
		return 0.10 + beverage.cost();
	}
}
public class Mocha extends CondimentDecorator {
	Beverage beverage;
 
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}
 
	public double cost() {
		double beverage_cost = beverage.cost(); 
		System.out.println("Mocha: beverage.cost() is: " + beverage_cost );		
		System.out.println("  - adding One Mocha cost of 0.20c ");
		System.out.println("  - new cost is: " + (0.20 + beverage_cost ) );
		
		return 0.20 + beverage_cost ;
	}
}
public class Soy extends CondimentDecorator {
	Beverage beverage;

	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	public double cost() {
		double beverage_cost = beverage.cost(); 
		System.out.println("Soy: beverage.cost() is: " + beverage_cost);		
		System.out.println("  - adding One Soy cost of 0.15c ");
		System.out.println("  - new cost is: " + (0.15 + beverage_cost ) );
		
		return 0.15 + beverage.cost();
	}
}
public class Whip extends CondimentDecorator {
	Beverage beverage;
 
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}
 
	public double cost() {
		double beverage_cost = beverage.cost(); 
		System.out.println("Whipe: beverage.cost() is: " + beverage_cost);		
		System.out.println("  - adding One Whip cost of 0.10c ");
		System.out.println("  - new cost is: " + (0.10 + beverage_cost ) );
		
		return 0.10 + beverage_cost ;
	}
}
~~~