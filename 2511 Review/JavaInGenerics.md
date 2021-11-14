# <center> Comp2511 Java in Generics </center>

Generics enable types (classes and interfaces) to be parameters when defining:
- classes,
- interfaces and
- methods.

## Benefits
- Removes casting and offers stronger type checks at compile time.
- Allows implementations of generic algorithms, that work on collections of different types, can be customized, and are type safe.
- Adds stability to your code by making more of your bugs detectable at compile time.

## Generic Types
- A generic type is a generic class or interface that is parameterized over types.
- A generic class is defined with the following format:
class name< T1, T2, ..., Tn > { /* ... */ }
- The most commonly used type parameter names are:
   - E - Element (used extensively by the Java Collections Framework)
   - K - Key
   - N - Number
   - T - Type
   - V - Value
   - S,U,V etc. - 2nd, 3rd, 4th types
- For example,
~~~java
Box<Integer> integerBox = new Box<Integer>();
OR
Box<Integer> integerBox = new Box<>();
~~~

## Multiple Type Parameters
A generic class can have multiple type parameters.

~~~java
    public interface Pair<K, V> {
        public K getKey();
        public V getValue();
    }
~~~

~~~java
    public class OrderedPair<K, V> implements Pair<K, V> {

        private K key;
        private V value;

        Public OrderedPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {return key;}
        public V getValue() {return value;}
    }
~~~

## Bounded Type Parameters
There may be times when you want to restrict the types that can be used as type arguments in a parameterized type.

A type parameter can have multiple bounds.

~~~java
public class MaximumTest {
   // determines the largest of three Comparable objects
   public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
      T max = x;   // assume x is initially the largest
      if(y.compareTo(max) > 0) {
         max = y;   // y is the largest so far
      }
      if(z.compareTo(max) > 0) {
         max = z;   // z is the largest now                 
      }
      return max;   // returns the largest object   
   }
   public static void main(String args[]) {
      System.out.printf("Max of %d, %d and %d is %d\n\n", 
         3, 4, 5, maximum( 3, 4, 5 ));
      System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
         6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ));
      System.out.printf("Max of %s, %s and %s is %s\n","pear",
         "apple", "orange", maximum("pear", "apple", "orange"));
   }
}
~~~

## Wildcards: Upper bounded
- In generic code, the question mark (?), called the wildcard, represents an unknown type.
- The wildcard can be used in a variety of situations: as the type of a parameter, field, or local variable; sometimes as a return type.
- The upper bounded wildcard, < ? extends Foo >, where Foo is any type, matches Foo and any subtype of Foo .
- You can specify an upper bound for a wildcard, or you can specify a lower bound, but you cannot specify both.

The unbounded wildcard type is specified using the wildcard character (?), for example, List< ? >. This is called a list of unknown type.

## Wildcards: Lower Bounded
- An upper bounded wildcard restricts the unknown type to be a specific type or a subtype of that type and is represented using the extends keyword.
- A lower bounded wildcard is expressed using the wildcard character ('?'), following by the super keyword, followed by its lower bound: < ? super A >.
- To write the method that works on lists of Integer and the super types of Integer, such as Integer, Number, and Object, you would specify List<? Super Integer>.
- The term List<Integer> is more restrictive than List<? super Integer>.