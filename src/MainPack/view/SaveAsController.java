package MainPack.view;

import java.io.IOException;

import MainPack.Main;
import MainPack.boot_class;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SaveAsController {
	
	public MyTexEditorView myTexEditorView = boot_class.getMyTexEditorView();
	
	@FXML TextField ta = null;
	
	@FXML
	public void save() throws IOException {
		String chosedStr = ta.getText();
		if(!chosedStr.equals("")) {
			myTexEditorView.setSelectedName(chosedStr);
			Main.closeSaveAsStage();
		}
	}

	@FXML
	private void cancelButton() throws IOException {
		myTexEditorView.setSelectedName("_cancel_");
		Main.closeSaveAsStage();
	}
}
