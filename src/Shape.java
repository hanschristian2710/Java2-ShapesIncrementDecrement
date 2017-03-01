import java.awt.Graphics;
import java.awt.Rectangle;

public interface Shape {

	public Rectangle getBoxBoundary();

	public void draw(Graphics g);

	public boolean addLevel();

	public boolean decreaseLevel();

	public boolean resetLevel();

	public Shape createChildShape(int dir, int changeDir);

	public void setParent(Shape s);

	public void setSouth(Shape s);

	public void setEast(Shape s);

	public void setWest(Shape s);

	public void setNorth(Shape s);

	public void setAdd();

	public void setDecrease();

	public boolean changeLevel();

}
