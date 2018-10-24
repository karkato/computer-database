import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDAO;

public class ComputerDAOTest {

	private ComputerDAO computerDAO;

	private Computer computer;
	private Company company;

	@BeforeEach
	public void beforeEachTest() {

		company = new Company();
		company.setId((long) 1);
		company.setName("Apple Inc.");

		computer = new Computer();
		computer.setName("iPhone 4S");
		computer.setIntroDate(LocalDate.of(2011, 10, 14));
		computer.setDiscDate(null);
		computer.setCompany(company);

	}

	/**
	 */
	@AfterEach
	public void afterEachTest() {

		computer = null;
		company = null;
	}


	// CREATE


	@Test
	public void createComputerShouldReturnGeneratedId() {
		long idGenerated = (computerDAO.find(computer.getId())).getId();
		computer = new Computer();
		computer.setName("iPhone 4S");
		computer.setIntroDate(LocalDate.of(2011, 10, 14));
		computer.setDiscDate(null);
		computer.setCompany(company);
		computerDAO.create(computer);
		long idGenerated2 = (computerDAO.find(computer.getId()).getId());
		assertEquals((idGenerated + 1), idGenerated2);
		computerDAO.delete(idGenerated);
		computerDAO.delete(idGenerated2);
	}

	public void createComputerShouldThrowExceptionBecauseNameIsNull() {
		computer.setName(null);
		computerDAO.create(computer);
		computerDAO.delete(computer.getId());
	}

	public void createComputerShouldThrowExceptionBecauseNameHasInvalidSymbols() {
		computer.setName("&)(\\$><");
		computerDAO.create(computer);
		computerDAO.delete(computer.getId());
	}

	public void createComputerShouldThrowExceptionBecauseNameIsEmpty() {
		computer.setName("");
		computerDAO.create(computer);
		computerDAO.delete(computer.getId());
	}

	public void createComputerShouldThrowExceptionBecauseIntroducedDateIsAfterDateDiscontinued() {
		computer.setIntroDate(LocalDate.of(2011, 10, 14));
		computer.setDiscDate(LocalDate.of(2010, 10, 14));
		computerDAO.create(computer);
		computerDAO.delete(computer.getId());
	}

	public void createComputerShouldThrowExceptionBecauseCompanyIdIsInvalid() {
		company.setId((long) -1);
		computerDAO.create(computer);
		computerDAO.delete(computer.getId());
	}

	//FIND 

	@Test
	public void getComputerByIdShouldReturnComputer() {
		computer.setId((long) 574);
		Computer computerFound = computerDAO.find(computer.getId());
		assertTrue(computerFound.equals(computer));
	}

	@Test
	public void getAllComputersShouldReturn574Computers() {
		List<Computer> computers = computerDAO.findAll();
		assertEquals(computers.size(), 574);
	}

	//UPDATE 

	@Test
	public void updateComputerShouldReturnModifiedComputer() {
		computer.setId((long) 574);
		computer.setName("iPhone 4S_modified");
		computerDAO.create(computer);
		assertEquals(computer.getName(), computerDAO.find((long) 574).getName());
		computer.setName("iPhone 4S");
		computerDAO.create(computer);
	}

	//DELETE 

	@Test
	public void deleteComputerShouldReturnFalse() {
		computerDAO.create(computer);
		long idGenerated = computer.getId();
		computerDAO.delete(idGenerated);
		//assertFalse(computerRepository.find(idGenerated));
	}

	public void deleteComputerShouldThrowExceptionBecauseIdisInvalid() {
		computerDAO.delete(-1L);
	}

}
