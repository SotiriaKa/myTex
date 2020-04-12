package MainPack.Command;

public class NewTempateCommand implements Command {
	
	MyTexEditorController controller;

	public NewTempateCommand(MyTexEditorController controller) {
		this.controller = controller;
	}

	@Override
	public void execute() {
		controller.setID(controller.newID());
		controller.enact("ShowInViewID"); 
	}

}
