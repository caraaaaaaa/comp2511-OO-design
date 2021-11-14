# <center> Composite Pattern </center>

In OO programming, a composite is an object designed as a composition of one-or-more similarbobjects (exhibiting similar functionality).

## Motivation and Intent
Aim is to be able to manipulate a single instance of the object just as we would manipulate a group of them. For example,
- operation to resize a group of Shapes should be same as resizing a single Shape.
- calculating size of a file should be same as a directory.

No discrimination between a Single (leaf) Vs a Composite (group) object.
- If we discriminate between a single object and a group of object, code will become more complex and therefore, more error prone.

## Implement Methods

- Define a unified Component interface for both Leaf (single / part) objects and Composite (Group / whole) objects.
- A Composite stores a collection of children components (either Leaf and/or Composite objects).
- Clients can **ignore the differences** between compositions of objects and individual objects, this greatly simplifies clients of complex hierarchies and makes them easier to implement, change, test, and reuse.

## Implementation Issue: Uniformity vs Type Safety
- Design for Uniformity
   - include all child-related operations in the Component interface, this means the Leaf class needs to implement these methods with “do nothing” or “throw exception”.
   - a client can treat both Leaf and Composite objects uniformly.
   - we loose type safety because Leaf and Composite types are not cleanly separated.
   - useful for dynamic structures where children types change dynamically (from Leaf to Composite and vice versa), and a client needs to perform child-related operations regularly.
   - For example, a document editor application.

~~~java
    public interface Component {
        
        public String nameString();
        public double calculateCost();
        
        public boolean add(Component child);
        public boolean remove(Component child);
        
    }
~~~

~~~java
    import java.util.ArrayList;

    public class Composite implements Component {

        private String name; 
        private double cost;
        
        ArrayList<Component>  children = new ArrayList<Component>();
        
        public Composite(String name, double cost) {
            super();
            this.name = name;
            this.cost = cost;
        }

        @Override
        public double calculateCost() {
            double answer = this.getCost();
            for(Component c : children) {
                answer += c.calculateCost();
            }
            
            return answer;
        }
        
        @Override
        public String nameString() {
            String answer = "[" + this.getName()  + " "; 
            for(Component c : children) {
                answer = answer + " " + c.nameString();
            }	
            answer = answer + "]";
            return answer;
        }


        @Override
        public boolean add(Component child) {
            children.add(child);
            return true;
        }

        @Override
        public boolean remove(Component child) {
            children.remove(child);
            return true;
        }
    }
~~~

~~~java
    public class Leaf implements Component {

        private String name; 
        private double cost;
        
        
        public Leaf(String name, double cost) {
            super();
            this.name = name;
            this.cost = cost;
        }

        @Override
        public String nameString() {
            return this.name;
        }

        @Override
        public double calculateCost() {
            return this.cost;
        }

        @Override
        public boolean add(Component child) {
            return false;
        }

        @Override
        public boolean remove(Component child) {
            return false;
        }
    }
~~~
- Design for Type Safety
   - only define child-related operations in the Composite class
   - the type system enforces type constraints, so a client cannot perform child-related operations on a Leaf object.
   - a client needs to treat Leaf and Composite objects differently.
   - useful for static structures where a client doesn’t need to perform child-related operations on “unknown” objects of type Component.

~~~java
    public interface Component {
        
        public String nameString();
        public double calculateCost();
        
    }
~~~

~~~java
    import java.util.ArrayList;

    public class Composite implements Component {

        private String name; 
        private double cost;
        
        ArrayList<Component>  children = new ArrayList<Component>();
        
        public Composite(String name, double cost) {
            super();
            this.name = name;
            this.cost = cost;
        }

        @Override
        public double calculateCost() {
            double answer = this.getCost();
            for(Component c : children) {
                answer += c.calculateCost();
            }
            
            return answer;
        }
        
        @Override
        public String nameString() {
            String answer = "[" + this.getName()  + " "; 
            for(Component c : children) {
                answer = answer + " " + c.nameString();
            }	
            answer = answer + "]";
            return answer;
        }

        public boolean add(Component child) {
            children.add(child);
            return true;
        }

        public boolean remove(Component child) {
            children.remove(child);
            return true;
        }
    }
~~~

~~~java
    public class Leaf implements Component {

        private String name; 
        private double cost;
        
        
        public Leaf(String name, double cost) {
            super();
            this.name = name;
            this.cost = cost;
        }

        @Override
        public String nameString() {
            return this.name;
        }

        @Override
        public double calculateCost() {
            return this.cost;
        }
    }
~~~

## Summary
- The Composite Pattern provides a structure to hold both individual objects and
composites.
- The Composite Pattern allows clients to treat composites and individual objects
uniformly.
- A Component is any object in a Composite structure. Components may be other
composites or leaf nodes.
- There are many design tradeoffs in implementing Composite. You need to balance
transparency/uniformity and type safety with your needs.