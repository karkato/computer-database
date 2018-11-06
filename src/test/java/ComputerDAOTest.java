
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

public class ComputerDAOTest {
private ComputerService computerService;
	
	@BeforeEach
	public void setUp() {
		computerService = ComputerService.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
		computerService=null;
	}
	
	@Test
	public void testNotNullFindAllComputers() {
		try {
			assertNotNull(computerService.findAll());	
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testEqualsComputerFindAllComputers() {
		try {
			List<Computer> result = computerService.findAll();
			Computer computer= new Computer();
			for(int i=0;i<result.size();i++) {
				assertEquals(computer.getClass(),result.get(i).getClass());
			}
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testCreateComputer() {
		try {
			Computer computer = new Computer();
			computer.setName("UnitTestCreate");
			Company company = new Company();
			computer.setCompany(company);
			assertTrue(computerService.create(computer));
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testUpdateComputer() {
		try {
			
			Computer computer = new Computer();
			computer.setId((long) 600);
			computer.setName("UnitTestUpdate");
			Company company = new Company();
			
			computer.setIntroDate(LocalDate.of(2000, 01, 01));
			computer.setDiscDate(LocalDate.of(2010, 01, 01));
			computer.setCompany(company);

			assertTrue(computerService.update(computer));
			
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testDeleteComputer() {
		try {	
			assertTrue(computerService.delete((long) 650));	
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testFindByIdComputer() {
		try {	
			Optional<Computer> computer = computerService.find((long) 12);
			assertEquals("Apple III",computer.get().getName());
			assertEquals("1980-05-01",computer.get().getIntroDate().toString());
			assertEquals("1984-04-01",computer.get().getDiscDate().toString());
			assertEquals("Apple Inc.",computer.get().getCompany().getName());
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testFindByIdOutofBoundComputer() throws IOException, SQLException{
		Optional<Computer> computer = null;
		try {	
			computer = computerService.find((long) 0);
		}catch(DataBaseException e) {
			assertNull(computer);
		}
	}
	
	@Test
	public void testUpdateOutOfBoundComputer() {
		try {
			
			Computer computer = new Computer();
			computer.setId((long) 0);
			computer.setName("UnitTestUpdate");
			Company company = new Company();
			
			computer.setIntroDate(LocalDate.of(2000, 01, 01));
			computer.setDiscDate(LocalDate.of(2010, 01, 01));
			computer.setCompany(company);

			assertFalse(computerService.update(computer));
			
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testUpdateComputerWithoutName() throws IOException, DataBaseException, SQLException {
		
			
			Computer computer = new Computer();
			computer.setId((long) 600);
			computer.setName("");
			Company company = new Company();
			
			computer.setIntroDate(LocalDate.of(2000, 01, 01));
			computer.setDiscDate(LocalDate.of(2010, 01, 01));
			computer.setCompany(company);

			try {
				computerService.update(computer);
			} catch (DataException e) {
				assertEquals("Le nom est requis",e.getMessage());			
			}
			
				
	}
	
	@Test
	public void testDeleteOutOfBoundComputer() {
		try {	
			assertFalse(computerService.delete((long) 0));	
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
}