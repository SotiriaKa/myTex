package MainPack.Command;

import MainPack.Document.Document;

public class EditCommand implements Command{
	
	private Document document;

	public EditCommand(Document document) {
		this.document = document;
	}
	
	@Override
	public void execute() {
		document.setContents("Test Text for method set");
	} 
}
