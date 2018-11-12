import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

public class CompanyDAOTest {

	@Autowired
	private CompanyService companyService;

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
		companyService=null;
	}

	@Test
	public void testNotNullFindAllCompanies() {
		try {
			assertNotNull(companyService.findAll());	
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}

	@Test
	public void testEqualsCompanyFindAllCompanies() {
		try {
			List<Company> result = companyService.findAll();
			Company company = new Company();
			for(int i=0;i<result.size();i++) {
				assertEquals(company.getClass(),result.get(i).getClass());
			}
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}

	@Test
	@Disabled
	public void testDeleteCompany() {
		try {
			companyService.delete((long) 58);
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}


}
