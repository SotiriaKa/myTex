package MainPack.view;

import java.io.IOException;

import MainPack.Main;
import MainPack.boot_class;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ChoiceViewController {
	
	private MyTexEditorView myTexEditorView = boot_class.getMyTexEditorView();
	
	@FXML private Button bookButton;
	@FXML private Button reportButton;
	@FXML private Button articleButton;
	@FXML private Button letterButton;
	@FXML private Button emptyButton;
	private boolean  emptycase = true;
	
	@FXML
	private void createBook() throws IOException {
		emptycase = false;
		Main.closeChoiceViewStage();
		myTexEditorView.addNewTab("book", "Volatile");
		myTexEditorView.createTemplate("book");
		myTexEditorView.newTemplate();    //showInView 
		myTexEditorView.enable_menu("chapterMenubutton", true);
		myTexEditorView.enable_menu("sectionMenubutton",true );
		myTexEditorView.enable_menu("subsectionMenubutton", true);
		myTexEditorView.enable_menu("subsubsectionMenubutton", true);
		myTexEditorView.enable_menu("itemizationListMenubutton", true);
		myTexEditorView.enable_menu("enumerationListMenubutton", true);
		myTexEditorView.enable_menu("tableMenubutton", true);
		myTexEditorView.enable_menu("figureMenubutton", true);
		
	}
	
	@FXML
	private void createReport() throws IOException {
		emptycase = false;
		Main.closeChoiceViewStage();
		myTexEditorView.addNewTab("report", "Volatile");
		myTexEditorView.createTemplate("report");
		myTexEditorView.newTemplate();
		myTexEditorView.enable_menu("chapterMenubutton", true);
		myTexEditorView.enable_menu("sectionMenubutton",true );
		myTexEditorView.enable_menu("subsectionMenubutton", true);
		myTexEditorView.enable_menu("subsubsectionMenubutton", true);
		myTexEditorView.enable_menu("itemizationListMenubutton", true);
		myTexEditorView.enable_menu("enumerationListMenubutton", true);
		myTexEditorView.enable_menu("tableMenubutton", true);
		myTexEditorView.enable_menu("figureMenubutton", true);
	}
	
	@FXML
	private void createArticle() throws IOException {
		emptycase = false;
		Main.closeChoiceViewStage();
		myTexEditorView.addNewTab("article", "Volatile");
		myTexEditorView.createTemplate("article");
		myTexEditorView.newTemplate();
		myTexEditorView.enable_menu("chapterMenubutton", false);
		myTexEditorView.enable_menu("sectionMenubutton",true );
		myTexEditorView.enable_menu("subsectionMenubutton", true);
		myTexEditorView.enable_menu("subsubsectionMenubutton", true);
		myTexEditorView.enable_menu("itemizationListMenubutton", true);
		myTexEditorView.enable_menu("enumerationListMenubutton", true);
		myTexEditorView.enable_menu("tableMenubutton", true);
		myTexEditorView.enable_menu("figureMenubutton", true);
	}
	
	@FXML
	private void createLetter() throws IOException {
		emptycase = false;
		Main.closeChoiceViewStage();
		myTexEditorView.addNewTab("letter", "Volatile");
		myTexEditorView.createTemplate("letter"); 
		myTexEditorView.newTemplate();
		myTexEditorView.enable_menu("chapterMenubutton", false);
		myTexEditorView.enable_menu("sectionMenubutton",false );
		myTexEditorView.enable_menu("subsectionMenubutton", false);
		myTexEditorView.enable_menu("subsubsectionMenubutton", false);
		myTexEditorView.enable_menu("itemizationListMenubutton", false);
		myTexEditorView.enable_menu("enumerationListMenubutton", false);
		myTexEditorView.enable_menu("tableMenubutton", false);
		myTexEditorView.enable_menu("figureMenubutton", false);
	}
	
	@FXML
	private void createEmpty() throws IOException {  
		Main.closeChoiceViewStage();
	} 
	
	@FXML
	public void shutdown() {
		if(emptycase) {
			myTexEditorView.addNewTab("empty", "Volatile");
			myTexEditorView.createTemplate("empty");
			myTexEditorView.newTemplate();
			myTexEditorView.enable_menu("chapterMenubutton", true);
			myTexEditorView.enable_menu("sectionMenubutton",true );
			myTexEditorView.enable_menu("subsectionMenubutton", true);
			myTexEditorView.enable_menu("subsubsectionMenubutton", true);
			myTexEditorView.enable_menu("itemizationListMenubutton", true);
			myTexEditorView.enable_menu("enumerationListMenubutton", true);
			myTexEditorView.enable_menu("tableMenubutton", true);
			myTexEditorView.enable_menu("figureMenubutton", true);
		}
	}
}
