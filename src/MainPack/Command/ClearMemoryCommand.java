package MainPack.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ClearMemoryCommand implements Command{
	

	private BufferedReader Godbr;

	public ClearMemoryCommand() {
	}

	@Override
	public void execute() {
		
		BufferedReader br;
		ArrayList<String> SavedIDs = new ArrayList<String>();
		String st;
		try {
			Godbr = new BufferedReader(new FileReader("_memory_\\GodFile.txt"));
			SavedIDs.clear();
			while ((st = Godbr.readLine()) != null) {
				SavedIDs.add(st.split("%")[0]);
			}
			Godbr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String id : SavedIDs) {
			int version = 0;
			boolean delete = false;
			FileReader fr = null;
			String Contents = "";
			try {
				fr = new FileReader("_memory_\\"+id+".txt");
				br = new BufferedReader(fr);
				Contents = "";
				String author    = br.readLine();
				String copyright = br.readLine(); 
				String date      = br.readLine();
				String versionID = br.readLine();
				
				while ((st = br.readLine()) != null) {
					Contents += (st + '\n');
				}
				if(Contents.length()!=0)Contents = Contents.substring(0, Contents.length()-1);
			} catch (IOException e) {e.printStackTrace();}
			while(true) {
				try {
					fr = new FileReader("_memory_\\"+id+""+version+".txt");
					br = new BufferedReader(fr);
					try {
						String VContents = "";
						String Vauthor    = br.readLine();
						String Vcopyright = br.readLine();  
						String Vdate      = br.readLine();
						String VversionID = br.readLine(); 
						
						while ((st = br.readLine()) != null) {
							VContents += (st + '\n');
						}
						if(VContents.length()!=0)VContents = VContents.substring(0, VContents.length()-1);
						
						if(Contents.equals(VContents)) 
							delete = true; 
						fr.close();
						if(delete) {
							File f = new File("_memory_\\"+id+""+version+".txt");
							f.delete();
						}
						version++; 
					} catch (IOException e) {
						/* e.printStackTrace(); */System.out.println(e.getMessage());}
				}catch (FileNotFoundException e) {
					break;
				}
			}
		}
	}

}
