package MainPack.Command;

import MainPack.VersionsManager.VersionsManager;
import MainPack.VersionsManager.VersionsStrategy;
import MainPack.VersionsManager.VersionsStrategyFactory;

public class ChangeVersionsStrategyToVolatileCommand implements Command {
	
	private VersionsStrategy strategy;
	private VersionsStrategyFactory versionsStrategyFactory;
	private VersionsManager versionsManager;
	private MyTexEditorController controller;
	
	public ChangeVersionsStrategyToVolatileCommand(MyTexEditorController controller) { 
		this.controller = controller;
		versionsStrategyFactory = controller.getVersionsStrategyFactory();
		versionsManager = controller.getVersionsManager();
		
		strategy = versionsStrategyFactory.createStrategy("Volatile");
	} 

	@Override
	public void execute() {
		versionsManager.setStrategy(strategy);
		controller.setStrategyType("Volatile");
	}
	
	

}
