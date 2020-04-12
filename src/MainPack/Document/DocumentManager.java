package MainPack.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DocumentManager {
	
	private static final String prototype_specs = "Templates\\prototype-specs.txt";
	private static final int ID = 0;
	private static final int STATE = 1;
	
	HashMap <String, Document> map = new HashMap <String, Document>();
	
	private BufferedReader prototypeSpecsReader;
	private BufferedReader br;
	
	public DocumentManager(){
		dynamicallyLoadDocuments(prototype_specs);
	}
	
	public void dynamicallyLoadDocuments(String prototypeSpecsFileName) {
		try {
			prototypeSpecsReader = new BufferedReader(
					new FileReader (prototypeSpecsFileName)
					);
			
			String currrentSpec;
			while ((currrentSpec = prototypeSpecsReader.readLine()) != null) {
				String keyStateConfigPair[] = currrentSpec.split(" ");
				map.put( 
						keyStateConfigPair[ID],
						loadDocument(keyStateConfigPair[STATE])
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Document loadDocument(String path) throws IOException {
		String author    = null;
		String date      = null;
		String copyright = null;
		String versionID = null; 
		String contents  = "";
		
		br = new BufferedReader(new FileReader(path)); 
		  
		author    = br.readLine();
		copyright = br.readLine();
		date      = br.readLine();
		versionID = br.readLine();
		 
		String st;
		while ((st = br.readLine()) != null) {
			contents += (st + '\n');
		}
		return new Document(author, date, copyright, versionID, contents);
	
	}
	
	public Document createDocument(String Type){
		Document doc = map.get(Type).clone();
		return doc;
		
	}

}
