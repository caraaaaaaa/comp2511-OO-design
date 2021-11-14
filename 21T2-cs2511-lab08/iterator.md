# Iterator Questions

a) Do you think making the Graph Iterable makes semantic sense? Discuss briefly, and think of both sides.
    Making the Graph Iterable can the code relationships much simpler but it's only convience for BFS because the default algorithm for graphs is BFS, no need to write 'BFSIterator'
    But if graph saves node by the original way, it can use whatever algorithm by writing different iterator.

b) Is a Graph an iterator or an iterable in this case?
    iterator.

c) What would the .iterator method return in this case?

```java
public Iterator<E> iterator() {
    // your code here
}
```
    It would return an Iterator with nodes in it.

d) What is the problem with this approach?