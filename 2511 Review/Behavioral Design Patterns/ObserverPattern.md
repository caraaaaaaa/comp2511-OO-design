# <center> Observer Pattern </center>

The Observer Pattern is used to implement distributed event handling systems, in
"event driven" programming.

In the observer pattern

- **an object**, called the subject (or observable or publisher) , maintains a list of its dependents, called observers (or subscribers), and
- **notifies the observers automatically** of any state changes in the subject, usually by calling one of their methods.

## Aim
- define a **one-to-many dependency** between objects without making the objects
tightly coupled.
- **automatically notify/update** an open-ended number of observers (dependent
objects) when the subject changes state
- be able to dynamically add and remove observers

## Passing data: Push or Pull
The Subject needs to pass (change) data while notifying a change to an Observer. Two possible options,
- **`Push data`**
   - Subject passes the changed data to its observers, for example: update(data1,data2,…)
   - All observers must implement the above update method.
- **`Pull data`**
   - Subject passes reference to itself to its observers, and the observers need
to get (pull) the required data from the subject, for example: update(this)
   - Subject needs to provide the required access methods for its observers. For example, public double getTemperature() ;

## Advantages
- Avoids tight coupling between Subject and its Observers.
- This allows the Subject and its Observers to be at different levels of abstractions in a system.
- Loosely coupled objects are easier to maintain and reuse.
- Allows dynamic registration and deregistration.

## Be careful
- A change in the subject may result in a chain of updates to its observers and in
turn their dependent objects – resulting in a complex update behaviour.
- Need to properly manage such dependencies.

~~~java
    public interface Subject {

        public void attach(Observer o);
        public void detach(Observer o);
        public void notifyObservers();
        
    }
~~~

~~~java
    public interface Observer {
        
        public void update(Subject obj);
        
    }
~~~

~~~java
    import java.util.ArrayList;

    public class Thermometer implements Subject {
        
        ArrayList<Observer> listObservers = new ArrayList<Observer>();
        double temperatureC = 0.0;
        
        @Override
        public void attach(Observer o) {
            if(! listObservers.contains(o)) { listObservers.add(o); }
        }

        @Override
        public void detach(Observer o) {
            listObservers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for( Observer obs : listObservers) {
                obs.update(this);
            }
        }

        public double getTemperatureC() {
            return temperatureC;
        }

        public void setTemperatureC(double temperatureC) {
            this.temperatureC = temperatureC;
            notifyObservers();
        }
    }
~~~

~~~java
    public class DisplayAustralia implements Observer {
        Subject subject;
        double temperatureC = 0.0;
        
        
        @Override
        public void update(Subject obj) {
            if(obj instanceof Thermometer) {
                this.temperatureC = ((Thermometer) obj).getTemperatureC();
                display();
            }	
        }

        public void display() {
            System.out.printf("From DisplayAus: Temperature is %.2f C\n" ， temperatureC);
        }
        
    }
~~~