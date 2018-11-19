package com.excilys.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.mapper.CompanyDTOMapper;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dto.CompanyDTO;
import com.excilys.cdb.persistence.dto.ComputerDTO;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("editComputer")
public class EditController {
	Logger logger = LoggerFactory.getLogger(EditController.class);
	@Autowired
	private CompanyService companyService;
	@Autowired
	private  ComputerService computerService;

	private ComputerDTOMapper computerMapper=ComputerDTOMapper.getInstance();
	private CompanyDTOMapper companyMapper=CompanyDTOMapper.getInstance();

	@GetMapping
	public String getDashboard(ModelMap model,@RequestParam String computerId) {
		ComputerDTO computerDto = computerMapper.fromOptionalComputer(computerService.find(Long.parseLong(computerId)));

		model.addAttribute("computerId", computerDto.id);
		model.addAttribute("computerName", computerDto.name);
		model.addAttribute("introduced", computerDto.introduced);
		model.addAttribute("discontinued", computerDto.discontinued);
		model.addAttribute("companyId", computerDto.companyId);

		List<Company> companies = companyService.findAll();
		List<CompanyDTO> subCompaniesDTO = new ArrayList<CompanyDTO>();
		for (int i = 0; i < companies.size(); i++) {
			subCompaniesDTO.add(companyMapper.fromCompany(companies.get(i)));
		}
		model.addAttribute("companies", companies);
		model.addAttribute("computerDTO", new ComputerDTO());

		return "editComputer";
	}

	@PostMapping
	public String postUpdateComputer(ModelMap model, 
			@Validated @ModelAttribute("computerDTO") ComputerDTO computerDto,BindingResult result) {


		if(result.hasErrors()) {

			return "500";
		}

		try {

			computerService.update(computerMapper.toComputer(computerDto));

			return "redirect:dashboard";
		} catch (DataException de) {
			model.addAttribute("internError", de.getMessage());
			return "editComputer";
		}
	}
}