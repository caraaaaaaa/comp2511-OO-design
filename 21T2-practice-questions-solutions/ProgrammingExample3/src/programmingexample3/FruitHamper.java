package programmingexample3;

import java.util.Iterator;

public class FruitHamper extends ArrayListItemHamper<Fruit> {
    /**
     * invariant: FruitHamper must have at least 2 apples and 2 avocados when have >= 6 fruits. Otherwise, adding an item should do nothing
     * fruit hamper has price surcharge of 25%, rounded up to nearest integer
     */

    @Override
    public int getPrice(){
        // TODO implement this
        return (int) Math.ceil(super.getPrice() * 1.25);
    }

    @Override
    public void add(Fruit e, int n){
        // TODO implement this
        // Find number of what each thing will be after adding the item
        int numFruit = 0;
        int numApple = 0;
        int numAvocado = 0;
        Iterator<Count<Fruit>> iter = iterator();
        while(iter.hasNext()) {
            Count<Fruit> c = iter.next();
            Fruit fruit = c.getElement();
            int num = c.getCount();
            if (fruit instanceof Apple) {
                numApple += num;
            }
            if (fruit instanceof Avocado) {
                numAvocado += num;
            }
            numFruit += num;
        }
        if (e instanceof Apple) {
            numApple += n;
        }
        if (e instanceof Avocado) {
            numAvocado += n;
        }
        numFruit += n;

        // Check if the item can be added
        if (numFruit < 6 || (numAvocado >= 2 && numApple >= 2)) {
            super.add(e, n);
        }

    }
}
