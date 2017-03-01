import java.util.ArrayList;
import java.util.List;

/**
 * This class will store all the shapes that are going to be displayed and also
 * the viewers. if there is a change about to be made the model will return a
 * deep copy as requested by the client.
 *
 */
public class DrawingModel {

	// ArrayList for shapes
	private ArrayList<Shape> shapesList = new ArrayList<Shape>();
	// ArrayList for the views
	private ArrayList<DrawingView> views = new ArrayList<DrawingView>();
	// ArrayList for the texts
	private ArrayList<TextViewer> textViews = new ArrayList<TextViewer>();

	public DrawingModel() {
	}

	// add new shapes to the model
	public void add(Shape shapes) {
		shapesList.add(shapes);
	}

	public void addView(Viewer view) {
		views.add(view);
	}

	public void addText(TextViewer viewer) {
		textViews.add(viewer);
	}

	public List<Shape> getShapes() {
		return new ArrayList<Shape>(this.shapesList);
	}

	// to notify viewers when something in the model changes
	public void notifyViewers() {
		for (DrawingView view : views) {
			view.notify(this);
		}

		for (DrawingView textViewer : textViews) {
			textViewer.notify(this);
		}
	}

	public void addLevel() {
		for (Shape s : getShapes()) {
			s.setAdd();
		}
	}

	public void decreaseLevel() {
		for (Shape s : getShapes()) {
			s.setDecrease();
		}
	}

	public boolean resetLevel() {
		boolean b1 = false;
		boolean b2 = false;
		for (Shape shape : getShapes()) {
			b1 = shape.resetLevel();
			if (b1) {
				b2 = true;
			}
		}
		return b2;
	}

}
