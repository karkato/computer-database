import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;

public class CompanyDAOTest {
	
private CompanyDAO companyDao;
	
	@BeforeEach
	public void setUp() {
		companyDao = CompanyDAO.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
		companyDao=null;
	}
	
	@Test
	public void testNotNullFindAllCompanies() {
		try {
			assertNotNull(companyDao.findAll());	
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}
	
	@Test
	public void testEqualsCompanyFindAllCompanies() {
		try {
			List<Company> result = companyDao.findAll();
			Company company = new Company();
			for(int i=0;i<result.size();i++) {
				assertEquals(company.getClass(),result.get(i).getClass());
			}
		}catch(Exception e) {
			fail("Exception inattendue");
		}		
	}


}
