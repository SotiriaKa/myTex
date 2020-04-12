package MainPack.Command;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import MainPack.Document.Document;
import MainPack.VersionsManager.VersionsManager;
import MainPack.VersionsManager.VersionsStrategyFactory;
import MainPack.view.MyTexEditorView;


public class MyTexEditorController { 
	
	private MyTexEditorView myTexEditorView;

	private int activeTab = 0;
	private int newTab;
	
	private String SELECTED_NAME = null;
	
	private ArrayList<Integer> tmpID = new ArrayList<Integer>();
	private ArrayList<HashMap<String, Command>> commands = 
			new ArrayList<HashMap<String, Command>>();
	
	private CommandsFactory commandsFactory = new CommandsFactory(this);
	private LoadCommand loader;
	private Command ClearMemory;
	private String[] commandsID = 
		   {
			"RollBackToPreviousVersionID", 
			"SaveID", 
			"EnableVersionsManagerID", 
			"DisableVersionsManagerID", 
			"ChangeVersionsStrategyToVolatileID", 
			"ChangeVersionsStrategyToStableID",
			"TakeVersionID",
			"ShowInViewID",
			"NewFileID",
			"CloseTabID",
			"NewTempateID"}; 
	
	public MyTexEditorController(MyTexEditorView myTexEditorView) {
		this.myTexEditorView = myTexEditorView;
		loader = (LoadCommand) commandsFactory.createCommand("LoadID");
		ClearMemory = commandsFactory.createCommand("ClearMemoryID");
	}
	public void deleteHas() {
		UpdateTab();
		commands.remove(activeTab);
	}
	
	public void setSelectedName(String name) {
		SELECTED_NAME = name;
	} 
	
	public String getSelectedName() {
		return SELECTED_NAME;
	}
	
	private void UpdateTab() {
		try {
			activeTab = myTexEditorView.getTabNum(); 
		}catch(Exception e) {activeTab = 0;}	
	}
	
	public MyTexEditorView getMyTexEditorView() {
		return this.myTexEditorView;
	}
	
	public String getFileName() {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		return c.getFileName();
	}
	
	public void setFileName(String name) {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		c.setFileName(name);
	}
	
	public String getStrategyType() {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		return c.getStrategyType();
	}
	
	public void setStrategyType(String str) {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		c.setStrategyType(str);
	}
	
	public String getTemplate() {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID"); 
		return c.getTemplate();
	}
	
	public int getID() {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		return c.getID();
	}
	
	public void setID(int id) {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		c.setID(id);
	}
	
	public Document getDocument() {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		return c.getDocument();
	}
	
	public void setDocument(Document doc) {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		c.setDocument(doc);
	}
	
	public  VersionsManager getVersionsManager() {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		return c.getVersionsManager();
	}
	
	public VersionsStrategyFactory getVersionsStrategyFactory() {
		UpdateTab();
		CreateCommand c = (CreateCommand)commands.get(activeTab).get("CreateID");
		return c.getVersionsStrategyFactory();
	}
	
	public void enact(String commandKey) {
		UpdateTab();
		if(commandKey.startsWith("Create")) {  
			addCreateCommand(commandKey); 
			commands.get(newTab).get("CreateID").execute(); 
		}else if(commandKey == "LoadID"){
			loader.execute();
		}else if(commandKey == "ClearMemoryID") {
			ClearMemory.execute();
		}else {
			commands.get(activeTab).get(commandKey).execute();
		} 
	}
	
	private void addCreateCommand(String commandKey){ 
		if(commands.isEmpty()) {
			newTab = 0;
		}else {
			newTab = commands.size();
		}
		HashMap<String, Command> innerMap = new HashMap<String, Command>();
		innerMap.put("CreateID", commandsFactory.createCommand(commandKey));
//		((CreateCommand)(innerMap.get("CreateID"))).setID(newID());
		commands.add(innerMap);
		for(int i=0; i<commandsID.length; i++) { 
			commands.get(newTab).put(commandsID[i], commandsFactory.createCommand(commandsID[i])); 
		}
	}
	
	public int newID(){
		Random r = new Random();
		ArrayList<Integer> existedIDs = new ArrayList<Integer>();
		BufferedReader br = null;
		String st;
		int id;
		File GodFile = new File("_memory_\\GodFile.txt");
		try {
			GodFile.createNewFile();
			br = new BufferedReader(new FileReader("_memory_\\GodFile.txt"));
			while ((st = br.readLine()) != null) {
				existedIDs.add(Integer.valueOf(st.split("%")[0]));
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace(); 
		}
		
		existedIDs.addAll(tmpID);
		
		boolean exists;
		do{
			id = r.nextInt(1000);
			exists = false;
			for(int i : existedIDs) {
				if(i == id) {
					exists = true;
					break;
				}
			}
		}while(exists);
		
		tmpID.add(id);
		return id;
	}
	
}
