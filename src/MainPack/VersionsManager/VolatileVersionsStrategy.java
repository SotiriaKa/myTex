package MainPack.VersionsManager;

import java.util.ArrayList;
import java.util.List;

import MainPack.Document.Document;

public class VolatileVersionsStrategy implements VersionsStrategy {
	
	private ArrayList<Document> historyList = new ArrayList<Document>();

	public VolatileVersionsStrategy() {
		
	}
	
	@Override
	public void putVersion(Document document) { 
		if(historyList.size() > 0) {	
			Document last_doc = historyList.get(historyList.size()-1);
			if(! (last_doc.getContents().equals(document.getContents()))) {
				historyList.add(document);
			}
		}else {
			historyList.add(document);
		}
	}

	@Override
	public Document getVersion(String viewContain) {
		int size = historyList.size();
		if(size > 0) {
			if(viewContain.equals(historyList.get(historyList.size()-1).getContents()) && size>1) {
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
	}

	@Override
	public List<Document> getEntireHistory() {
		return historyList;
	}

	@Override
	public void removeVersion(Document doc) {
		int size = historyList.size();
		for(int i=0; i<size; i++) {
			if(doc.getContents().equals(historyList.get(i).getContents())) {
				for(int j=i+1; j<size; j++) {
					historyList.remove(j);
				}
				break;
			}
		}
	}
}
