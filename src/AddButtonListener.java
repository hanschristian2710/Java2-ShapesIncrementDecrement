import java.awt.event.ActionEvent;

public class AddButtonListener extends ButtonListener {
	public AddButtonListener(DrawingModel model) {
		super(model);
	}

	public void actionPerformed(ActionEvent event) {
		// if the add radio button chosen the method will call the 
		// addlevel method in model
		this.model.addLevel();
	}

}
