package mapper;


import java.sql.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import model.Company;
import model.Computer;

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

		computer.setName(computerDto.getName());

		if (computerDto.getId() != null) {
			computer.setId(Long.parseLong(computerDto.getId()));
		}
		if (computerDto.introduced != null && !("".equals(computerDto.introduced))) {
			computer.setIntroDate(Date.valueOf(computerDto.introduced).toLocalDate());
		}
		if (computerDto.discontinued != null && !("".equals(computerDto.discontinued))) {
			computer.setDiscDate(Date.valueOf(computerDto.discontinued).toLocalDate());
		}
		if(computerDto.getCompanyId() != null) {
			company.setId(Long.parseLong(computerDto.getCompanyId()));
		}	
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


