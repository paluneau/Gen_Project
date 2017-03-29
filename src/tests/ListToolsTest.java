package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utils.ListTools;

public class ListToolsTest {

	List<String> l1, l2, l3, l4;

	@Before
	public void setUp() throws Exception {

		l1 = new ArrayList<String>();
		l1.add("42");

		l2 = new ArrayList<String>();

		l3 = new ArrayList<String>();
		l3.add("33");
		l3.add("267");
		l3.add("29");

		l4 = new ArrayList<String>();
		l4.add("3896");
		l4.add("3895");
		l4.add("3897");

	}

	@Test
	public void formatListTest() {

		assertEquals(ListTools.formatList(l2).toString(), "[]");

		assertEquals(ListTools.formatList(l1).toString(), "[42]");

		assertEquals(ListTools.formatList(l3).toString(), "[267, 33, 29]");

		assertEquals(ListTools.formatList(l4).toString(), "[3897, 3896, 3895]");
	}

}
