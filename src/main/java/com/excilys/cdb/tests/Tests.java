package com.excilys.cdb.tests;
import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.jupiter.api.*;

import com.excilys.cdb.persistence.DBDemo;

public class Tests{
	
	@SuppressWarnings("unused")
	private Connection connect;
	private DBDemo dbDemo;
	@Before
	public void executerAvantChaqueTest() {
		dbDemo = new DBDemo();
		dbDemo.getInstance();
		
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
