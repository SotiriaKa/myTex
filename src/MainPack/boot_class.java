package MainPack;

import MainPack.view.MainViewController;
import MainPack.view.MyTexEditorView;

public class boot_class {

	public static MyTexEditorView myTexEditorView = new MyTexEditorView();
//	public static MainViewController mainViewController = new MainViewController();
	
	public boot_class() {
		
	}
	
//	public MainViewController getMainViewController() {
//		return this.mainViewController;
//	}
	
	public static MyTexEditorView getMyTexEditorView() {
		 return myTexEditorView; 
	 }
}
