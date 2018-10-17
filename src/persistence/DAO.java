package persistence;

import java.sql.Connection;
import java.util.List;

import model.Computers;

@SuppressWarnings("unused")
public abstract class DAO<T> {

	protected Connection connect = null;

	public DAO(Connection conn){
		this.connect = conn;
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
	public abstract T find(int id);

	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

