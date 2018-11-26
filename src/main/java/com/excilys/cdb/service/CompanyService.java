package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;
import com.excilys.cdb.persistence.ComputerDAO;

@Service
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

	@Transactional
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

}
