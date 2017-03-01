import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ButtonListener implements ActionListener {

	protected DrawingModel model;

	public ButtonListener(DrawingModel d) {
		this.model = d;
	}

	public abstract void actionPerformed(ActionEvent event);

}
