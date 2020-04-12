package MainPack.test_pack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import MainPack.Command.AddMyTexCommand;
import MainPack.Document.Document;

class AddMyTexCommandTest {

private AddMyTexCommand addMyTexCommand;
	
	private Document document = new Document("author", "date", "copyright", "versionID", "");

	@Test
	public void ShouldAddChapter(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\chapter{...}");
		addMyTexCommand.execute();
		
		assertEquals("\\\\chapter{...}", document.getContents());
	}
	
	@Test
	public void ShouldAddSection(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\section{}");
		addMyTexCommand.execute();
		
		assertEquals("\\\\section{}", document.getContents());
	}
	
	@Test
	public void ShouldAddSubsection(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\subsection{}");
		addMyTexCommand.execute();
		
		assertEquals("\\\\subsection{}", document.getContents());
	}
	
	@Test
	public void ShouldAddSubsubsection(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\subsubsection{}");
		addMyTexCommand.execute();
		
		assertEquals("\\\\subsubsection{}", document.getContents());
	}
	
	@Test
	public void ShouldAddItemization(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\end{itemize}\r\n" + 
				"\\\\item ...\r\n" + 
				"\\\\begin{itemize}");
		addMyTexCommand.execute();
		
		assertEquals("\\\\end{itemize}\r\n" + 
				"\\\\item ...\r\n" + 
				"\\\\begin{itemize}", document.getContents());
	}
	
	@Test
	public void ShouldAddEnumeration(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\end{table}\r\n" + 
				"\\\\end{tabular}\r\n" + 
				"... &...&...\\\\\r\n" + 
				"  \\hline\r\n" + 
				"\\\\end{enumerate}\r\n" + 
				"\\\\item ...\r\n" + 
				"\\\\begin{enumerate}\r\n" + 
				"");
		addMyTexCommand.execute();
		
		assertEquals("\\\\end{table}\r\n" + 
				"\\\\end{tabular}\r\n" + 
				"... &...&...\\\\\r\n" + 
				"  \\hline\r\n" + 
				"\\\\end{enumerate}\r\n" + 
				"\\\\item ...\r\n" + 
				"\\\\begin{enumerate}\r\n" + 
				"", document.getContents());
	}
	
	@Test
	public void ShouldAddTable(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\begin{tabular}{|c|c|c|}\r\n" + 
				"\\\\caption{....}\\label{...}\r\n" + 
				"\\\\begin{table}\r\n" + 
				"");
		addMyTexCommand.execute();
		
		assertEquals("\\\\begin{tabular}{|c|c|c|}\r\n" + 
				"\\\\caption{....}\\label{...}\r\n" + 
				"\\\\begin{table}\r\n" + 
				"", document.getContents());
	}
	
	@Test
	public void ShouldAddFigure(){
		addMyTexCommand = new AddMyTexCommand(document, "\\\\end{figure}\r\n" + 
				"\\\\caption{....}\\label{...}\r\n" + 
				"\\\\includegraphics[width=...,height=...]{...}\r\n" + 
				"\\\\begin{figure}");
		addMyTexCommand.execute();
		
		assertEquals("\\\\end{figure}\r\n" + 
				"\\\\caption{....}\\label{...}\r\n" + 
				"\\\\includegraphics[width=...,height=...]{...}\r\n" + 
				"\\\\begin{figure}", document.getContents());
	}

}
