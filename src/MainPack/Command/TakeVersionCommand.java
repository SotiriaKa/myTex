package MainPack.Command;

import MainPack.Document.Document;
import MainPack.VersionsManager.VersionsManager;
import MainPack.test_pack.Test_All;
import MainPack.view.MyTexEditorView;

public class TakeVersionCommand implements Command{
	
	private Document document;
	private VersionsManager versionsManager;
	private MyTexEditorView myTexEditorView;
	private MyTexEditorController controller;
	
	
	public TakeVersionCommand(MyTexEditorController controller) {
		this.controller = controller;
		versionsManager = controller.getVersionsManager();
		try{myTexEditorView = controller.getMyTexEditorView();}catch(Exception e) {}
	}
 
	@Override
	public void execute() {
		String newContents = null;
		try {
			newContents = myTexEditorView.getTextFromView();
		}catch(Exception e) {newContents = Test_All.getTextToviewFromTest();}
		
		document = controller.getDocument().clone();  
		document.setContents(newContents);
		versionsManager.setCurrentVersion(document);
	}
}
