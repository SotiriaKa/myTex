package MainPack.VersionsManager;

import MainPack.Command.MyTexEditorController;

public class VersionsStrategyFactory {
	
	private MyTexEditorController controller;

	public VersionsStrategyFactory(MyTexEditorController controller) {
		this.controller = controller;
	}
	
	public VersionsStrategy createStrategy(String strategy_version) {
		if(strategy_version == "Stable") {
			return new StableVersionsStrategy(controller) ;
		}else {
			return new VolatileVersionsStrategy(); 
		}
	}
	
}
