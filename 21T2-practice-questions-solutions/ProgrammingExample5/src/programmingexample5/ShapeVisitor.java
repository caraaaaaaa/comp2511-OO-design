package programmingexample5;

public interface ShapeVisitor {

	/*
	 * TODO Here, you need to add the required method signatures(s),
	 * to answer the question.
	 *
	 */

	 void visit(Circle circle);

	 void visit(Rectangle rectangle);

	 void visit(Triangle triangle);

	 void visit(ShapeGroup group);

}
