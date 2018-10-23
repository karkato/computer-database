package com.excilys.cdb.tests;
import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.jupiter.api.*;


public class Tests{
	
	private Connection connect;
	@Before
	public void executerAvantChaqueTest() {
	
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
