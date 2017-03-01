import java.awt.Color;
import java.awt.Graphics;

public class PlusShape extends AbstractShape {

	public PlusShape(int x, int y, Color c, double size) {
		super(x, y, c, size);

	}

	public void draw(Graphics g) {
		// x coordinate of the horizontal axis of plus
		int[] xHorizontalArray = { this.xWest, this.xEast, this.xEast,
				this.xWest };
		// x coordinate of the vertical axis of the plus
		int[] xVerticalArray = { this.xNorth - 1, this.xSouth - 1,
				this.xSouth + 1, this.xNorth + 1 };
		// y coordinate of the horizontal axis of plus
		int[] yHorizontalArray = { this.yWest - 1, this.yEast - 1,
				this.yEast + 1, this.yWest + 1 };
		// y coordinate of the vertical axis of plus
		int[] yVerticalArray = { this.yNorth, this.ySouth, this.ySouth,
				this.yNorth };

		g.setColor(this.c);
		g.fillPolygon(xHorizontalArray, yHorizontalArray, 4);
		g.fillPolygon(xVerticalArray, yVerticalArray, 4);

		if (this.level > 0) {
			if (this.north != null && this.north != this.parent) {
				this.north.draw(g);
			}
			if (this.west != null && this.west != this.parent) {
				this.west.draw(g);
			}
			if (this.east != null && this.east != this.parent) {
				this.east.draw(g);
			}
			if (this.south != null && this.south != this.parent) {
				this.south.draw(g);
			}
		}
	}

	public Shape createChildShape(int dir, int changeDir) {
		// counter clockwise order
		// yNorth
		if (dir == 0) {
			return new PlusShape(x, changeDir, c, size / 2);
			// xWest
		} else if (dir == 1) {
			return new PlusShape(changeDir, y, c, size / 2);
			// xEast
		} else if (dir == 2) {
			return new PlusShape(changeDir, y, c, size / 2);
			// ySouth
		} else {
			return new PlusShape(x, changeDir, c, size / 2);
		}

	}

	public Object clone() {
		PlusShape copy = null;
		try {
			copy = (PlusShape) super.clone();
			copy.c = new Color(this.c.getRed(), this.c.getGreen(),
					this.c.getBlue());
		} catch (CloneNotSupportedException e) {

		}
		return copy;
	}

	public String toString() {
		return "Shape type: " + this.getClass() + ", Center coordinate: (" + x
				+ " ," + y + ")" + ", Size: " + getCurrentSize(size)
				+ ", Color: " + c + ",Level: " + level;
	}

}
