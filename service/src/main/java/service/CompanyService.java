package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import model.Company;
import dao.CompanyDAO;
import dao.ComputerDAO;
import exceptions.DataException;

@Service
@Transactional
public class CompanyService {
	
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private PlatformTransactionManager transactionManager;

	public <T> List<Company> findAll() {
		List<Company> list = new ArrayList<Company>();
		list = companyDao.findAll();	
		return list;
	}
	
	public Optional<Company> find(Long id) {
		Optional<Company> company;
		company = companyDao.find(id);
		return company;
	}

	public void delete(Long id) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				computerDao.deleteByCompany(id);
				companyDao.delete(id);
			}
		});
	}
	
	public void update(Company company) {
		companyDao.update(company);
	}
	@Transactional
	public void create(Company company) throws DataException {
		//CompanyValidator.computerValidator(company);
		companyDao.create(company);
	}

}
