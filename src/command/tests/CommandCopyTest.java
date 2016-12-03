package command.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import command.ClipboardManager;
import command.CommandCopy;
import solidText.EditorEngine;

public class CommandCopyTest {
	
	private EditorEngine buffer;
	private CommandCopy cmdCopy;
	
	private final String test = "test copy";
	
	@Before
	public void initTests(){
		buffer = new EditorEngine();
		cmdCopy = new CommandCopy(buffer);
	}
	
	@Test
	public void executeTest1(){
		buffer.replaceSelection(test);
		buffer.setSelect(0, test.length());
		cmdCopy.execute();
		String result = ClipboardManager.getContent();
		assertEquals(test, result);
	}

}
