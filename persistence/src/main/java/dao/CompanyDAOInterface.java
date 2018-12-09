package dao;

import java.util.List;

public interface CompanyDAOInterface<T> {

	abstract List<T> findAll() ;
	public abstract void delete(Long id) ;


}
