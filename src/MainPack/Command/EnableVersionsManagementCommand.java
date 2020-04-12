package MainPack.Command;

import MainPack.VersionsManager.VersionsManager;

public class EnableVersionsManagementCommand implements Command {

	private VersionsManager versionsManager;

	public EnableVersionsManagementCommand(MyTexEditorController controller) {
		versionsManager = controller.getVersionsManager();
	}
	
	@Override
	public void execute() {
		versionsManager.enable();
	}
}
