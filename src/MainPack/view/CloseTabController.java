package MainPack.view;

import java.io.IOException;

import MainPack.Main;
import MainPack.boot_class;
import javafx.fxml.FXML;

public class CloseTabController {

public MyTexEditorView myTexEditorView = boot_class.getMyTexEditorView();
	
	@FXML
	public void Yes() throws IOException {
		myTexEditorView.setSelectedName("Yes");
		Main.closeCloseTabStage();
	}

	@FXML 
	private void No() throws IOException {
		myTexEditorView.setSelectedName("No");
		Main.closeCloseTabStage();;
	}
	
	@FXML
	private void cancelButton() throws IOException {
		myTexEditorView.setSelectedName("_cancel_");
		Main.closeCloseTabStage();
	}
}
