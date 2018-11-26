package com.excilys.cdb.persistence;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAO implements CompanyDAOInterface<Company>{

	@PersistenceContext
	private EntityManager em;

	private CriteriaBuilder cb;


	@PostConstruct
	public void init() {
		this.cb=em.getCriteriaBuilder();
	}



	@Override
	public List<Company> findAll() {

		List<Company> companies = new ArrayList<Company>();
		CriteriaQuery<Company> criteriaQuery = cb.createQuery(Company.class);
		criteriaQuery.from(Company.class);
		companies=em.createQuery(criteriaQuery).getResultList();		
		return companies;

	}

	@Override
	public void delete(Long id) {

		CriteriaDelete<Company> criteriaQuery = cb.createCriteriaDelete(Company.class);
		Root<Company> model = criteriaQuery.from(Company.class);
		criteriaQuery.where(cb.equal(model.get("id"), id));
		em.createQuery(criteriaQuery).executeUpdate();


	}
}
