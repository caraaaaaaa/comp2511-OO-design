package programmingexample5;

import java.awt.Color;

public class ShapeColourAreaVisitor implements ShapeVisitor {

    /*
     *
     * TODO In this class, you need to implement the required method(s), to answer
     * the question.
     *
     * You also need to implement the following constructor and declare variables,
     * if required.
     *
     */
    private double area;
    private Color colour;

    public ShapeColourAreaVisitor(Color colour) {
        // initialise values
        area = 0;
        this.colour = colour;
    }

    public double getTotalArea() {
        // TODO Implement this method as well
        return area;
    }

    @Override
    public void visit(Circle circle) {
        // If colour matches, add area to total
        if (circle.getColour().equals(colour)) {
            area += circle.area();
        }

    }

    @Override
    public void visit(Rectangle rectangle) {
        // If colour matches, add area to total
        if (rectangle.getColour().equals(colour)) {
            area += rectangle.area();
        }

    }

    @Override
    public void visit(Triangle triangle) {
        // If colour matches, add area to total
        if (triangle.getColour().equals(colour)) {
            area += triangle.area();
        }

    }

    @Override
    public void visit(ShapeGroup group) {
        // Do nothing for a shapegroup since its sub components will be visited

    }

}
