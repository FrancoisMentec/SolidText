package solidText.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import solidText.EditorEngine;

public class EditorEngineTest {
	
	private EditorEngine editorEngine;
	
	@Before
	public void initBuffer(){
		editorEngine = new EditorEngine();
	}
	
	@Test
	public void replaceSelectionTest1(){
		String text = "test";
		editorEngine.replaceSelection(text);
		assertEquals(editorEngine.toString(), text);
		assertEquals(editorEngine.getStartSelect(), 4);
		assertEquals(editorEngine.getEndSelect(), 4);
	}
	
	@Test
	public void replaceSelectionTest2(){
		String text = "test";
		String text2 = "text";
		editorEngine.replaceSelection(text);
		editorEngine.replaceSelection(text2);
		assertEquals(editorEngine.toString(), text+text2);
		assertEquals(editorEngine.getStartSelect(), 8);
		assertEquals(editorEngine.getEndSelect(), 8);
	}
	
	@Test
	public void replaceSelectionTest3(){
		String text = "test";
		String text2 = "text";
		String expectedText = "tetextst";
		editorEngine.replaceSelection(text);
		editorEngine.setSelect(2, 2);
		editorEngine.replaceSelection(text2);
		assertEquals(editorEngine.toString(), expectedText);
		assertEquals(editorEngine.getStartSelect(), 6);
		assertEquals(editorEngine.getEndSelect(), 6);
	}
	
	@Test
	public void replaceSelectionTest4(){
		String text = "test";
		String text2 = "text";
		String expectedText = "textst";
		editorEngine.replaceSelection(text);
		editorEngine.setSelect(0, 2);
		editorEngine.replaceSelection(text2);
		assertEquals(editorEngine.toString(), expectedText);
		assertEquals(editorEngine.getStartSelect(), 4);
		assertEquals(editorEngine.getEndSelect(), 4);
	}
	
	
	@Test
	public void setSelectNegativeTest(){
		editorEngine.setSelect(-10, -20);
		assertEquals(editorEngine.getStartSelect(), 0);
		assertEquals(editorEngine.getEndSelect(), 0);
	}
	
	
	@Test
	public void removeSelectionTest1(){
		String r = editorEngine.removeSelection();
		assertEquals(r, "");
	}
	
	@Test
	public void removeSelectionTest2(){
		String text = "test";
		editorEngine.replaceSelection(text);
		String r = editorEngine.removeSelection();
		assertEquals(r, "");
	}
	
	@Test
	public void removeSelectionTest3(){
		String text = "test";
		editorEngine.replaceSelection(text);
		editorEngine.setSelect(0, 4);
		String r = editorEngine.removeSelection();
		assertEquals(r, "test");
	}
	
	@Test
	public void removeSelectionTest4(){
		String text = "test";
		String expectedText = "st";
		editorEngine.replaceSelection(text);
		editorEngine.setSelect(0, 2);
		editorEngine.removeSelection();
		assertEquals(editorEngine.toString(), expectedText);
	}
	
	
	@Test
	public void removeTest1(){
		String text = "test";
		String expectedText = "tet";
		editorEngine.replaceSelection(text);
		editorEngine.setSelect(2, 2);
		editorEngine.remove(EditorEngine.RIGHT);
		assertEquals(editorEngine.toString(), expectedText);
	}
	
	@Test
	public void removeTest2(){
		String text = "test";
		String expectedText = "tst";
		editorEngine.replaceSelection(text);
		editorEngine.setSelect(2, 2);
		editorEngine.remove();
		assertEquals(editorEngine.toString(), expectedText);
	}
	

}
