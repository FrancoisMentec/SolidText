package command.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import command.CommandMove;
import solidText.EditorEngine;

public class CommandMoveTest {

	private EditorEngine buffer;
	private String test = "01234567879";
	
	@Before
	public void initTests(){
		buffer = new EditorEngine();
		buffer.replaceSelection(test);
		buffer.setSelect(0, 0);
	}
	
	@Test
	public void executeTest(){
		CommandMove cm = new CommandMove(buffer, CommandMove.RIGHT);
		cm.execute();
		assertEquals(buffer.getFirst(), 1);
	}
	
	@Test
	public void executeTest2(){
		CommandMove cm = new CommandMove(buffer, CommandMove.LEFT);
		cm.execute();
		assertEquals(buffer.getFirst(), 0);
	}
	
	@Test
	public void executeTest3(){
		CommandMove cm = new CommandMove(buffer, CommandMove.RIGHT);
		cm.execute();
		cm.execute();
		assertEquals(buffer.getFirst(), 2);
	}
	
}
