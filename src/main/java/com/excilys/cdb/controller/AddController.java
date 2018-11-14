package com.excilys.cdb.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.mapper.CompanyDTOMapper;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dto.CompanyDTO;
import com.excilys.cdb.persistence.dto.ComputerDTO;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
@Controller("addController")
public class AddController {
	Logger logger = LoggerFactory.getLogger(AddController.class);
	@Autowired
	private CompanyService companyService;
	@Autowired
	private  ComputerService computerService;
	
	private ComputerDTOMapper computerMapper=ComputerDTOMapper.getInstance();
	private CompanyDTOMapper companyMapper=CompanyDTOMapper.getInstance();
	
	@GetMapping("add")
	public String getDashboard(ModelMap model) {
		List<Company> companies = companyService.findAll();
		List<CompanyDTO> subCompaniesDTO = new ArrayList<CompanyDTO>();
		for (int i = 0; i < companies.size(); i++) {
			subCompaniesDTO.add(companyMapper.fromCompany(companies.get(i)));
		}
		model.addAttribute("companies", companies);
	return "addComputer";
	}
	
	@PostMapping("addComputer")
	public String postDeleteComputer(ModelMap model, 
			@RequestParam String computerName,
			@RequestParam String introduced,
			@RequestParam String discontinued,
			@RequestParam String companyId) {
		
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.name = computerName;
		computerDto.introduced = introduced;
		computerDto.discontinued =discontinued;
		computerDto.companyId = companyId;
	
		try {
			computerService.create(computerMapper.toComputer(computerDto));
			return "redirect:dashboard";
		} catch (DataException de) {
			model.addAttribute("internError", de.getMessage());
			return "addComputer";
		} 
	}

}
