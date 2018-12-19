package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dto.ComputerDTO;
import exceptions.DataException;
import mapper.ComputerDTOMapper;
import model.Computer;
import service.ComputerService;

@CrossOrigin()
@RestController("computerController")
@RequestMapping("/computer")

public class ComputerController implements WebMvcConfigurer {

	private final ComputerService computerService;
	private final ComputerDTOMapper computerMapper;

	public ComputerController(ComputerService computerService,ComputerDTOMapper computerMapper) {
		this.computerService = computerService;
		this.computerMapper = computerMapper;

	}


	@GetMapping("/{id}")
	public ResponseEntity <ComputerDTO>  find(@PathVariable("id") String id){
		ComputerDTO computerDto = computerMapper.fromOptionalComputer(computerService.find(Long.parseLong(id)));
		return new ResponseEntity<>(computerDto, HttpStatus.OK);
	}

	@GetMapping({ "/count", "/count/{name}" })
	public ResponseEntity<Long> count(@PathVariable("name") Optional<String> name) {
		long count;
		if (name.isPresent()) {
			count = computerService.count(name.get());
		} else {
			count = computerService.count("");
		}

		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping({ "/all" })
	public ResponseEntity<List<ComputerDTO>> findAll() {
		List<Computer> computerList = new ArrayList<>();
		List<ComputerDTO> subComputersDTO = new ArrayList<>();

				computerList = computerService.findAll("");

			subComputersDTO = computerList.stream().map(computerMapper::fromComputer).collect(Collectors.toList());

		if (computerList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(subComputersDTO, HttpStatus.OK);

	}
	@GetMapping({ "/all/{name}" })
	public ResponseEntity<List<ComputerDTO>> findAll(@PathVariable("name") String name) {
		List<Computer> computerList = new ArrayList<>();
		List<ComputerDTO> subComputersDTO = new ArrayList<>();
			if (name == "") {
				computerList = computerService.findAll("");
			} else {
				computerList = computerService.findAll(name);
			}
			subComputersDTO = computerList.stream().map(computerMapper::fromComputer).collect(Collectors.toList());

		if (computerList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(subComputersDTO, HttpStatus.OK);

	}

	@PostMapping()
	public ResponseEntity<ComputerDTO> create(@RequestBody ComputerDTO computerDto) {
		try {
			computerService.create(computerMapper.toComputer(computerDto));
		} catch (DataException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ComputerDTO>(computerDto, HttpStatus.CREATED);

	}
	@PatchMapping()
	public ResponseEntity<ComputerDTO> update(@RequestBody ComputerDTO computerDto) {
		try {
			computerService.update(computerMapper.toComputer(computerDto));
		} catch (DataException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ComputerDTO>(computerDto, HttpStatus.OK);

	}
	@DeleteMapping("/{idTab}")
	public ResponseEntity<Void> delete(@PathVariable String idTab) {
		computerService.delete(Long.parseLong(idTab));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}




