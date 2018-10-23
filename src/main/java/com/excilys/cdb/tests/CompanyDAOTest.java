package com.excilys.cdb.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;

public class CompanyDAOTest {
	
	@Autowired
	private CompanyDAO companyRepository;

	private Company company;



	@Before
	public void beforeEachTest() {

		company = new Company();
		company.setId((long) 1);
		company.setName("Apple Inc.");


	}

	/**
	 */
	@After
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


	//@Test(expected=DaoException.class)
	@Transactional
	public void deleteCompanyShouldThrowExceptionBecauseIdisInvalid() {
		companyRepository.delete(-1L);
	}


}
