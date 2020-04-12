package MainPack.view;

import java.io.IOException;
import java.util.ArrayList;

import MainPack.Main;
import MainPack.boot_class;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class OpenFileController {

	private static final double MAX_VALUE = 1000;
	
	public MyTexEditorView myTexEditorView = boot_class.getMyTexEditorView();
	
	@FXML private VBox vBox;
	
	public void chooseStr(ArrayList<String> strList) throws InterruptedException {
		for(int i=0; i<strList.size(); i++) {
			Button bt = new Button();
			bt.setMaxWidth(MAX_VALUE);
			bt.setText(strList.get(i));
			bt.setStyle("-fx-background-color: #F26350;");  
			bt.setTextFill((Paint.valueOf("#f8c28f"))); 
			bt.setOnAction(e -> select(e));
			vBox.getChildren().add(bt);
		} 
	}
	
	private void select(Event e) {
		String chosedStr = ((Button) e.getSource()).getText();
		myTexEditorView.setSelectedName(chosedStr);
		System.out.println(chosedStr);
		try {
			Main.closeOpenFileStage();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}	

	@FXML
	private void cancelButton() throws IOException {
		myTexEditorView.setSelectedName("_cancel_");
		Main.closeOpenFileStage();
	}
}
