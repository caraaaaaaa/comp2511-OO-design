# <center> Template Pattern </center>

## Template Pattern: Motivation and Intent
- "Define the skeleton of an algorithm in an operation, deferring some steps to subclasses.
Template Method lets subclasses redefine certain steps of an algorithm
without changing the algorithm's structure." 
- A template Method defines the skeleton (structure) of a behavior (by implementing the
invariant parts).
- A template Method calls primitive operations, that could be implemented by sub classes
OR has default implementations in an abstract super class.
- Subclasses can redefine only certain parts of a behavior without changing the other parts
or the structure of the behavior.
- Subclasses do not control the behavior of a parent class,
a parent class calls the operations of a subclass and not the other way around.
- Inversion of control:
   - when using a library (reusable classes), we call the code we want to reuse.
   - when using a framework (like Template Pattern), we write subclasses and
implement the variant code the framework calls.
- Template pattern implement the common (invariant) parts of a behavior once "and
leave it up to subclasses to implement the behavior that can vary."
- Invariant behavior is in one class (localized)

## Structure
- Abstract class defines a templateMethod() to implement an invariant structure (behaviour)
- templateMethod() calls methods defined in the abstract class (abstract or concrete) - like primitive1, primitive2, etc.
- Default behaviour can be implemented in the abstract class by offering concrete methods
- Importantly, sub classes can implement primitive methods for variant behaviour
- "To reuse an abstract class effectively, subclass writers must understand which operations are designed for overriding."
- Primitive operations : operations that have default implementations or must be implemented by sub classes.
- Final operations: concrete operations that cannot be overridden by sub classes.
- Hook operations: concrete operations that do nothing by default and can be redefined by subclasses if necessary. This gives subclasses the ability to “hook into” the algorithm at various points, if they wish; a subclass is also free to ignore the hook.

~~~java
public abstract class CaffeineBeverageWithHook {
 
	void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {
			addCondiments();
		}
	}
 
	abstract void brew();
 
	abstract void addCondiments();
 
	void boilWater() {
		System.out.println("Boiling water");
	}
 
	void pourInCup() {
		System.out.println("Pouring into cup");
	}
 
	boolean customerWantsCondiments() {
		return true;
	}
}
~~~

~~~java
import java.io.*;

public class CoffeeWithHook extends CaffeineBeverageWithHook {
 
 	@Override
	public void brew() {
		System.out.println("Dripping Coffee through filter");
	}
 
 	@Override
	public void addCondiments() {
		System.out.println("Adding Sugar and Milk");
	}
 
 	@Override
	public boolean customerWantsCondiments() {

		String answer = getUserInput();

		if (answer.toLowerCase().startsWith("y")) {
			return true;
		} else {
			return false;
		}
	}
 
	private String getUserInput() {
		String answer = null;

		System.out.print("Would you like milk and sugar with your coffee (y/n)? ");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			answer = in.readLine();
		} catch (IOException ioe) {
			System.err.println("IO error trying to read your answer");
		}
		if (answer == null) {
			return "no";
		}
		return answer;
	}
}
~~~

~~~java
public class TeaWithHook extends CaffeineBeverageWithHook {
 
 	@Override
	public void brew() {
		System.out.println("Steeping the tea");
	}
 
 	@Override
	public void addCondiments() {
		System.out.println("Adding Lemon");
	}
 
 	@Override
	public boolean customerWantsCondiments() {

		String answer = getUserInput();

		if (answer.toLowerCase().startsWith("y")) {
			return true;
		} else {
			return false;
		}
	}
 
	private String getUserInput() {
		// get the user's response
		String answer = null;

		System.out.print("Would you like lemon with your tea (y/n)? ");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			answer = in.readLine();
		} catch (IOException ioe) {
			System.err.println("IO error trying to read your answer");
		}
		if (answer == null) {
			return "no";
		}
		return answer;
	}
}
~~~