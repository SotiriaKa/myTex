package MainPack.Command;

public class CommandsFactory {
	
	
	private MyTexEditorController controller;

	public CommandsFactory(MyTexEditorController controller) {
		this.controller = controller;
	} 
	
	public Command createCommand(String commandID) {
		
		if( commandID == "CreateArticleID"){
			return new CreateCommand("Article", controller);
		}else if( commandID == "CreateReportID"){
			return new CreateCommand("Report", controller);
		}else if( commandID == "CreateBookID"){  
			return new CreateCommand("Book", controller); 
		}else if( commandID == "CreateLetterID"){ 
			return new CreateCommand("Letter", controller);
		}else if( commandID == "CreateEmptyID"){
			return new CreateCommand("Empty", controller);
		}else if(commandID == "RollBackToPreviousVersionID") {
			return new RollBackToPreviousVersionCommand(controller);
		}else if(commandID == "LoadID") {
			return new LoadCommand(controller);
		}else if(commandID == "SaveID") {
			return new SaveCommand(controller);
		}else if(commandID == "EnableVersionsManagerID") {
			return new EnableVersionsManagementCommand(controller);
		}else if(commandID == "DisableVersionsManagerID") {
			return new DisableVersionsManagementCommand(controller);
		}else if(commandID == "ChangeVersionsStrategyToStableID") {
			return new ChangeVersionsStrategyToStableCommand(controller);
		}else if(commandID == "ChangeVersionsStrategyToVolatileID") {
			return new ChangeVersionsStrategyToVolatileCommand(controller);
		}else if (commandID == "TakeVersionID") {
			return new TakeVersionCommand(controller);
		}else if  (commandID == "ShowInViewID") {
			return new ShowInViewCommand(controller);
		}else if (commandID == "CloseTabID") {
			return new CloseTabCommand(controller);
		}else if (commandID == "NewTempateID") {
			return new NewTempateCommand(controller);
		}else if (commandID == "ClearMemoryID") {
			return new ClearMemoryCommand();
		}
		return null;
	}
	
}
