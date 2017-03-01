/**
 * This class should implements DrawingView and it will print in the terminal
 * window the information about the shapes as given by the toString method of a
 * shape.
 *
 */
public class TextViewer implements DrawingView {

	@Override
	public void notify(DrawingModel dModel) {
		for (Shape s : dModel.getShapes()) {
			System.out.println(s.toString());
		}
		System.out.println();
	}
}
