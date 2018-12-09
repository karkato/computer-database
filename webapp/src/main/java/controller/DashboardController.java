package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exceptions.NoNextPageException;
import exceptions.NoPreviousPageException;
import mapper.ComputerDTOMapper;
import model.Computer;
import model.Page;
import dto.ComputerDTO;
import service.ComputerService;

@Controller
public class DashboardController {

	@Autowired
	private ComputerService computerService;
	private ComputerDTOMapper computerMapper = ComputerDTOMapper.getInstance();

	@GetMapping("dashboard")
	public String getDashboard(ModelMap model, @RequestParam(required = false, defaultValue = "") String search,
			@RequestParam(required = false, defaultValue = "1") String page,
			@RequestParam(required = false, defaultValue = "10") String size) {
		List<Computer> computers;
		List<ComputerDTO> subComputersDTO = new ArrayList<ComputerDTO>();
		int counter = 0;
		try {
			Page.setPage(page, size);

			if (search == null) {
				computers = computerService.findAll("");
				counter = computerService.count("");
			} else {
				model.addAttribute("search", search);
				computers = computerService.findAll(search);
				counter = computerService.count(search);
			}
			subComputersDTO.clear();	
			subComputersDTO = computers.stream().map(temp -> {
				ComputerDTO obj = computerMapper.fromComputer(temp);
				return obj;
			}).collect(Collectors.toList());

		} catch (NoPreviousPageException nppe) {
			Page.increasePage();
		} catch (NoNextPageException nnpe) {
			Page.decreasePage();
		}
		model.addAttribute("counter", counter);
		model.addAttribute("pageIndex", Page.getPage());
		model.addAttribute("pageSize", Page.getPageSize());
		model.addAttribute("computers", subComputersDTO);
		return "dashboard";
	}

	@PostMapping("dashboard")
	public String postDeleteComputer(ModelMap model, 
			@RequestParam String[] selection) {

		String[] idTab = selection[0].split(",");
		computerService.deleteAll(idTab);	

		return "redirect:dashboard";
	}
	
	@GetMapping("/")
    public String index(Model model, Principal principal) {
        return "redirect:dashboard";
    }
	
	@GetMapping("login")
	public String login(ModelMap model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		if (error != null) {
			model.addAttribute("msg", "Invalid username and password!");
		}

		else if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		return "login";

	}

}