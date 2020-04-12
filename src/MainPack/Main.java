package MainPack;

import java.io.IOException;
import java.util.ArrayList;

import MainPack.view.ChoiceViewController;
import MainPack.view.MainViewController;
import MainPack.view.MyTexEditorView;
import MainPack.view.OpenFileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	private static Stage primaryStage;
	private static Stage addDialogStage;
	private static Stage SaveAsStage;
	private static Stage CloseTabStage;
	private static Stage OpenFileStage;
	private static Stage AreYouSureStage;
	private static AnchorPane mainLayout;

	private static boot_class boot = new boot_class(); 
	private static MainViewController mainViewController/* = boot.getMainViewController() */;
	private static MyTexEditorView myTexEditorView = boot.getMyTexEditorView();
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MyTex"); 
 
		showMainView();
		showChoiceViewStage();
	}
	
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml")); 
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		
		mainViewController = loader.getController();
		primaryStage.setOnCloseRequest(e -> mainViewController.shutdown()); 
		
		primaryStage.show();
		myTexEditorView.setMainViewController(mainViewController);
	} 

	public static void showChoiceViewStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/choiceView.fxml"));
		AnchorPane addNewEmployee = loader.load();

		addDialogStage = new Stage();
		addDialogStage.setTitle("MyTex");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(addNewEmployee);
		addDialogStage.setScene(scene);

		ChoiceViewController controller = loader.getController();
		addDialogStage.setOnHidden(e -> controller.shutdown());

		addDialogStage.showAndWait();
	}
	public static void closeChoiceViewStage() throws IOException {
		addDialogStage.close();
	}
	
	public static void showSaveAsStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/SaveAs.fxml"));
		BorderPane SaveAs = loader.load();

		SaveAsStage = new Stage();
//		SaveAsStage.setTitle("Save as...");
		SaveAsStage.initStyle(StageStyle.TRANSPARENT);
		SaveAsStage.initModality(Modality.WINDOW_MODAL);
		SaveAsStage.initOwner(primaryStage);
		Scene scene = new Scene(SaveAs);
		SaveAsStage.setScene(scene);

//		SaveAsController controller = loader.getController();
//		controller.save();
		
	
		SaveAsStage.showAndWait();
	} 
	public static void closeSaveAsStage() throws IOException {
		SaveAsStage.close();
	}
	
	public static void showOpenFileStage(ArrayList<String> strList) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/OpenFile.fxml"));
		BorderPane OpenFile = loader.load();

		OpenFileStage = new Stage();
//		OpenFileStage.setTitle("Open file...");
		OpenFileStage.initStyle(StageStyle.TRANSPARENT);
		OpenFileStage.initModality(Modality.WINDOW_MODAL);
		OpenFileStage.initOwner(primaryStage);
		Scene scene = new Scene(OpenFile);
		OpenFileStage.setScene(scene);

		OpenFileController controller = loader.getController();
		try {
			controller.chooseStr(strList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OpenFileStage.showAndWait();
	}
	public static void closeOpenFileStage() throws IOException {
		OpenFileStage.close();
	}
	
	public static void showCloseTabStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CloseTab.fxml"));
		AnchorPane CloseTab = loader.load();

		CloseTabStage = new Stage();
//		OpenFileStage.setTitle("Close tab...");
		CloseTabStage.initStyle(StageStyle.TRANSPARENT);
		CloseTabStage.initModality(Modality.WINDOW_MODAL);
		CloseTabStage.initOwner(primaryStage);
		Scene scene = new Scene(CloseTab);
		CloseTabStage.setScene(scene);

//		OpenFileController controller = loader.getController();
//		OpenFileStage.setOnHidden(e -> controller.shutdown());
	
		CloseTabStage.showAndWait();
	}
	public static void closeCloseTabStage() throws IOException {
		CloseTabStage.close();
	}
	
	public static void showAreYouSureStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AreYouSure.fxml"));
		AnchorPane AreYouSure = loader.load();

		AreYouSureStage = new Stage();
//		OpenFileStage.setTitle("Close tab...");
		AreYouSureStage.initStyle(StageStyle.TRANSPARENT);
		AreYouSureStage.initModality(Modality.WINDOW_MODAL);
		AreYouSureStage.initOwner(primaryStage);
		Scene scene = new Scene(AreYouSure);
		AreYouSureStage.setScene(scene);

//		OpenFileController controller = loader.getController();
//		OpenFileStage.setOnHidden(e -> controller.shutdown());
	
		AreYouSureStage.showAndWait();
	}
	public static void closeAreYouSureStage() throws IOException {
		AreYouSureStage.close();
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
