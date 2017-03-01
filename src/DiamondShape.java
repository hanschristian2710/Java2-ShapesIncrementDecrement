import java.awt.Color;
import java.awt.Graphics;

public class DiamondShape extends AbstractShape implements Cloneable {

	public DiamondShape(int x, int y, Color c, double size) {
		super(x, y, c, size);
	}

	public void draw(Graphics g) {

		// x coordinate of the vertices stored in counter clockwise order
		int[] xArray = { this.xNorth, this.xWest, this.xSouth, this.xEast };
		// y coordinate of the vertices stored in counter clockwise order
		int[] yArray = { this.yNorth, this.yWest, this.ySouth, this.yEast };
		g.setColor(this.c);
		g.fillPolygon(xArray, yArray, 4);

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
			return new DiamondShape(x, changeDir, complementaryColor(),
					size / 2);
			// xWest
		} else if (dir == 1) {
			return new DiamondShape(changeDir, y, complementaryColor(),
					size / 2);
			// xEast
		} else if (dir == 2) {
			return new DiamondShape(changeDir, y, complementaryColor(),
					size / 2);
			// ySouth
		} else {
			return new DiamondShape(x, changeDir, complementaryColor(),
					size / 2);
		}
	}

	public Object clone() {
		DiamondShape copy = null;
		try {
			copy = (DiamondShape) super.clone();
			copy.c = new Color(c.getRed(), c.getGreen(), c.getBlue());
		} catch (CloneNotSupportedException e) {

		}
		return copy;
	}

	public Color complementaryColor() {
		return new Color(255 - c.getRed(), 255 - c.getGreen(),
				255 - c.getBlue());
	}

	// This is to determine which color to return, determined based
	// on the level
	public Color determineColor() {
		if (level % 2 == 0) {
			return c;
		} else {
			return complementaryColor();
		}
	}

	public String toString() {
		return "Shape type: " + this.getClass() + ", Center coordinate: (" + x
				+ " ," + y + ")" + ", Size: " + getCurrentSize(size) + ", Color: "
				+ determineColor() + ",Level: " + level;
	}

}
