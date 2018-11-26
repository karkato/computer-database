package com.excilys.cdb.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.mapper.CompanyDTOMapper;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dto.CompanyDTO;
import com.excilys.cdb.persistence.dto.ComputerDTO;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("addComputer")
public class AddController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private  ComputerService computerService;
	private ComputerDTOMapper computerMapper=ComputerDTOMapper.getInstance();
	private CompanyDTOMapper companyMapper=CompanyDTOMapper.getInstance();

	@GetMapping
	public String getDashboard(ModelMap model) {
		List<Company> companies = companyService.findAll();
		List<CompanyDTO> subCompaniesDTO = new ArrayList<CompanyDTO>();
		subCompaniesDTO.clear();	
		subCompaniesDTO = companies.stream().map(temp -> {
			CompanyDTO obj = companyMapper.fromCompany(temp);
			return obj;
		}).collect(Collectors.toList());
		model.addAttribute("companies", companies);
		model.addAttribute("computerDTO", new ComputerDTO());
		return "addComputer";
	}

	@PostMapping
	public String postCreateComputer(ModelMap model, 
			@Validated @ModelAttribute("computerDTO") ComputerDTO computerDto,BindingResult result) {

		if(result.hasErrors()) {
			return "500";
		}

		try {
			computerService.create(computerMapper.toComputer(computerDto));
			return "redirect:dashboard";
		} catch (DataException de) {
			model.addAttribute("internError", de.getMessage());
			return "addComputer";
		}
	}

}
