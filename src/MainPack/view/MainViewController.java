package MainPack.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MainPack.Main;
import MainPack.boot_class;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

public class MainViewController {
	
	private boolean VersionState = true;
	
	private int count = 10;
	private int T = 1;
	private Tab activeTab = new Tab("_-_");
	
	private int caretPosition = 0;
	private ArrayList<String[]> TabList = new ArrayList<String[]>(); 
	
	public MyTexEditorView myTexEditorView = boot_class.getMyTexEditorView();
	
	ObservableList<String> versionsStrategy = FXCollections
			.observableArrayList("Volatile", "Stable");
	
	@FXML	private ComboBox<String> versionsStrategyBox;
	
	@FXML   private TextArea textArea;
	@FXML   private TabPane tabpane;
	
	@FXML   private Button commitVersionbutton;
	@FXML	private Button enableVersionbutton;
	
	@FXML   private Button chapterMenubutton;
	@FXML   private Button sectionMenubutton;
	@FXML   private Button subsectionMenubutton;
	@FXML   private Button subsubsectionMenubutton;
	@FXML   private Button itemizationListMenubutton;
	@FXML   private Button enumerationListMenubutton;
	@FXML   private Button tableMenubutton;
	@FXML   private Button figureMenubutton; 
	
	public void changeTextArea(String str) {
		((TextArea) activeTab.getContent()).setText(str);
	}
	
	public void showSaveWindow() throws IOException {
		Main.showSaveAsStage();
	}
	
	public void showAreYouSureStageWindow() throws IOException {
		Main.showAreYouSureStage();
	}
	
	public void showSavedFiles(ArrayList<String> allFileName) throws IOException {
		Main.showOpenFileStage(allFileName);
	}
	
	public void showCloseTabWindow() throws IOException {
		Main.showCloseTabStage();
	}
	
	public void changeTabTitle(String title) {
		TabList.get(getTabNum())[0] = title;
		activeTab.setText(title);
	}
	
	public int getTabNum() {
		for(String[] x : TabList){ 
			if(x[0].equals(activeTab.getText())) { 
				return TabList.indexOf(x);
			} 
		}
		return 0;
	}
	
	public void newTab(String template, String strategy) {
		
		Tab tab = new Tab("Tab "+T);
		String[] s = {"Tab "+T, template, strategy};
		T++;
		TabList.add(s);
		TextArea ta = new TextArea(null);
		
		ta.setOnMouseClicked(MouseEvent -> updatePosition());
		ta.setOnKeyReleased(KeyEvent -> updatePosition());
		tab.setContent(ta);
		
		tab.setOnSelectionChanged(event -> updateButtons());
		tab.setOnCloseRequest(event -> RequestCloseTab(event));
		tabpane.getTabs().add(tab);
		tabpane.getSelectionModel().select(tab);
		
		activeTab = tabpane.getSelectionModel().getSelectedItem();
	}
	
	private void RequestCloseTab(Event e) {
		activeTab = ((Tab) e.getSource());
		myTexEditorView.closeTab();
		activeTab = tabpane.getSelectionModel().getSelectedItem();
		e.consume();
	}
	
	public void OKcloseTab() {
		TabList.remove(getTabNum()); 
		tabpane.getTabs().remove(activeTab);
	}
	
	@FXML
	public void shutdown() {
		List<Tab> Tabs = tabpane.getTabs(); 
		int size = Tabs.size();
		int j = size-1;
		for(int i=0; i<size; i++){
			tabpane.getSelectionModel().select(Tabs.get(j));
			activeTab = Tabs.get(j);
			myTexEditorView.closeTab();
			j--;
		}
		myTexEditorView.ClearMemory();
	}
	
	public String takeTextArea() {
		return ((TextArea)activeTab.getContent()).getText();
	}
	
	public void enable_sectionMenubutton(boolean enable) {
			sectionMenubutton.setDisable(!enable); 	
	}

	public void enable_subsectionMenubutton(boolean enable) {
			subsectionMenubutton.setDisable(!enable); 	
	}
	
	public void enable_chapterMenubutton(boolean enable) {
			chapterMenubutton.setDisable(!enable); 	
	}
	
	public void enable_subsubsectionMenubutton(boolean enable) {
			subsubsectionMenubutton.setDisable(!enable); 	
	}
	
	public void enable_itemizationListMenubutton(boolean enable) {
			itemizationListMenubutton.setDisable(!enable); 	
	}
	
	public void enable_enumerationListMenubutton(boolean enable) {
			enumerationListMenubutton.setDisable(!enable); 
	}
	
	public void enable_tableMenubutton(boolean enable) {
			tableMenubutton.setDisable(!enable); 	
	}
	
	public void enable_figureMenubutton(boolean enable) {
			figureMenubutton.setDisable(!enable); 	
	}
	
	@FXML
	private void updateButtons() {
		activeTab = tabpane.getSelectionModel().getSelectedItem();
		
		if(! TabList.isEmpty()) {
			versionsStrategyBox.setValue(TabList.get(getTabNum())[2]);
			switch(TabList.get(getTabNum())[1]) {
			  case "letter":
				  enable_sectionMenubutton(false);
				  enable_subsectionMenubutton(false);
				  enable_chapterMenubutton(false);
				  enable_subsubsectionMenubutton(false);
				  enable_itemizationListMenubutton(false);
				  enable_enumerationListMenubutton(false);
				  enable_tableMenubutton(false);
				  enable_figureMenubutton(false);
			    break;
			  case "article":
				  enable_sectionMenubutton(false);
				  enable_subsectionMenubutton(true);
				  enable_chapterMenubutton(true);
				  enable_subsubsectionMenubutton(true);
				  enable_itemizationListMenubutton(true);
				  enable_enumerationListMenubutton(true);
				  enable_tableMenubutton(true);
				  enable_figureMenubutton(true);
			    break;
			  case "book":
			  case "report":
			  case "empty":	  
				  enable_sectionMenubutton(true);
				  enable_subsectionMenubutton(true);
				  enable_chapterMenubutton(true);
				  enable_subsubsectionMenubutton(true);
				  enable_itemizationListMenubutton(true);
				  enable_enumerationListMenubutton(true);
				  enable_tableMenubutton(true);
				  enable_figureMenubutton(true);
				  break;
			  default:	 
			}
		}
	}
	
	@FXML   
	private void save() {
		myTexEditorView.save();
	}
	
	@FXML
	private void enableVersion() {
		if(VersionState) {
			VersionState = false;
			enableVersionbutton.setText("Enable");
			myTexEditorView.DisableVersionsManager();
			commitVersionbutton.setDisable(true);
		}else {
			VersionState = true;
			enableVersionbutton.setText("Disable");
			myTexEditorView.EnableVersionsManager();
			commitVersionbutton.setDisable(false);
		}
	}
	
	@FXML   
	private void takeVersions() {
		myTexEditorView.TakeVersion();
	}
	
	@FXML
	private void RollbackToPreviousVersion() {
		myTexEditorView.RollBackToPreviousVersion();
//		myTexEditorView.newTemplate();
	}
	
	@FXML
	private void changeVersion() {
		if (versionsStrategyBox.getValue().contentEquals("Stable")) {
			if(TabList.get(getTabNum())[2] == "Volatile") {
				myTexEditorView.ChangeVersionsStrategyToStable();
				TabList.get(getTabNum())[2] = "Stable";
			}
		}else {
			if(TabList.get(getTabNum())[2] == "Stable") {
				myTexEditorView.ChangeVersionsStrategyToVolatile();
				TabList.get(getTabNum())[2] = "Volatile";
			}	
		}
	}
	
	@FXML
	private void newFile() throws IOException {
		Main.showChoiceViewStage();
	}
	
	@FXML
	private void openFile() {
		myTexEditorView.load();
	}
	
	 
	@FXML
	private void initialize() {
		versionsStrategyBox.setItems(versionsStrategy);
		versionsStrategyBox.setValue("Volatile");
	}
//	public void StrategyBoxSetTo(String Strategy) {
//		versionsStrategyBox.setValue(Strategy);
//	}
	
	@FXML
	private void commandSelectedChapter() {
		commandSelected("\\\\\\chapter{...}");
	}
	
	@FXML
	private void commandSelectedSection() {
		commandSelected("\\\\section{}");
	}
	
	@FXML
	private void commandSelectedSubsection() {
		commandSelected("\\\\subsection{}");
	}
	
	@FXML
	private void commandSelectedSubsubsection() {
		commandSelected("\\\\subsubsection{}");
	}
	
	@FXML
	private void commandSelectedItemizationList() {
		commandSelected("\\\\begin{itemize}\n"+
						"\\\\item ...\n"+
						"\\\\end{itemize}\n");
	}
	
	@FXML
	private void commandSelectedTABLE() {
		commandSelected("\\\\begin{table}\n"+
						"\\\\caption{....}\\label{...}\n"+
						"\\\\begin{tabular}{|c|c|c|}\n");
	}
	
	@FXML
	private void commandSelectedEnumerationList() {
		commandSelected("\\\\begin{enumerate}\n"+
						"\\\\item ...\n"+
						"\\\\end{enumerate}\n"+
						"  \\hline\n"+
						"... &...&...\\\\\n"+
						"\\\\end{tabular}\n"+
						"\\\\end{table}\n");
	}
	
	@FXML
	private void commandSelectedFIGURE() {
		commandSelected("\\\\begin{figure}\n"+
						"\\\\includegraphics[width=...,height=...]{...}\n"+
						"\\\\caption{....}\\label{...}\n"+
						"\\\\end{figure}\n");
	}
	
	@FXML
	private void updatePosition() {
		caretPosition = ((TextArea) activeTab.getContent()).getCaretPosition();
		count--;
		if(count == 0) {
			count = 10;
			myTexEditorView.TakeVersion();
		}
	}
	
	
	private void commandSelected(String command) {
		updatePosition();
		((TextArea) activeTab.getContent()).insertText(caretPosition, command);
		myTexEditorView.TakeVersion();
	}

	

	
	
	
}
