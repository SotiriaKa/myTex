package MainPack.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Document {
	
	private String FileName = "default_name";
	private String author;
	private String copyright;
	private String date;
	private String versionID;
	private String contents;
	private BufferedWriter bw;
	
	public Document(String author, String date, String copyright, String versionID,
			String contents) {
		this.author = author;
		this.contents = contents;
		this.copyright = copyright; 
		this.date = date; 
		this.versionID = versionID;
	}
	public String getFileName() {
		return FileName;
	}
	
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	
	public void setDatails(String[] details) {
		author    = details[0];
		copyright = details[1];
		date      = details[2]; 
		versionID = details[3];
	}
	
	public String[] getDetails() {
		String[] s = {author, date, copyright, versionID};
		return s;
	}

	public void setContents(String newContents) {
		this.contents = newContents;
	}
	
	public String getContents() {
		return this.contents;
	}
	
	public void save() { 
		try {
			File saveVersionFile = new File("MyTexFiles\\"+FileName+".tex");
			saveVersionFile.createNewFile();
			bw = new BufferedWriter(new FileWriter("MyTexFiles\\"+FileName+".tex"));
			bw.write(author+"\n"+copyright+"\n"+date+"\n"+versionID+"\n"+contents);
			bw.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public Document clone() {
		Document newDocument = new Document(this.author, this.date, this.copyright, this.versionID,
				this.contents);
		return newDocument;
	}
}
