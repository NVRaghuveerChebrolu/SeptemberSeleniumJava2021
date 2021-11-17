package com.testNg.concepts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestNgFactory {
	private String param = "";

	//constructor with one argument
	public TestNgFactory(String param) {
		this.param = param;
	}

	@BeforeClass
	public void beforeClass() {
		long id = Thread.currentThread().getId();
		System.out.println("inside beforeClass executed. "+id);
	}

	@Test
	public void testcase1() {
		long id = Thread.currentThread().getId();
		System.out.println("inside testcase1 parameter value is: " + param+" "+ id);
	}
	
	@Test
	public void testcase2() {
		long id = Thread.currentThread().getId();
		System.out.println("inside testcase2 parameter value is: " + param +" "+ id);
	}
	
	@Test
	public void testcase3() {
		long id = Thread.currentThread().getId();
		System.out.println("inside testcase3 without parameter value "+id);
	}

	@Factory
	public Object[] factoryMethod() {
		return new Object[] { new TestNgFactory("hello"), new TestNgFactory("hi") };
	}
	
	@AfterClass
	public void AfterClass() {
		long id = Thread.currentThread().getId();
		System.out.println("inside AfterClass executed. "+id);
	}
}
