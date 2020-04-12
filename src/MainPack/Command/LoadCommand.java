package MainPack.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import MainPack.Document.Document;
import MainPack.view.MyTexEditorView;

public class LoadCommand implements Command {

	private MyTexEditorController controller;
	private MyTexEditorView myTexEditorView;
	private BufferedReader br;
	private BufferedReader br_saved_file;
	private ArrayList<String[]> GodArray = new ArrayList<String[]>();	

	public LoadCommand(MyTexEditorController controller) {
		this.controller = controller;
		try{myTexEditorView = controller.getMyTexEditorView();}catch(Exception e) {}
	}
	
	@Override
	public void execute() {
		String st; 
		ArrayList<String> AllFileName = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader("_memory_\\GodFile.txt"));
			while ((st = br.readLine()) != null) {
				GodArray.add(st.split("%"));
				AllFileName.add(st.split("%")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(! GodArray.isEmpty()) {
			int size = GodArray.size();
			String ChosedFileName = null; 
			try {
				myTexEditorView.showSavedFiles(AllFileName);
			} catch (Exception e) {}
//			while(controller.getSelectedName() == null);
			ChosedFileName = controller.getSelectedName();
			controller.setSelectedName(null); 
			
			String[] ChosedFile = null;
			for(int i=0; i<size; i++) {
				if(GodArray.get(i)[1].equals(ChosedFileName)) { 
					ChosedFile = GodArray.get(i);
					break;
				}
			}
			if(! ChosedFileName.equals("_cancel_")) {
				switch(ChosedFile[2]) {
					case "Article":
						myTexEditorView.addNewTab("article", ChosedFile[3]);
						controller.enact("CreateArticleID"); 
						myTexEditorView.enable_menu("chapterMenubutton", false);
						myTexEditorView.enable_menu("sectionMenubutton",true );
						myTexEditorView.enable_menu("subsectionMenubutton", true);
						myTexEditorView.enable_menu("subsubsectionMenubutton", true);
						myTexEditorView.enable_menu("itemizationListMenubutton", true);
						myTexEditorView.enable_menu("enumerationListMenubutton", true);
						myTexEditorView.enable_menu("tableMenubutton", true);
						myTexEditorView.enable_menu("figureMenubutton", true);
						break;
					case "Report":
						try {
						myTexEditorView.addNewTab("report", ChosedFile[3]);}catch(Exception e) {}
						controller.enact("CreateReportID");
						try {
						myTexEditorView.enable_menu("chapterMenubutton", true);
						myTexEditorView.enable_menu("sectionMenubutton",true );
						myTexEditorView.enable_menu("subsectionMenubutton", true);
						myTexEditorView.enable_menu("subsubsectionMenubutton", true);
						myTexEditorView.enable_menu("itemizationListMenubutton", true);
						myTexEditorView.enable_menu("enumerationListMenubutton", true);
						myTexEditorView.enable_menu("tableMenubutton", true);
						myTexEditorView.enable_menu("figureMenubutton", true);}catch(Exception e) {}
						break;
					case "Letter":
						myTexEditorView.addNewTab("letter", ChosedFile[3]);
						controller.enact("CreateLetterID"); 
						myTexEditorView.enable_menu("chapterMenubutton", false);
						myTexEditorView.enable_menu("sectionMenubutton",false );
						myTexEditorView.enable_menu("subsectionMenubutton", false);
						myTexEditorView.enable_menu("subsubsectionMenubutton", false);
						myTexEditorView.enable_menu("itemizationListMenubutton", false);
						myTexEditorView.enable_menu("enumerationListMenubutton", false);
						myTexEditorView.enable_menu("tableMenubutton", false);
						myTexEditorView.enable_menu("figureMenubutton", false);
						break;
					case "Book":
						myTexEditorView.addNewTab("book", ChosedFile[3]);
						controller.enact("CreateBookID"); 
						myTexEditorView.enable_menu("chapterMenubutton", true);
						myTexEditorView.enable_menu("sectionMenubutton",true );
						myTexEditorView.enable_menu("subsectionMenubutton", true);
						myTexEditorView.enable_menu("subsubsectionMenubutton", true);
						myTexEditorView.enable_menu("itemizationListMenubutton", true);
						myTexEditorView.enable_menu("enumerationListMenubutton", true);
						myTexEditorView.enable_menu("tableMenubutton", true);
						myTexEditorView.enable_menu("figureMenubutton", true);
						break;	
					case "Empty":
						myTexEditorView.addNewTab("empty", ChosedFile[3]);
						controller.enact("CreateEmptyID"); 
						myTexEditorView.enable_menu("chapterMenubutton", true);
						myTexEditorView.enable_menu("sectionMenubutton",true );
						myTexEditorView.enable_menu("subsectionMenubutton", true);
						myTexEditorView.enable_menu("subsubsectionMenubutton", true);
						myTexEditorView.enable_menu("itemizationListMenubutton", true);
						myTexEditorView.enable_menu("enumerationListMenubutton", true);
						myTexEditorView.enable_menu("tableMenubutton", true);
						myTexEditorView.enable_menu("figureMenubutton", true); 
						break;
					default:
				}
				try {
				myTexEditorView.changeTabTitle(ChosedFile[1]); }catch(Exception e) {}
				controller.setID(Integer.valueOf(ChosedFile[0]));
				Document document = controller.getDocument();
				try {
					br_saved_file = new BufferedReader(new FileReader("_memory_\\"+ChosedFile[0]+".txt"));
					String newContents = "";
					String author    = br_saved_file.readLine();
					String copyright = br_saved_file.readLine();
					String date      = br_saved_file.readLine();
					String versionID = br_saved_file.readLine();
					 
					while ((st = br_saved_file.readLine()) != null) {
						newContents += (st + '\n');
					}
					if(newContents.length()!=0)newContents = newContents.substring(0, newContents.length()-1);
					document = new Document(author, date, copyright, versionID, newContents);
					document.setFileName(ChosedFile[1]);
					controller.setDocument(document);
				} catch (IOException e) {
					e.printStackTrace();
				}
				controller.enact("ShowInViewID");
				if(ChosedFile[3].equals("Stable")) {
					controller.enact("ChangeVersionsStrategyToStableID");
				}
			}
		}
	}
}
