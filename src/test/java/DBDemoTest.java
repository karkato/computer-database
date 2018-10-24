import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DBDemoTest{
	
	@SuppressWarnings("unused")
	private Connection connect;
	@BeforeEach
	public void executerAvantChaqueTest() {
		
	}
	
	@AfterEach
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
