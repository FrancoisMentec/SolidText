package command.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import command.CommandAddText;
import solidText.EditorEngine;

public class CommandAddTextTest {
	
	private static EditorEngine buffer;
	private static String testString;
	private static String testString2;
	
	@Before
	public void initTestBuffer(){
		buffer = new EditorEngine();
		testString = new String("test");
		testString2 = new String("test2");
	}
	
	@Test
	public void executeTest(){
		CommandAddText cat = new CommandAddText(buffer, testString);
		cat.execute();
		assertEquals(buffer.toString(), testString);
	}
	
	@Test
	public void executeTest2(){
		CommandAddText cat1 = new CommandAddText(buffer, testString);
		cat1.execute();
		CommandAddText cat2 = new CommandAddText(buffer, testString2);
		cat2.execute();
		assertEquals(buffer.toString(), testString+testString2);
	}
	
	@Test
	public void revertTest(){
		CommandAddText cat = new CommandAddText(buffer, testString);
		cat.execute();
		cat.revert();
		assertEquals(buffer.toString(), "");		
	}
	

}
