package MainPack.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import MainPack.Document.Document;
import MainPack.view.MyTexEditorView;

public class CloseTabCommand implements Command{
	
	private MyTexEditorController controller;
	private MyTexEditorView myTexEditorView;
	private BufferedReader br;
	private BufferedReader br_saved_file;

	public CloseTabCommand(MyTexEditorController controller) {
		this.controller = controller;
		myTexEditorView = controller.getMyTexEditorView();
	} 

	@Override
	public void execute() {
		String contents_on_view = myTexEditorView.getTextFromView();
		boolean ok_close = true;
		int ID = controller.getID();
		String st;
		ArrayList<String> All_ID = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader("_memory_\\GodFile.txt"));
			while ((st = br.readLine()) != null) {
				All_ID.add(st.split("%")[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean id_found = false;
		for(String s : All_ID) {
			if(ID == Integer.valueOf(s)) {
				id_found = true;
				break;
			}
		}
		if(id_found) {
			Document saved_document = null;
			try {
				br_saved_file = new BufferedReader(new FileReader("_memory_\\"+ID+".txt"));
				String newContents = "";
				String author    = br_saved_file.readLine();
				String copyright = br_saved_file.readLine();
				String date      = br_saved_file.readLine();
				String versionID = br_saved_file.readLine();
				
				while ((st = br_saved_file.readLine()) != null) {
					newContents += (st + '\n');
				}
				if(newContents.length()!=0)newContents = newContents.substring(0, newContents.length()-1);
				saved_document = new Document(author, date, copyright, versionID, newContents);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(! saved_document.getContents().equals(contents_on_view)) {
				ok_close = false;
			}
		}else {
			ok_close = false;
		} 
		if(ok_close) {
			try {
				myTexEditorView.showAreYouSureStageWindow();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			while(controller.getSelectedName() == null);
			String answer = controller.getSelectedName();
			controller.setSelectedName(null);
			if(answer == "Yes") {
				controller.deleteHas();
				myTexEditorView.OKcloseTab(); 
			}else if(answer == "No") {
				//do nothing
			}else if(answer == "_cancel_") {
				//do nothing
			}
		}else {
			try {
				myTexEditorView.showCloseTabWindow();
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(controller.getSelectedName() == null); 
			String answer = controller.getSelectedName();
			controller.setSelectedName(null);
			if(answer == "Yes") {
				controller.enact("SaveID");
				controller.deleteHas();
				myTexEditorView.OKcloseTab();
			}else if(answer == "No") {
				controller.deleteHas();
				myTexEditorView.OKcloseTab();
			}else if(answer == "_cancel_") {
				//do nothing
			}
		}
	}
}















