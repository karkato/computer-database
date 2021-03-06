package rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CompanyDTO;
import mapper.CompanyDTOMapper;
import model.Company;
import service.CompanyService;

@RestController("companyController")
@RequestMapping("/company")
public class CompanyController {

	private final CompanyService companyService;
	private final CompanyDTOMapper companyMapper;
	
	public CompanyController(CompanyService companyService, CompanyDTOMapper companyMapper) {
		this.companyService = companyService;
		this.companyMapper = companyMapper;
	}

	@GetMapping("/all")
	public ResponseEntity<List<CompanyDTO>> findAll() {
		List<Company> companyList;	
		companyList = companyService.findAll();
		List<CompanyDTO> subCompaniesDTO = companyList.stream().map(
				companyMapper::fromCompany
			).collect(Collectors.toList());	
		return new ResponseEntity<>(subCompaniesDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		companyService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}