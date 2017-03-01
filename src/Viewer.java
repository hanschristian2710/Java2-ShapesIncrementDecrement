import java.awt.Graphics;

import javax.swing.JPanel;

public class Viewer extends JPanel implements DrawingView {

	// instance variables
	private DrawingModel model = null; // initialize to null until it is
										// notified

	public void notify(DrawingModel d) {
		this.model = d;
		this.repaint();
	}

	/**
	 * override PaintComponent to draw all the shape stored in the arrayList
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (model == null) {
			return;
		} else {
			for (Shape s : model.getShapes()) {
				s.draw(g);
			}
		}
	}
}
