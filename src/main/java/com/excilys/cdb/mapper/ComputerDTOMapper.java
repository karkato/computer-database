package com.excilys.cdb.mapper;


import java.sql.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.dto.ComputerDTO;

@Component
public class ComputerDTOMapper {

	private static ComputerDTOMapper mapperComputerDTO = new ComputerDTOMapper();

	private ComputerDTOMapper() {
	}

	public static ComputerDTOMapper getInstance() {
		return mapperComputerDTO;
	}

	public Computer toComputer(ComputerDTO computerDto) {
		Computer computer = new Computer();
		Company company = new Company();

		computer.setName(computerDto.name);

		if (computerDto.id != null) {
			computer.setId((long) Integer.parseInt(computerDto.id));
		}
		if (!("".equals(computerDto.introduced))) {
			computer.setIntroDate((Date.valueOf(computerDto.introduced)).toLocalDate());
		}
		if (!("".equals(computerDto.discontinued))) {
			computer.setDiscDate((Date.valueOf(computerDto.discontinued)).toLocalDate());
		}
		
		company.setId((long) Integer.parseInt(computerDto.companyId));
		company.setName(computerDto.companyName);
		computer.setCompany(company);
		return computer;

	}

	public ComputerDTO fromOptionalComputer(Optional<Computer> optional) {

		ComputerDTO computerDto = new ComputerDTO();
		

		computerDto.id=optional.get().getId()+"";
		computerDto.name=optional.get().getName();

		if (optional.get().getIntroDate() != null) {
			computerDto.introduced=optional.get().getIntroDate().toString();
		}
		if (optional.get().getDiscDate() != null) {
			computerDto.discontinued=optional.get().getDiscDate().toString();
		}

		computerDto.companyId=optional.get().getCompany().getId()+"";
		computerDto.companyName=optional.get().getCompany().getName();
		return computerDto;

	}
	
	public ComputerDTO fromComputer(Computer computer) {

		ComputerDTO computerDto = new ComputerDTO();
		

		computerDto.id=computer.getId()+"";
		computerDto.name=computer.getName();

		if (computer.getIntroDate() != null) {
			computerDto.introduced=computer.getIntroDate().toString();
		}
		if (computer.getDiscDate() != null) {
			computerDto.discontinued=computer.getDiscDate().toString();
		}

		computerDto.companyId=computer.getCompany().getId()+"";
		computerDto.companyName=computer.getCompany().getName();
		return computerDto;

	}
}


