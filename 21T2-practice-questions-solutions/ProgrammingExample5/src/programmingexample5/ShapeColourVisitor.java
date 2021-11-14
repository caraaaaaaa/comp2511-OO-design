package programmingexample5;

import java.awt.Color;

public class ShapeColourVisitor implements ShapeVisitor {

    @Override
    public void visit(Circle circle) {
        // If a circle set colour to red
        circle.setColour(Color.RED);

    }

    @Override
    public void visit(Rectangle rectangle) {
        // If a rectangle set colour to green
        rectangle.setColour(Color.GREEN);

    }

    @Override
    public void visit(Triangle triangle) {
        // If a triangle set colour to blue
        triangle.setColour(Color.BLUE);

    }

    @Override
    public void visit(ShapeGroup group) {
        // Do nothing for a shapegroup since its sub components will be visited
    }

    /*
     *
     * TODO In this class, you need to implement the required method(s), to
     * answer the question.
     *
     */

}
