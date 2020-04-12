package MainPack.view;

import java.io.IOException;

import MainPack.Main;
import MainPack.boot_class;
import javafx.fxml.FXML;

public class AreYouSureController {
	
	public MyTexEditorView myTexEditorView = boot_class.getMyTexEditorView();

	@FXML
	public void Yes() throws IOException {
		myTexEditorView.setSelectedName("Yes");
		Main.closeAreYouSureStage();
	}

	@FXML 
	private void No() throws IOException {
		myTexEditorView.setSelectedName("No");
		Main.closeAreYouSureStage();
	}
	
	@FXML
	private void cancelButton() throws IOException {
		myTexEditorView.setSelectedName("_cancel_");
		Main.closeAreYouSureStage();
	}
}
