package com.excilys.cdb.persistence;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import com.excilys.cdb.model.Computer;


@Repository

public class ComputerDAO implements ComputerDAOInterface<Computer> {

	@PersistenceContext
	private EntityManager em;
	private CriteriaBuilder cb;

	@PostConstruct
	public void init() {
		this.cb=em.getCriteriaBuilder();
	}


	@Override

	public void create(Computer computer) {
        em.persist(computer);
	}

	@Override
	public void update(Computer computer){
		CriteriaUpdate<Computer> update = cb.createCriteriaUpdate(Computer.class);
		Root<Computer> model = update.from(Computer.class);
		update.set("name", computer.getName());
		update.set("introduced", computer.getIntroDate());
		update.set("discontinued", computer.getDiscDate());
		if (computer.getCompany() != null) {
			update.set("company", computer.getCompany());
		}
		update.where(cb.equal(model.get("id"), computer.getId()));
		em.createQuery(update).executeUpdate();

	}

	public void delete(Long id){
		CriteriaDelete<Computer> criteriaQuery = cb.createCriteriaDelete(Computer.class);
		Root<Computer> model = criteriaQuery.from(Computer.class);
		criteriaQuery.where(cb.equal(model.get("company_id"), id));
		em.createQuery(criteriaQuery).executeUpdate();
	}

	public Optional<Computer> find(Long id){
		Computer computer;
		CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		Root<Computer> model = criteriaQuery.from(Computer.class);
		criteriaQuery.where(cb.equal(model.get("id"), id));
		TypedQuery<Computer> query = em.createQuery(criteriaQuery);
		computer = query.getSingleResult();
		return Optional.ofNullable(computer);
	}

	@Override
	public List<Computer> findAll(String name, int page, int size) {


		CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		Root<Computer> model = criteriaQuery.from(Computer.class);
		criteriaQuery.where(cb.like(model.get("name"), "%" + name + "%"));
		TypedQuery<Computer> query = em.createQuery(criteriaQuery)
				.setFirstResult((page - 1) * size)
				.setMaxResults(size);
		return query.getResultList();
	}

	@Override
	public long count(String name) {

		CriteriaQuery<Long> criteriaQuery2 = cb.createQuery(Long.class);
		Root<Computer> model = criteriaQuery2.from(Computer.class);
		criteriaQuery2.select(cb.count(model));
		criteriaQuery2.where(cb.like(model.get("name"), "%" + name + "%"));
		TypedQuery<Long> query2 = em.createQuery(criteriaQuery2);
		return query2.getSingleResult();
	}

	@Override
	public void deleteByCompany(Long companyId) {
		CriteriaDelete<Computer> criteriaQuery = cb.createCriteriaDelete(Computer.class);
		Root<Computer> model = criteriaQuery.from(Computer.class);
		criteriaQuery.where(cb.equal(model.get("company_id"), companyId));
		em.createQuery(criteriaQuery).executeUpdate();
	}
}
