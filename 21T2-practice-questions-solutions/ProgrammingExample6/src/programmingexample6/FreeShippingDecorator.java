package programmingexample6;

public class FreeShippingDecorator extends Decorator {

    private int maxWeight;
    private double minPrice;

    public FreeShippingDecorator(Product product, double minPrice, int maxWeight) {
        super(product);
        this.maxWeight = maxWeight;
        this.minPrice = minPrice;
    }

    @Override
    public double getShippingCost() {
        if (getWeight() < maxWeight && getPrice() > minPrice)
            return 0;
        else
            return super.getShippingCost();
    }

}
