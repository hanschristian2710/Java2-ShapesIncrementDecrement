import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class is to handle the boundary of the mouse click
 */
public class ClickHandler implements MouseListener {
	private DrawingModel model;

	public ClickHandler(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Shape shape : model.getShapes()) {
			// get the boundary from the rectangle boundary set
			if (e.getPoint().x >= shape.getBoxBoundary().getMinX()
					&& e.getPoint().x <= shape.getBoxBoundary().getMaxX()
					&& e.getPoint().y >= shape.getBoxBoundary().getMinY()
					&& e.getPoint().y <= shape.getBoxBoundary().getMaxY()) {
				if (shape.changeLevel()) {
					// if the changes happen to the shape is success
					// we will notify
					this.model.notifyViewers();
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

}
