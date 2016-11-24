package command.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import command.Command;
import command.CommandRemove;
import solidText.Buffer;

public class CommandRemoveTest {
	
	private Buffer buffer;
	private String test = "test";
	
	@Before
	public void initTests(){
		buffer = new Buffer();
	}
	
	@Test
	public void executeTest1(){
		String expectedResult = "tes";
		buffer.replaceSelection(test);
		CommandRemove removecmd = new CommandRemove(buffer, Command.LEFT);
		removecmd.execute();
		assertEquals(expectedResult, buffer.toString());
	}
	
	@Test
	public void executeTest2(){
		String expectedResult = "test";
		buffer.replaceSelection(test);
		CommandRemove removecmd = new CommandRemove(buffer, Command.RIGHT);
		removecmd.execute();
		assertEquals(expectedResult, buffer.toString());
	}
	
	@Test
	public void executeTest3(){
		String expectedResult = "tet";
		buffer.replaceSelection(test);
		buffer.setSelect(2, 2);
		CommandRemove removecmd = new CommandRemove(buffer, Command.LEFT);
		removecmd.execute();
		assertEquals(expectedResult, buffer.toString());
	}
	
	@Test
	public void revertTest1(){
		buffer.replaceSelection(test);
		CommandRemove rmCmd = new CommandRemove(buffer, Command.LEFT);
		rmCmd.execute();
		rmCmd.revert();
		assertEquals(buffer.toString(), test);
	}

}
