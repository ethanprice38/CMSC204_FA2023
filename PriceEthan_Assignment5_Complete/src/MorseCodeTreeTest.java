import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeTreeTest {
	MorseCodeTree tree = new MorseCodeTree();
	ArrayList<String> list2 = new ArrayList<String>();

	@Test
	public void testFetch() {
		assertEquals("A",tree.fetch(".-"));
	}
	
	@Test
	public void testInsert() {
		tree.insert(".....", ",");
		assertEquals(",", tree.fetch("....."));
	}
	
	@Test
	public void testToArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("H");
		list.add("S");
		list.add("V");
		list.add("I");
		list.add("F");
		list.add("U");
		list.add("E");
		list.add("L");
		list.add("R");
		list.add("A");
		list.add("P");
		list.add("W");
		list.add("J");
		list.add("");
		list.add("B");
		list.add("D");
		list.add("X");
		list.add("N");
		list.add("C");
		list.add("K");
		list.add("Y");
		list.add("T");
		list.add("Z");
		list.add("G");
		list.add("Q");
		list.add("M");
		list.add("O");
		
		tree.LNRoutputTraversal(tree.root, list2);
		
		assertEquals(list, list2);
	}
	
	@Test
	public void testLNRoutputTraversal() {
		tree.LNRoutputTraversal(tree.root, list2);
		assertEquals("H", list2.get(0));
		assertEquals("S", list2.get(1));
		assertEquals("V", list2.get(2));
		assertEquals("I", list2.get(3));
		assertEquals("F", list2.get(4));
		assertEquals("U", list2.get(5));
		assertEquals("E", list2.get(6));
		assertEquals("L", list2.get(7));
		assertEquals("R", list2.get(8));
		assertEquals("A", list2.get(9));
		assertEquals("P", list2.get(10));
		assertEquals("W", list2.get(11));
		assertEquals("J", list2.get(12));
		assertEquals("", list2.get(13));
		assertEquals("B", list2.get(14));
		assertEquals("D", list2.get(15));
		assertEquals("X", list2.get(16));
		assertEquals("N", list2.get(17));
		assertEquals("C", list2.get(18));
		assertEquals("K", list2.get(19));
		assertEquals("Y", list2.get(20));
		assertEquals("T", list2.get(21));
		assertEquals("Z", list2.get(22));
		assertEquals("G", list2.get(23));
		assertEquals("Q", list2.get(24));
		assertEquals("M", list2.get(25));
		assertEquals("O", list2.get(26));
	}
}
