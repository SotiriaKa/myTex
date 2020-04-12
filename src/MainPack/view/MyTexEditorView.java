package MainPack.view;

import java.io.IOException;
import java.util.ArrayList;

import MainPack.Command.MyTexEditorController;
import MainPack.view.MainViewController;

public class MyTexEditorView {
	
	private static MyTexEditorController mytexcontr;
	
	private MainViewController mainViewController;
	 
	
	public MyTexEditorView() {
		mytexcontr = new MyTexEditorController(this);
	}

	public void createTemplate(String template) {
		if (template == "book") {
			mytexcontr.enact("CreateBookID");
		}else if (template == "report") {
			mytexcontr.enact("CreateReportID");
		}else if (template == "article") {
			mytexcontr.enact("CreateArticleID");
		}else if (template == "letter") {
			mytexcontr.enact("CreateLetterID");
		}else if (template == "empty") {
			mytexcontr.enact("CreateEmptyID");
		}
	}
	
	public void enable_menu(String Button, boolean enable) { 
		
		if(Button == "chapterMenubutton") {
			mainViewController.enable_chapterMenubutton(enable);
		}else if(Button == "sectionMenubutton") {
			mainViewController.enable_sectionMenubutton(enable);
		}else if(Button == "subsectionMenubutton") {
			mainViewController.enable_subsectionMenubutton(enable);
		}else if(Button == "subsubsectionMenubutton") {
			mainViewController.enable_subsubsectionMenubutton(enable);
		}else if(Button == "itemizationListMenubutton") {
			mainViewController.enable_itemizationListMenubutton(enable);
		}else if(Button == "enumerationListMenubutton") {
			mainViewController.enable_enumerationListMenubutton(enable);
		}else if(Button == "tableMenubutton") {
			mainViewController.enable_tableMenubutton(enable);
		}else if(Button == "figureMenubutton") {
			mainViewController.enable_figureMenubutton(enable);
		}
	}
	
	public void setSelectedName(String name) {
		mytexcontr.setSelectedName(name);
	}
	
	public void OKcloseTab() {
		mainViewController.OKcloseTab();
	}
	
	public void showSavedFiles(ArrayList<String> allFileName) throws IOException { 
		mainViewController.showSavedFiles(allFileName);
	}
	
	public void showSaveWindow() throws IOException {
		mainViewController.showSaveWindow();
	}
	
	public void showAreYouSureStageWindow() throws IOException  {
		mainViewController.showAreYouSureStageWindow();
	}
	
	public void showCloseTabWindow() throws IOException {
		mainViewController.showCloseTabWindow();
	}
	
	public void changeTabTitle(String title) {
		mainViewController.changeTabTitle(title);
	}
	
	public int getTabNum() {
		return mainViewController.getTabNum();
	}
	
	public void setMainViewController(MainViewController mainViewController) {
		this.mainViewController = mainViewController;
	}
	
	public void newTemplate() {
		mytexcontr.enact("NewTempateID");
	}
	
	public void addNewTab(String template, String strategy) {
		mainViewController.newTab(template, strategy);
	}
	
	
	public void setTextToview(String str) {
		mainViewController.changeTextArea(str);
	}
	
	public String getTextFromView() {
		return mainViewController.takeTextArea();
	}
	
	public void closeTab() {
		mytexcontr.enact("CloseTabID");
	}
	
	public void save() {
		mytexcontr.enact("SaveID");
	}
	
	public void load() {
		mytexcontr.enact("LoadID");
	}
	
	public void edit() {
		mytexcontr.enact("EditID");
	}
	
	public void EnableVersionsManager() {
		mytexcontr.enact("EnableVersionsManagerID");
	}
	
	public void DisableVersionsManager() {
		mytexcontr.enact("DisableVersionsManagerID");
	}
	
	public void ChangeVersionsStrategyToVolatile() {
		mytexcontr.enact("ChangeVersionsStrategyToVolatileID");
	}
	
	public void ChangeVersionsStrategyToStable() {
		mytexcontr.enact("ChangeVersionsStrategyToStableID");
	}
	
	public void RollBackToPreviousVersion() {
		mytexcontr.enact("RollBackToPreviousVersionID");
	}
	
	public void NewFile() {
		mytexcontr.enact("NewFileID");
	}
	
	public void TakeVersion() {
		mytexcontr.enact("TakeVersionID");
	}

	public void ClearMemory() {
		mytexcontr.enact("ClearMemoryID");
	}
	
}
