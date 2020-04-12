package MainPack.VersionsManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MainPack.Command.MyTexEditorController;
import MainPack.Document.Document;

public class StableVersionsStrategy implements VersionsStrategy {
	
	private BufferedWriter bw;
	
	private ArrayList<Document> historyList = new ArrayList<Document>();
	private int ID ;
	
	
	MyTexEditorController controller;

	public StableVersionsStrategy(MyTexEditorController controller) {
		this.controller = controller;
		ID = controller.getID();
	}
	
	private void updateID() {
		if(ID == -1) { 
			ID = controller.getID();
			loadHistoryFromDisk();
		} 
	}
	private void loadHistoryFromDisk() {
		int version = 0;
		BufferedReader br;
		while(true) {
			try {
				FileReader fr = new FileReader("_memory_\\"+ID+""+version+".txt");
				br = new BufferedReader(fr);
				try {
					String st;
					String newContents = "";
					String author = br.readLine();
					String copyright = br.readLine(); 
					String date      = br.readLine();
					String versionID = br.readLine(); 
					
					while ((st = br.readLine()) != null) {
						newContents += (st + '\n');
					}
					if(newContents.length()!=0)newContents = newContents.substring(0, newContents.length()-1);
					historyList.add(new Document(author, date, copyright, versionID, newContents));
					version++;
				} catch (IOException e) {e.printStackTrace();}
			}catch (FileNotFoundException e) {
				break;
			}
		}
	}
	
	private void addDocumentFile(Document doc, int version) {
		updateID();
		File saveVersionFile = new File("_memory_\\"+ID+""+version+".txt");
		String[] details = doc.getDetails();
		String Contents = doc.getContents();
		try {
			saveVersionFile.createNewFile();
			bw = new BufferedWriter(new FileWriter("_memory_\\"+ID+""+version+".txt"));
			bw.write(details[0]+"\n"+details[1]+"\n"+details[2]+"\n"+details[3]+"\n"+Contents);
			bw.close();
			System.out.println("add file at disk -> "+Contents);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void putVersion(Document document) {
		if(historyList.size() > 0) {	
			Document last_doc = historyList.get(historyList.size()-1);
			if(! (last_doc.getContents().equals(document.getContents()))) {
				historyList.add(document);
				addDocumentFile(document, historyList.size()-1);
			}
		}else {
			historyList.add(document);
			addDocumentFile(document, historyList.size()-1);
		}
	}

	@Override
	public Document getVersion(String viewContain) {
		int size = historyList.size();
		if(size > 0) {
			if(viewContain.equals(historyList.get(historyList.size()-1).getContents()) && size>1){
				return historyList.get(size-2);
			}else {
				return historyList.get(size-1);
			}
		}
		return null;
	}

	@Override
	public void setEntireHistory(List<Document> document_list) {
		historyList.clear();
		historyList.addAll(document_list);
		
		for(int i=0; i<historyList.size(); i++) {
			addDocumentFile(historyList.get(i), i);
		}
		updateID();
	}

	@Override
	public List<Document> getEntireHistory() {
		updateID(); 
		for(int i=0; i<historyList.size(); i++) {
			File f = new File("_memory_\\"+ID+""+i+".txt");
			f.delete();
		}
		return historyList;
	}

	@Override
	public void removeVersion(Document doc) {
		updateID();
		int size = historyList.size();
		for(int i=0; i<size; i++) {
			if(doc.getContents().equals(historyList.get(i).getContents())) {
				for(int j=i+1; j<size; j++) {
					File f = new File("_memory_\\"+ID+""+j+".txt");
					f.delete();
					historyList.remove(j);
				}
				break;
			}
		}
	}

}
