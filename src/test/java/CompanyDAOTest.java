import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;

public class CompanyDAOTest {
	
	private CompanyDAO companyRepository;

	private Company company;



	@BeforeEach
	public void beforeEachTest() {

		company = new Company();
		company.setId((long) 1);
		company.setName("Apple Inc.");


	}

	@AfterEach
	public void afterEachTest() {

		company = null;
	}


	//FIND 
	@Test
	public void getAllCompaniesShouldReturn42Companies() {
		List<Company> companies = companyRepository.findAll();
		assertEquals(companies.size(), 42);
	}


	@Test
	public void getCompanyByIdShouldReturnCompany() {
		Company companyFound = companyRepository.find(1L);
		assertTrue(companyFound.equals(company));
	}

	//DELETE 
	public void deleteCompanyShouldThrowExceptionBecauseIdisInvalid() {
		companyRepository.delete(-1L);
	}


}
