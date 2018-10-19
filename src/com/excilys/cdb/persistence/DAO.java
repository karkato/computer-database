package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.cdb.model.Computer;

@SuppressWarnings("unused")
public abstract class DAO<T> {

	protected Connection connect;

	public DAO(){
		connect =DBDemo.getInstance();
	}
	 

	/**
	 * Méthode de création
	 * @param obj
	 * @return boolean 
	 */
	public abstract boolean create(T obj);

	/**
	 * Méthode pour effacer
	 * @param id
	 * @return boolean 
	 */
	public abstract boolean delete(int id);

	/**
	 * Méthode de mise à jour
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);

	/**
	 * Méthode de recherche des informations
	 * @param id
	 * @return T
	 */
	public abstract T find(Long id);

	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

