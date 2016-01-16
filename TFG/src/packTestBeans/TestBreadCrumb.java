package packTestBeans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packBeans.BreadCrumb;

public class TestBreadCrumb {
protected BreadCrumb bread,bread2;
	@Before
	public void setUp() throws Exception {
		bread = new BreadCrumb();
		bread.setName("name");
		bread.setUrl("url");
		
		bread2 = new BreadCrumb();
		bread2.setName("name");
		bread2.setUrl("url");
	}

	@After
	public void tearDown() throws Exception {
		bread = null;
	}

	@Test
	public void testGetName() {
		assertEquals("name", bread.getName());
	}

	@Test
	public void testSetName() {
		bread.setName("n");
		assertNotEquals("name", bread.getName());
		assertEquals("n", bread.getName());
	}

	@Test
	public void testGetUrl() {
		assertEquals("url", bread.getUrl());
	}

	@Test
	public void testSetUrl() {
		bread.setUrl("u");
		assertNotEquals("url", bread.getUrl());
		assertEquals("u", bread.getUrl());
	}

	@Test
	public void testEqualsObject() {
		assertEquals(bread2, bread);
		bread.setName("n");
		assertNotEquals(bread2, bread);
		
	}

}
