import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

/**
 * This should be an abstract class that implements interface Shape. Add
 * instance variables, additional methods and default implementations here once
 * it becomes clear that all Shapes need to have such fields.
 * 
 * @author EvanLie, HansChristian
 *
 */
public abstract class AbstractShape implements Shape {

	// shapes that show where to add the new smaller shapes
	protected Shape west, east, north, south, parent;
	// The middle point of the shape
	protected int x, y;

	// Vertices of the shape
	protected int xNorth, xEast, xSouth, xWest;
	protected int yNorth, yEast, ySouth, yWest;

	// The size of the shape given
	protected double size;

	// color of the shape
	protected Color c;

	// keeping track of the level reached by the shape projected
	protected int level;

	final int WIDTH = 1, HEIGHT = 1;

	// boolean to know whether to add or decrease level
	protected boolean add = true;

	public AbstractShape(int x, int y, Color color, double size) {
		this.x = x;
		this.y = y;
		this.c = color;
		this.size = size;

		this.xNorth = this.xSouth = x;
		this.xWest = (int) (x - size * WIDTH / 2);
		this.xEast = (int) (x + size * WIDTH / 2);
		this.yNorth = (int) (y - size * HEIGHT / 2);
		this.ySouth = (int) (y + size * HEIGHT / 2);
		this.yWest = this.yEast = y;
	}

	// Adding Level to the shape drawn in the window
	public boolean addLevel() {
		// when the size of the shape is too small the draw,
		// it will throw recursion
		if (size < 1) {
			// throw exception
			throw new ErrorLevelException();
		} else {
			if (level == 0) {
				if (north == null) {
					// create a north shape
					north = createChildShape(0, yNorth);
					north.setParent(this);
					north.setSouth(this);
				}

				if (south == null) {
					// create a north shape
					south = createChildShape(3, this.ySouth);
					south.setParent(this);
					south.setNorth(this);
				}
				if (east == null) {
					// create a north shape
					east = createChildShape(2, this.xEast);
					east.setParent(this);
					east.setWest(this);
				}

				if (west == null) {
					// create a north shape
					west = createChildShape(1, this.xWest);
					west.setParent(this);
					west.setEast(this);
				}

			} else {
				if (north != parent) {
					north.addLevel();
				}
				if (south != parent) {
					south.addLevel();
				}

				if (east != parent) {
					east.addLevel();
				}

				if (west != parent) {
					west.addLevel();
				}
			}
			// count level when added
			level++;
			return true;
		}
	}

	// remove level
	public boolean decreaseLevel() {

		// when the decrease level has reached 0,
		// it will throw the exception and will be catch by
		// the try catch in change level
		if (level == 0) {
			throw new ErrorLevelException();
		} else {
			if (level == 1) {
				north = south = east = west = null;
			} else {
				if (north != null && north != parent) {
					north.decreaseLevel();
				}
				if (south != null && south != parent) {
					south.decreaseLevel();
				}
				if (east != null && east != parent) {
					east.decreaseLevel();
				}
				if (west != null && west != parent) {
					west.decreaseLevel();
				}
			}
			// update level count when remove
			level--;
			return true;
		}
	}

	// reset the level
	public boolean resetLevel() {
		if (level != 0) {
			north = south = east = west = null;
			// set back the level to 0 and return true
			level = 0;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * if the boolean add == true the changeLevel method add shape will draw new
	 * shapes.
	 */
	public boolean changeLevel() {
		boolean b = false;
		// when add is true (in this case is adding the shapes's level)
		if (this.add) {
			try {
				b = addLevel();
			} catch (ErrorLevelException e) {
				JOptionPane.showMessageDialog(null,
						"You have reached the maximum level.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
			// when add is false (in this case is decreasing the level of the
			// shapes)
		} else {
			try {
				b = decreaseLevel();
			} catch (ErrorLevelException e) {
				JOptionPane.showMessageDialog(null,
						"You have reached the minimum level.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		return b;

	}

	// The boundary of the mouse click has twice the size of the shape
	public Rectangle getBoxBoundary() {
		return new Rectangle(x - (int) size, y - (int) size, 2 * (int) size,
				2 * (int) size);
	}

	public int getCurrentSize(double size) {
		int sizeFirst = (int) size;
		if (level == 0) {
			return sizeFirst;
		} else {
			// Count the level backward, so that recursion can get
			// the previous size of the shapes and added it to the
			// current size
			level--;
			int currentSize = sizeFirst + getCurrentSize(size / 2);
			// then we add the level again, which is the current real level
			// of the shapes
			level++;
			return currentSize;
		}
	}

	public void setAdd() {
		// if the add is triggered,
		// add becomes true
		this.add = true;
	}

	public void setDecrease() {
		// add is false when decreaseLevel
		this.add = false;
	}

	public void setParent(Shape s) {
		this.parent = s;
	}

	public void setNorth(Shape s) {
		this.north = s;
	}

	public void setEast(Shape s) {
		this.east = s;
	}

	public void setSouth(Shape s) {
		this.south = s;
	}

	public void setWest(Shape s) {
		this.west = s;
	}
}