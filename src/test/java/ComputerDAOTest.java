
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;


public class ComputerDAOTest {

	@Autowired
	private ComputerService computerService;

	@BeforeEach
	public void setUp() {

	}

	@AfterEach
	public void tearDown() {
		computerService = null;
	}

	@Test
	public void testNotNullFindAllComputers() {
		try {
			assertNotNull(computerService.findAll(""));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}

	@Test
	public void testEqualsComputerFindAllComputers() {
		try {
			List<Computer> result = computerService.findAll("");
			Computer computer = new Computer();
			for (int i = 0; i < result.size(); i++) {
				assertEquals(computer.getClass(), result.get(i).getClass());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}

	@Test
	@Disabled
	public void testCreateComputer() {
		try {
			Computer computer = new Computer();
			computer.setName("UnitTestCreate");
			Company company = new Company();
			computer.setCompany(company);
			computerService.create(computer);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}

	@Test
	@Disabled
	public void testUpdateComputer() {
		try {

			Computer computer = new Computer();
			computer.setId((long) 600);
			computer.setName("UnitTestUpdate");
			Company company = new Company();
			company.setId((long) 5);

			computer.setIntroDate(LocalDate.of(2000, 01, 01));
			computer.setDiscDate(LocalDate.of(2010, 01, 01));
			computer.setCompany(company);

			computerService.update(computer);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}

	@Test
	@Disabled
	public void testDeleteComputer() {
		try {
			computerService.delete((long) 650);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}

	@Test
	public void testFindByIdComputer() {
		try {
			Optional<Computer> computer = computerService.find((long) 12);
			assertEquals(12, computer.get().getId());
			assertEquals("Apple III", computer.get().getName());
			assertEquals("1980-05-01", computer.get().getIntroDate().toString());
			assertEquals("1984-04-01", computer.get().getDiscDate().toString());
			assertEquals("Apple Inc.", computer.get().getCompany().getName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}


	@Test
	public void testUpdateOutOfBoundComputer() {
		try {

			
			Computer computer = new Computer();
			computer.setId((long) 0);
			computer.setName("UnitTestUpdate");
			Company company = new Company();
			company.setId((long) 5);

			computer.setIntroDate(LocalDate.of(2000, 01, 01));
			computer.setDiscDate(LocalDate.of(2010, 01, 01));
			computer.setCompany(company);

			computerService.update(computer);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}

	@Test
	public void testUpdateComputerWithoutName() throws IOException, DataBaseException {

		Computer computer = new Computer();
		computer.setId((long) 600);
		computer.setName("");
		Company company = new Company();
		company.setId((long) 5);

		computer.setIntroDate(LocalDate.of(2000, 01, 01));
		computer.setDiscDate(LocalDate.of(2010, 01, 01));
		computer.setCompany(company);

		try {
			computerService.update(computer);
		} catch (DataException e) {
			e.printStackTrace();
			assertEquals("Le nom est requis", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteOutOfBoundComputer() {
		try {
			computerService.delete((long) 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception inattendue");
		}
	}
}