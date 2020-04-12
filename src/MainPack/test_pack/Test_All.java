package MainPack.test_pack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import MainPack.Command.MyTexEditorController;

public class Test_All {
	
	private MyTexEditorController controller;
	private static String SCREEN;


	public static void setTextToviewFromTest(String cont) {

		SCREEN  = cont;
	}
	
	public static String getTextToviewFromTest() {

		return SCREEN;
	}
	
	
	//test for US-04
	//Volatile
	@Test
	public void shouldHaveVolatileFile() throws Exception {
				
		controller = new MyTexEditorController(null);
		
		controller.enact("CreateReportID");
		controller.enact("NewTempateID");
		
		SCREEN = "version_for_Volatile_1";
		
		controller.enact("TakeVersionID");
		
		SCREEN = "version_for_Volatile_2";
		
		controller.enact("RollBackToPreviousVersionID");

		assertEquals("version_for_Volatile_1", SCREEN);
		
	}
	
	//Stable
	@Test
	public void shouldHaveStableleFile() throws Exception {
				
		controller = new MyTexEditorController(null);
		
		controller.enact("CreateReportID");
		controller.enact("NewTempateID");
		
		int id = controller.getID();
		
		controller.enact("ChangeVersionsStrategyToStableID");
		
		SCREEN = "version_for_Stable";
		
		controller.enact("TakeVersionID");
		
		SCREEN = "version_for_Stable_2";
		
		controller.enact("RollBackToPreviousVersionID");
		
		String st, last_commit = ""; 
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("_memory_\\"+id+"0.txt")); 
			String author    = br.readLine();
			String copyright = br.readLine();
			String date      = br.readLine();
			String versionID = br.readLine();
			
			while ((st = br.readLine()) != null) {
				last_commit += (st);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals("version_for_Stable", SCREEN);
		assertEquals(SCREEN, last_commit);
		
	}
	
	//test for US-05
	//Volatile to Stable
	@Test
	public void shouldChangeToStable() {
		controller = new MyTexEditorController(null);
		
		controller.enact("CreateReportID");
		controller.enact("NewTempateID");
		
		int id = controller.getID();
		
		SCREEN = "Volatile_Strategy_1";
		
		controller.enact("TakeVersionID");
		
		SCREEN = "Volatile_Strategy_2";
		
		controller.enact("ChangeVersionsStrategyToStableID");
		
		controller.enact("RollBackToPreviousVersionID");
		
		String st, last_commit = ""; 
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("_memory_\\"+id+"0.txt")); 
			String author    = br.readLine();
			String copyright = br.readLine();
			String date      = br.readLine();
			String versionID = br.readLine();
			
			while ((st = br.readLine()) != null) {
				last_commit += (st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("Volatile_Strategy_1", SCREEN);
		assertEquals(SCREEN, last_commit);

	}
	
	//Stable to Volatile
	@Test
	public void shouldChangeToVolatile() {
	controller = new MyTexEditorController(null);
	
	controller.enact("CreateReportID");
	controller.enact("NewTempateID");
	
	controller.enact("ChangeVersionsStrategyToStableID");
	
	SCREEN = "Stable_Strategy_1";
	
	controller.enact("TakeVersionID");
	
	SCREEN = "Stable_Strategy_2";
	
	controller.enact("ChangeVersionsStrategyToVolatileID");
	
	controller.enact("RollBackToPreviousVersionID");
	
	assertEquals(SCREEN, "Stable_Strategy_1" );
	}
	
	//test for US-06
	//Enable Mechanism
	@Test
	public void shouldEnableMechanism() {
		controller = new MyTexEditorController(null);
		
		controller.enact("CreateReportID");
		controller.enact("NewTempateID");
		
		controller.enact("EnableVersionsManagerID");
		
		SCREEN = "enable the mechanism";
		
		controller.enact("TakeVersionID");

		SCREEN = "enable the mechanism_2";
		
		controller.enact("RollBackToPreviousVersionID");

		assertEquals("enable the mechanism", SCREEN);
	
	}
	
	//Disable Mechanism
	@Test
	public void shouldDisableMechanism() {
		controller = new MyTexEditorController(null);
		
		controller.enact("CreateReportID");
		controller.enact("NewTempateID");
		
		controller.enact("DisableVersionsManagerID");
		
		SCREEN = "disable the mechanism";
		
		controller.enact("TakeVersionID");

		SCREEN = "disable the mechanism_2";
		
		controller.enact("RollBackToPreviousVersionID");

		assertEquals("disable the mechanism_2", SCREEN);

	}
	
	
	//test for US-07
	@Test
	public void shouldRollBackFile() throws Exception {
				
		controller = new MyTexEditorController(null);
		
		controller.enact("CreateReportID");
		controller.enact("NewTempateID");
				
		SCREEN = "version1";
		
		controller.enact("TakeVersionID");
		
		SCREEN = "version2";
		
		controller.enact("TakeVersionID");

		controller.enact("RollBackToPreviousVersionID");

		assertEquals("version1", SCREEN);
		
	}
	
	
	//test for US-08
	@Test
	public void shouldSaveFile() throws Exception {
							
		controller = new MyTexEditorController(null);
			
		controller.enact("CreateReportID");
		controller.enact("NewTempateID");
		
		int id = controller.getID();
		
		SCREEN = "EIMASTE OI css07";
		
		controller.setSelectedName("css07");
		
		controller.enact("SaveID");

		String st; 
		String Contents = "";
		try {
//			BufferedReader br = new BufferedReader(new FileReader("_memory_\\"+id+".txt")); 
			BufferedReader br = new BufferedReader(new FileReader("MyTexFiles\\css07.tex"));
			String author    = br.readLine();
			String copyright = br.readLine();
			String date      = br.readLine();
			String versionID = br.readLine();
			
			while ((st = br.readLine()) != null) {
				Contents += (st);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---> "+Contents);
		assertEquals("EIMASTE OI css07", Contents);
	}
	
	
		//test for US-09
		@Test
		public void shouldLoadFile()  {
			
			try {
			BufferedWriter Godbw = new BufferedWriter(new FileWriter("_memory_\\GodFile.txt"));
			
			Godbw.write("29%css07%Report%Volatile");
				
			Godbw.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("_memory_\\29.txt"));
			
			bw.write("%css07\n"+  
					 "%css07\n"+
					 "%19/4\n"+
					 "%1.0.0\n"+
					 "EIMASTE OI css07");
				
			bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			controller = new MyTexEditorController(null);
			
			controller.setSelectedName("css07");
	
			controller.enact("LoadID");
	
			assertEquals("EIMASTE OI css07", SCREEN);
	
		}
		
		
}