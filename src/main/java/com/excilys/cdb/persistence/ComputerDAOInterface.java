package com.excilys.cdb.persistence;

import java.util.List;
import java.util.Optional;

import com.excilys.cdb.model.Computer;

public interface ComputerDAOInterface<T> {


	public abstract void create(T obj) ;

	public abstract void delete(Long id) ;

	
	public abstract void update(T obj) ;

	
	public abstract Optional<Computer> find(Long id) ;

	
	public abstract List<T> findAll(String name, int page, int size) ;
	int count(String name) ;

	void deleteByCompany(Long companyId);

}

