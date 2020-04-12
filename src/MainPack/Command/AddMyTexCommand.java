package MainPack.Command;

import MainPack.Document.Document;

public class AddMyTexCommand implements Command {

	private Document document;
	private String command;

	
	public AddMyTexCommand(Document document, String command) { 
		this.document = document;
		this.command = command;
	}
	
	@Override
	public void execute() {
		document.setContents(command);
	}
	
	

}
