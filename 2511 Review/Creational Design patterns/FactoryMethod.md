# <center> Factory Method </center>

Factory Method is a creational design pattern that uses factory methods to deal with the problem of **creating objects** without having to specify the exact class of the object that will be created.

## Problem:
- creating an object directly within the class that requires (uses) the object is inflexible
- it commits the class to a particular object and
- makes it impossible to change the instantiation independently from (without having to change) the class.

## Possible Solution:
- Define a separate operation (factory method) for creating an object.
- Create an object by calling a factory method.
- This enables writing of subclasses to change the way an object is created (to redefine which class to instantiate).

## Structure
1. The Product declares the interface, which is common to all objects that can be produced by the creator and its subclasses.
2. Concrete Products are different implementations of the product interface.
3. The Creator class declares the factory method that returns new product objects.
4. Concrete Creators override the base factory method so it returns a different type of product.

~~~java
public interface Button {
	
	public void setLabel(String label);
	public String getLabel();

	public void setBgColour(String colour);
	public String getBgColour();
	
	public void click();

}
~~~
~~~java
public class ButtonFactory {

	public static Button getButton() {
		
		String platform = System.getProperty("os.name");

		return getButton(platform);
	}

	public static Button getButton(String platform) {
		Button btn = null;

		if (platform.equalsIgnoreCase("Html")) {
			btn = new ButtonHtml();
		} else if (platform.equalsIgnoreCase("Windows 10")) {  // this may be different! 
			btn = new ButtonWin10();
		} else if (platform.equalsIgnoreCase("MacOs")) {  // this may be different! 
			btn = new ButtonMacOs();
		} else if (platform.equalsIgnoreCase("Linux")) {
			btn = new ButtonLinux();
		} else {
			new Exception("Unknwon platform type!");
		}

		return btn;
	}

}
~~~
~~~java
public class ButtonHtml implements Button {
	private String label = "";
	private String colour = "";

	
	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void setBgColour(String colour) {
		this.colour = colour;	
	}

	@Override
	public String getBgColour() {
		return this.colour;
	}
	
	@Override
	public void click() {
		System.out.println("HTML Button is Clicked !! ");
	}

	@Override
	public String toString() {
		return "HTML Button, lable: " + getLabel() ;
	}
	
}
~~~