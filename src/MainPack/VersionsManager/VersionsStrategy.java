package MainPack.VersionsManager;

import java.util.List;

import MainPack.Document.Document;

public interface VersionsStrategy {
	
	public void putVersion(Document document) ;
	
	public Document getVersion(String viewContain);
	
	public void setEntireHistory(List<Document> document_list);
	
	public List <Document> getEntireHistory();
	
	public void removeVersion(Document doc);
	
}
