import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainClass {
	// final size of the window
	protected final static int HEIGHT = 500;
	protected final static int WIDTH = 500;

	public static void main(String[] args) {

		// creating JFrame
		JFrame frame = new JFrame("Increment Shape Program");
		Viewer view = new Viewer();
		TextViewer text = new TextViewer();
		frame.getContentPane().add(view).setBackground(Color.BLACK);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the drawing model (Diamond and Plus)
		DrawingModel model = new DrawingModel();
		model.add(new DiamondShape(WIDTH - 3 * WIDTH / 4, HEIGHT / 3,
				Color.BLUE, 115));
		model.add(new PlusShape(WIDTH - WIDTH / 4, HEIGHT / 3, Color.WHITE, 115));

		// Controllers (Buttons)

		// reset button
		JButton resetButton = new JButton("Reset");
		ResetButtonListener resetButtonListen = new ResetButtonListener(model);
		resetButton.addActionListener(resetButtonListen);

		// Add Level Button
		JRadioButton addButton = new JRadioButton("Add Level");
		AddButtonListener addButtonListen = new AddButtonListener(model);
		// the addLevel button has been selected initially
		addButton.setSelected(true);
		addButton.addActionListener(addButtonListen);

		// Decrease Level Button
		JRadioButton decreaseButton = new JRadioButton("Decrease Level");
		DecreaseButtonListener decreaseButtonListen = new DecreaseButtonListener(
				model);
		decreaseButton.addActionListener(decreaseButtonListen);

		// Button group (add all of the radio button)
		ButtonGroup group = new ButtonGroup();
		group.add(addButton);
		group.add(decreaseButton);

		// Mouse Click
		ClickHandler mouseClicks = new ClickHandler(model);
		frame.addMouseListener(mouseClicks);

		// draw all the buttons
		JPanel drawButton = new JPanel();
		frame.add(drawButton, BorderLayout.NORTH);
		drawButton.add(addButton);
		drawButton.add(decreaseButton);
		drawButton.add(resetButton);

		// View
		frame.validate();
		frame.setVisible(true);

		// model adds
		model.addView(view);
		model.addText(text);
		model.notifyViewers();

	}
}
