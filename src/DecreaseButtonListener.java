import java.awt.event.ActionEvent;

public class DecreaseButtonListener extends ButtonListener {
	public DecreaseButtonListener(DrawingModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// will call the decrease level method in DrawingModel class
		this.model.decreaseLevel();

	}
}
