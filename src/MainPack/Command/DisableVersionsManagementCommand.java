package MainPack.Command;

import MainPack.VersionsManager.VersionsManager;

public class DisableVersionsManagementCommand implements Command {
	
	private VersionsManager versionsManager;

	public DisableVersionsManagementCommand(MyTexEditorController controller) {
		versionsManager = controller.getVersionsManager();
	}
	
	@Override
	public void execute() {
		versionsManager.disable();
	}
}
