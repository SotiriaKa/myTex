package MainPack.Command;


import MainPack.VersionsManager.VersionsManager;
import MainPack.test_pack.Test_All;

public class RollBackToPreviousVersionCommand implements Command {
	
	private VersionsManager versionsManager;
	String viewContain;
	private MyTexEditorController controller;

	public RollBackToPreviousVersionCommand(MyTexEditorController controller) {
		this.controller = controller;
		try{versionsManager = controller.getVersionsManager();}catch(Exception e) {}
	}
	
	@Override
	public void execute() {
		try {
			viewContain = controller.getMyTexEditorView().getTextFromView();
		}catch(Exception e) {viewContain = Test_All.getTextToviewFromTest();}
		versionsManager.rollbackToPreviousVersion(viewContain); 
	}
	
	

}
