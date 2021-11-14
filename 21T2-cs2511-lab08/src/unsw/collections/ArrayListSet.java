package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListSet<E> implements Set<E> {
    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }
    

    @Override
    public void add(E e) {
        // TODO Auto-generated method stub
        boolean flag = false;
        for (E ele: this.elements) {
            if (ele.equals(e)) {
                flag = true;
            }
        }

        if (flag == false) {
            this.elements.add(e);
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String a = "";
        for (E e: this.elements) {
            a = a + e.toString();
        }
        return a;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return this.elements.iterator();
    }

    @Override
    public void remove(E e) {
        // TODO Auto-generated method stub
        this.elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        // TODO Auto-generated method stub
        for (Object a: this.elements) {
            if (e.equals(a)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        // TODO Auto-generated method stub
        for (E e: this.elements) {
            if (!other.contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        // TODO Auto-generated method stub
        ArrayListSet<E> newSet = new ArrayListSet<E>();

        for (E e: other) {
            newSet.add(e);
        }
        for (E e: this.elements) {
            if (!newSet.contains(e)) {
                newSet.add(e);
            }
        }
        return newSet;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
        // TODO Auto-generated method stub
        ArrayListSet<E> newSet = new ArrayListSet<E>();
        for (E e: this.elements) {
            if (other.contains(e)) {
                newSet.add(e);
            }
        }
        return newSet;
    }

    @Override
    public boolean equals(Object other) {
        ArrayListSet<E> newSet = (ArrayListSet<E>) other;

        if(newSet.subsetOf(this)&&this.subsetOf(newSet)) {
        	return true;
        }
        
        return false;
    }
}
