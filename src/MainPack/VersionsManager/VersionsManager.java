package MainPack.VersionsManager;

import MainPack.Command.MyTexEditorController;
import MainPack.Document.Document;
import MainPack.test_pack.Test_All;
import MainPack.view.MyTexEditorView;

public class VersionsManager {
	
	private boolean enable = true;
	private VersionsStrategy strategy;
	private MyTexEditorView myTexEditorView;
	MyTexEditorController controller;
	
	public VersionsManager(VersionsStrategy strategy, MyTexEditorController controller) {
		this.controller = controller;
		this.strategy = strategy;
		myTexEditorView = controller.getMyTexEditorView();
	}
	
	public boolean isEnable() {
		return enable;
	}
	
	public void enable() {
		enable = true;
	}
	
	public void disable() {
		enable = false;
	}
	
	public void setStrategy(VersionsStrategy new_strategy) {
		new_strategy.setEntireHistory(this.strategy.getEntireHistory()); 
		this.strategy = new_strategy; 
	}
	
	public void setCurrentVersion(Document document) {
		if(isEnable()) {
			strategy.putVersion(document);
		}
	}
	
	public Document getPreviousVersion(String viewContain) {
		return strategy.getVersion(viewContain); 
	}
	
	public void rollbackToPreviousVersion(String viewContain) {	
		if(isEnable()) {
			Document doc = getPreviousVersion(viewContain);
			if(doc != null) {
				strategy.removeVersion(doc);
				try {
					myTexEditorView.setTextToview(doc.getContents()); 
				}catch(Exception e) {Test_All.setTextToviewFromTest(doc.getContents());}
			}
		}
	}
}
