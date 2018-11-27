package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Company;
import service.CompanyService;

@RequestMapping("/company")
public class CompanyController {
	
	
private final CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping(value = "")
	public ResponseEntity<List<Company>> findAll() {
		List<Company> companyList = new ArrayList<Company>();
		companyList = companyService.findAll();
		return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
	}

	@DeleteMapping(value = "")
	public ResponseEntity<Void> delete(long id) {
		companyService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.GONE);
	}

}