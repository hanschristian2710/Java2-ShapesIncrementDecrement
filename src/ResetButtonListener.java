import java.awt.event.ActionEvent;

public class ResetButtonListener extends ButtonListener {
	public ResetButtonListener(DrawingModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// will call the resetLevel method
		if (this.model.resetLevel()) {
			this.model.notifyViewers();
		}
	}

}
