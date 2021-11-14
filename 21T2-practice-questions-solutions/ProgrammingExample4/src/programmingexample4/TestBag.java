package programmingexample4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestBag {

    @Test
    public void simpleTest() {
        Bag<Fruit> fruitBag = new ArrayListBag<Fruit>();
        fruitBag.add(new Apple("Gala"));
        fruitBag.add(new Apple("Fuji"));
        fruitBag.add(new Orange("Navel"));

        assertEquals(1, fruitBag.count(new Apple("Gala")));
        assertEquals(1, fruitBag.count(new Apple("Fuji")));
        assertEquals(1, fruitBag.count(new Orange("Navel")));


        // The same element again
        fruitBag.add(new Apple("Gala"));
        assertEquals(2, fruitBag.count(new Apple("Gala")));
        assertEquals(1, fruitBag.count(new Apple("Fuji")));
        assertEquals(1, fruitBag.count(new Orange("Navel")));
    }

    @Test
    public void removeTest() {
        Bag<Fruit> fruitBag = new ArrayListBag<Fruit>();
        fruitBag.add(new Apple("Gala"));
        fruitBag.add(new Apple("Fuji"), 2);
        fruitBag.add(new Orange("Navel"), 3);

        fruitBag.remove(new Orange("Navel"), 2);
        assertEquals(1, fruitBag.count(new Apple("Gala")));
        assertEquals(2, fruitBag.count(new Apple("Fuji")));
        assertEquals(1, fruitBag.count(new Orange("Navel")));

        fruitBag.remove(new Apple("Fuji"), 2);
        assertEquals(1, fruitBag.count(new Apple("Gala")));
        assertEquals(0, fruitBag.count(new Apple("Fuji")));
        assertEquals(1, fruitBag.count(new Orange("Navel")));

        fruitBag.remove(new Apple("Gala"), 3);
        assertEquals(0, fruitBag.count(new Apple("Gala")));
        assertEquals(0, fruitBag.count(new Apple("Fuji")));
        assertEquals(1, fruitBag.count(new Orange("Navel")));

        // Check the invariant hasn't been broken
        for (Count<Fruit> c : fruitBag)
            assertTrue(c.getCount() > 0);
    }

    @Test
    public void sizeTest() {
        Bag<Fruit> fruitBag = new ArrayListBag<Fruit>();
        fruitBag.add(new Apple("Gala"));
        fruitBag.add(new Apple("Fuji"), 2);
        fruitBag.add(new Orange("Navel"), 3);

        assertEquals(6, fruitBag.size());
    }

    @Test
    public void sumTest() {
        Bag<Apple> b = new ArrayListBag<Apple>();
        Apple a1 = new Apple("Gala");
        Apple a2 = new Apple("Fuji");
        Apple a3 = new Apple("Granny Smith");
        b.add(a1, 2);
        b.add(a2, 3);
        b.add(a3);

        Bag<Apple> b2 = new ArrayListBag<Apple>();
        b2.add(a2);
        b2.add(a1);

        Bag<Apple> b3 = b.sum(b2);

        assertEquals(3, b3.count(a1));
        assertEquals(4, b3.count(a2));
        assertEquals(1, b3.count(a3));

        int counter = 0;
        for (Count<Apple> c : b3) {
            if (c.getElement().equals(a1))
                assertEquals(3, c.getCount());
            else if (c.getElement().equals(a2))
                assertEquals(4, c.getCount());
            else if (c.getElement().equals(a3))
                assertEquals(1, c.getCount());
            counter++;
        }

        assertEquals(3, counter);
    }

    @Test
    public void simpleEqualityTest() {
        Bag<Apple> b = new ArrayListBag<Apple>();
        Apple a1 = new Apple("Gala");
        Apple a2 = new Apple("Fuji");
        Apple a3 = new Apple("Granny Smith");
        b.add(a1, 2);
        b.add(a2, 3);
        b.add(a3);

        assertTrue(b.equals(b));

        Bag<Apple> b2 = new ArrayListBag<Apple>();
        b2.add(a3);
        b2.add(a1, 2);
        b2.add(a2, 3);

        assertTrue(b.equals(b2));
        assertTrue(b2.equals(b));

        b2.add(a1);

        assertFalse(b.equals(b2));
        assertFalse(b2.equals(b));

        Bag<Apple> b3 = new ArrayListBag<Apple>();
        b3.add(a3);
        b3.add(a1, 2);
        b3.add(a2, 3);
        b3.add(new Apple("Honey Crisp"));

        assertFalse(b.equals(b3));
        assertFalse(b3.equals(b));
    }

}
