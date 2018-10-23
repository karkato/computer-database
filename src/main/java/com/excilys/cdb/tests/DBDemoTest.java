package com.excilys.cdb.tests;
import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;


public class DBDemoTest{
	
	private Connection connect;
	@Before
	public void executerAvantChaqueTest() {
		
	}
	
	@After
	public void executerApresChaqueTest() {
		
		connect =null;
	}
	
	
	
	@Test
	public void connectionTest (Connection connect) {
		try {
			assertNotNull(connect);
		}catch (Exception e) {
			fail("Connection failed !");
		}
	}

	
}
