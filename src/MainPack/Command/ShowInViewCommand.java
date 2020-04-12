package MainPack.Command;

//import MainPack.boot_class;
import MainPack.Document.Document;
import MainPack.test_pack.Test_All;
import MainPack.view.MyTexEditorView;

public class ShowInViewCommand implements Command{
	
	private Document document;
	private MyTexEditorController controller;
	
	private MyTexEditorView myTexEditorView;
	
	public ShowInViewCommand(MyTexEditorController controller) {
		this.controller = controller;
		try{myTexEditorView = controller.getMyTexEditorView();}catch(Exception e) {}
	}

	@Override
	public void execute() {
		document = controller.getDocument();
		try{
			myTexEditorView.setTextToview(document.getContents()); }
		catch(Exception e) {Test_All.setTextToviewFromTest(document.getContents());}
	}
}
