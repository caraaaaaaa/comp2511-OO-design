# <center> Comp2511 Java Doc </center>

Javadoc (originally cased JavaDoc) is a **documentation generator** created by Sun Microsystems for the Java language (now owned by Oracle Corporation) for generating API documentation in HTML format from Java source code. The HTML format is used for adding the convenience of being able to hyperlink related documents together.

The "doc comments" format used by Javadoc is the de facto industry standard for documenting Java classes. Some IDEs, like IntelliJ IDEA, NetBeans and Eclipse, automatically generate Javadoc HTML. Many file editors assist the user in producing Javadoc source and use the Javadoc info as internal references for the programmer.

Javadoc also provides an API for creating doclets and taglets, which allows users to analyze the structure of a Java application. This is how JDiff can generate reports of what changed between two versions of an API.

Javadoc **does not affect performance** in Java as all comments are removed at compilation time. Writing comments and Javadoc is for better understanding the code and thus better maintaining it.

## Structure of a Javadoc comment
A Javadoc comment is set off from code by standard multi-line comment tags `/*` and `*/`. The opening tag (called begin-comment delimiter), has an extra asterisk, as in `/**`.

The first paragraph is a description of the method documented.
Following the description are a varying number of descriptive tags, signifying:
- The parameters of the method (@param)
- What the method returns (@return)
- Any exceptions the method may throw (@throws)
- Other less-common tags such as @see (a "see also" tag)

## **JavaDoc for whole doc**
### Description
~~~java
/**
 * Class {@code Object} is the root of the class hierarchy.
 * Every class has {@code Object} as a superclass. All objects,
 * including arrays, implement the methods of this class.
 */
public class Object {}
~~~

### @param
~~~java
/**
* @param <E> the type of elements in this list
*/
public interface List<E> extends Collection<E> {}
~~~

### @author

~~~java
// ???????????????
@author Rod Johnson

// ????????????????????????
@author Igor Hersht, igorh@ca.ibm.com

// ??????????????? ???????????????
@author <a href="mailto:ovidiu@cup.hp.com">Ovidiu Predescu</a>

// ???????????????
@author shane_curcuru@us.ibm.com

// ????????? ??????
@author Apache Software Foundation

// ????????????????????? ???????????????
@author <a href="https://jakarta.apache.org/turbine"> Apache Jakarta Turbine</a>
~~~

### @since ?????????????????????

~~~java
package java.util.stream;

/**
* @since 1.8
*/
public interface Stream<T> extends BaseStream<T, Stream<T>> {}
~~~

### @version ??????
~~~java
package com.sun.org.apache.xml.internal.resolver;
 /**
 * @version 1.0
 */
public class Resolver extends Catalog {}
~~~
&nbsp;
## **JavaDoc for method**

### @param ???????????????????????????????????????

~~~java
/**
* @param str the {@code CharSequence} to check (may be {@code null})
*/
public static boolean containsWhitespace(@Nullable CharSequence str) {}
~~~

### @return ?????????????????????

~~~java
/**
* @return {@code true} if the {@code String} is not {@code null}, its
*/
public static boolean hasText(@Nullable String str){}
~~~

### @throws ??????????????? ???????????? , ?????????????????????????????????????????????

~~~java
/**
* @throws IllegalArgumentException when the given source contains invalid encoded sequences
*/
public static String uriDecode(String source, Charset charset){}
~~~

### @exception ????????????????????????throws???????????????
~~~java
/**
* @exception IllegalArgumentException if <code>key</code> is null.
*/
public static Object get(String key) throws IllegalArgumentException {}
~~~

### @pre for pre-condition
### @post for post-condition

~~~java
/**
* @param value to calculate sqr root
* @return sqre - sqr root of value
* @pre value >= 0
* @post value = sqrt * sqrt
*/
public double SquareRoot(double value);
~~~

### @invarient for fixed value
~~~java
/**
 * @invarient age >= 0
 */
public class Student {
~~~