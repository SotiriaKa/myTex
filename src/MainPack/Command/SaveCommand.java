package MainPack.Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import MainPack.Document.Document;
import MainPack.test_pack.Test_All;
import MainPack.view.MyTexEditorView;


public class SaveCommand implements Command {
	
	private MyTexEditorController controller;
	private MyTexEditorView myTexEditorView;
	private BufferedWriter Godbw;
	private BufferedWriter bw;
	private BufferedReader Godbr;
	private ArrayList<String[]> GodArray = new ArrayList<String[]>();

	public SaveCommand(MyTexEditorController controller) {
		this.controller = controller;
		try{myTexEditorView = controller.getMyTexEditorView();}catch(Exception e) {}
	}
	 
	@Override
	public void execute() {
		boolean newSave = true;
		boolean ok_Save = true;
		int ID = controller.getID();
		String FileName = controller.getFileName();
		String template = controller.getTemplate();
		String StrategyType = controller.getStrategyType();
		String newContents = null;
		try {
			newContents = myTexEditorView.getTextFromView();
		}catch(Exception e) {newContents = Test_All.getTextToviewFromTest();}	
		Document document = controller.getDocument().clone(); 
		String[] details = document.getDetails();
		document.setContents(newContents);
		String st;
		try {
			Godbr = new BufferedReader(new FileReader("_memory_\\GodFile.txt"));
			GodArray.clear();
			while ((st = Godbr.readLine()) != null) {
				GodArray.add(st.split("%"));
			}
			Godbr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(! GodArray.isEmpty()) {
			
			for(int i=0; i<GodArray.size(); i++) {	
				if(Integer.valueOf(GodArray.get(i)[0]) == ID){
					GodArray.get(i)[3] = StrategyType;
					newSave = false;
					break;
				}
			}
		}
		if(newSave) {
			
			try {
				myTexEditorView.showSaveWindow(); 
			} catch (Exception e) {}
//			while(controller.getSelectedName() == null);
			FileName = controller.getSelectedName();
			controller.setSelectedName(null);
			if(FileName == "_cancel_") {
				ok_Save = false;
			}else {
				controller.setFileName(FileName);
				try {
					myTexEditorView.changeTabTitle(FileName);
				} catch (Exception e) {}
				String[] s = {String.valueOf(ID), FileName, template, StrategyType};
				
				GodArray.add(s);
			}
			
		}
		if(ok_Save) { 
			try {
				File saveFile = new File("_memory_\\"+ID+".txt"); 
			
				saveFile.createNewFile();
				bw = new BufferedWriter(new FileWriter("_memory_\\"+ID+".txt"));
				bw.write(details[0]+"\n"+details[1]+"\n"+details[2]+"\n"+details[3]+"\n"+newContents);
				bw.close();
				controller.enact("TakeVersionID");
			
				
				
				document.setFileName(FileName);
				document.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		try {
			Godbw = new BufferedWriter(new FileWriter("_memory_\\GodFile.txt"));
			for(String[] s : GodArray) {
					Godbw.write(s[0]+"%"+s[1]+"%"+s[2]+"%"+s[3]+"\n");
			}	
			Godbw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
