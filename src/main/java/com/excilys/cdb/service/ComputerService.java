package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.exceptions.NoNextPageException;
import com.excilys.cdb.exceptions.NoPreviousPageException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.validator.ComputerValidator;
import com.excilys.cdb.validator.PageValidator;

@Service

public class ComputerService {


	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private PlatformTransactionManager transactionManager;

	public Optional<Computer> find(Long id) {
		Optional<Computer> computer;
		computer = computerDao.find(id);
		return computer;
	}
	@Transactional
	public void create(Computer computer) throws DataException {
		ComputerValidator.computerValidator(computer);
		computerDao.create(computer);
	}
	@Transactional
	public void update(Computer computer) throws DataException {
		ComputerValidator.computerValidator(computer);
		computerDao.update(computer);
	}
	@Transactional
	public void delete(Long id) {
		computerDao.delete(id);
	}

	@Transactional
	public void deleteAll(String[] idTab) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				try {
					for (int i = 0; i < idTab.length; i++) {
						if (!("".equals(idTab[i])))
							delete(Long.parseLong(idTab[i]));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public <T> List<Computer> findAll(String name) throws NoPreviousPageException, NoNextPageException {
		PageValidator.previousPageValidator();
		List<Computer> computerList = new ArrayList<Computer>();
		computerList = computerDao.findAll(name, Page.getPage(), Page.getPageSize());
		PageValidator.nextPageValidator(computerList);
		return computerList;
	}

	public long count(String name) {
		long count = 0;
		count = computerDao.count(name);
		return count;
	}

}
