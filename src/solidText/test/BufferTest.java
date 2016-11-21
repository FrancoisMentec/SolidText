package solidText.test;

import static org.junit.Assert.*;

import org.junit.Test;

import solidText.Buffer;

public class BufferTest {
	
	@Test
	public void replaceSelectionTest1(){
		String text = "test";
		Buffer buffer = new Buffer();
		buffer.replaceSelection(text);
		assertEquals(buffer.toString(), text);
		assertEquals(buffer.getStartSelect(), 4);
		assertEquals(buffer.getEndSelect(), 4);
	}
	
	@Test
	public void replaceSelectionTest2(){
		String text = "test";
		String text2 = "text";
		Buffer buffer = new Buffer();
		buffer.replaceSelection(text);
		buffer.replaceSelection(text2);
		assertEquals(buffer.toString(), text+text2);
		assertEquals(buffer.getStartSelect(), 8);
		assertEquals(buffer.getEndSelect(), 8);
	}
	
	@Test
	public void replaceSelectionTest3(){
		String text = "test";
		String text2 = "text";
		String expectedText = "tetextst";
		Buffer buffer = new Buffer();
		buffer.replaceSelection(text);
		buffer.setSelect(2, 2);
		buffer.replaceSelection(text2);
		assertEquals(buffer.toString(), expectedText);
		assertEquals(buffer.getStartSelect(), 6);
		assertEquals(buffer.getEndSelect(), 6);
	}
	
	@Test
	public void replaceSelectionTest4(){
		String text = "test";
		String text2 = "text";
		String expectedText = "textst";
		Buffer buffer = new Buffer();
		buffer.replaceSelection(text);
		buffer.setSelect(0, 2);
		buffer.replaceSelection(text2);
		assertEquals(buffer.toString(), expectedText);
		assertEquals(buffer.getStartSelect(), 4);
		assertEquals(buffer.getEndSelect(), 4);
	}
	
	
	@Test
	public void setSelectNegativeTest(){
		Buffer buffer = new Buffer();
		buffer.setSelect(-10, -20);
		assertEquals(buffer.getStartSelect(), 0);
		assertEquals(buffer.getEndSelect(), 0);
	}
	
	
	@Test
	public void removeSelectionTest1(){
		Buffer buffer = new Buffer();
		String r = buffer.removeSelection();
		assertEquals(r, "");
	}
	
	@Test
	public void removeSelectionTest2(){
		String text = "test";
		Buffer buffer = new Buffer();
		buffer.replaceSelection(text);
		String r = buffer.removeSelection();
		assertEquals(r, "");
	}
	
	@Test
	public void removeSelectionTest3(){
		String text = "test";
		Buffer buffer = new Buffer();
		buffer.replaceSelection(text);
		buffer.setSelect(0, 4);
		String r = buffer.removeSelection();
		assertEquals(r, "test");
	}
	
	@Test
	public void removeSelectionTest4(){
		String text = "test";
		String expectedText = "st";
		Buffer buffer = new Buffer();
		buffer.replaceSelection(text);
		buffer.setSelect(0, 2);
		buffer.removeSelection();
		assertEquals(buffer.toString(), expectedText);
	}
	
	

}
