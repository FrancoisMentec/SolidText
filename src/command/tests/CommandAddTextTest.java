package command.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import command.CommandAddText;
import solidText.Buffer;

public class CommandAddTextTest {
	
	private static Buffer buffer;
	private static String testString;
	private static String testString2;
	
	@Before
	public static void initTestBuffer(){
		buffer = new Buffer();
		testString = new String("test");
		testString2 = new String("test2");
	}
	
	@Test
	public static void executeTest(){
		CommandAddText cat = new CommandAddText(buffer, testString);
		cat.execute();
		assertEquals(buffer.toString(), testString);
	}
	
	@Test
	public static void executeTest2(){
		CommandAddText cat1 = new CommandAddText(buffer, testString);
		CommandAddText cat2 = new CommandAddText(buffer, testString2);
		cat1.execute();
		cat2.execute();
		assertEquals(buffer.toString(), testString+testString2);
	}
	
	@Test
	public static void revertTest(){
		CommandAddText cat = new CommandAddText(buffer, testString);
		cat.execute();
		cat.revert();
		assertEquals(buffer.toString(), "");		
	}
	

}
