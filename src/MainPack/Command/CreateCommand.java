package MainPack.Command;

import MainPack.Document.Document;
import MainPack.Document.DocumentManager;
import MainPack.VersionsManager.VersionsManager;
import MainPack.VersionsManager.VersionsStrategy;
import MainPack.VersionsManager.VersionsStrategyFactory;

public class CreateCommand implements Command {
	
	private String TemplateID;
	private int ID = -1;
	private String StrategyType;
	private String FileName /* = "default_name" */;
	
	private DocumentManager documentManager ;
	private VersionsManager versionsManager;
	private VersionsStrategyFactory versionsStrategyFactory; 
	private Document document;
	
	private VersionsStrategy default_strategy; 
	
	public CreateCommand(String TemplateID, MyTexEditorController controller) {
		this.TemplateID = TemplateID; 
		
		versionsStrategyFactory = new VersionsStrategyFactory(controller);
		
		default_strategy = versionsStrategyFactory.createStrategy("Volatile");
		setStrategyType("Volatile");
		
		documentManager = new DocumentManager();
		versionsManager = new VersionsManager(default_strategy, controller);
	}
	
	public String getFileName() {
		return document.getFileName();
	}
	
	public void setFileName(String name) {
		document.setFileName(name);
	}
	
	public String[] getDetails() {
		return document.getDetails();
	}
	
	public String getStrategyType() {
		return StrategyType;
	}
	
	public void setStrategyType(String str) {
		StrategyType = str;
	}
	
	public String getTemplate() {
		return TemplateID;
	}
	
	public  VersionsManager getVersionsManager() {
		return this.versionsManager;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int id) {
		ID = id;
	}
	
	public VersionsStrategyFactory getVersionsStrategyFactory() {
		return this.versionsStrategyFactory;
	}
	
	public Document getDocument() {
		return this.document;
	}
	
	public void setDocument(Document doc) {
		this.document = doc;
	}
	
	@Override
	public void execute() {
		document = documentManager.createDocument(TemplateID);
	}
}
