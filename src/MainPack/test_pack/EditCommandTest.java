package MainPack.test_pack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import MainPack.Command.EditCommand;
import MainPack.Document.Document;

class EditCommandTest {
	
	private EditCommand editCommand;
	
	private Document document = new Document("author", "date", "copyright", "versionID", "");
	

	@Test
	public void testForEdit(){
		editCommand = new EditCommand(document);
		editCommand.execute();
		
		assertEquals("Test Text for method set", document.getContents());
	}

}
