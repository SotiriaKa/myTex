package MainPack.Command;

import MainPack.VersionsManager.VersionsManager;
import MainPack.VersionsManager.VersionsStrategy;
import MainPack.VersionsManager.VersionsStrategyFactory;

public class ChangeVersionsStrategyToStableCommand implements Command{
	
	private VersionsStrategy strategy;
	private VersionsStrategyFactory versionsStrategyFactory;
	private VersionsManager versionsManager;
	private MyTexEditorController controller;
	
	public ChangeVersionsStrategyToStableCommand(MyTexEditorController controller) {
		this.controller = controller;
		versionsStrategyFactory = controller.getVersionsStrategyFactory();
		versionsManager = controller.getVersionsManager();
		
		strategy = versionsStrategyFactory.createStrategy("Stable");
	}

	@Override
	public void execute() {
		versionsManager.setStrategy(strategy);
		controller.setStrategyType("Stable");
	}
	
	

}
