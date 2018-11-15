package com.excilys.cdb.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDAOInterface<T> {

	abstract List<T> findAll() ;
	public abstract void delete(Long id) ;


}
