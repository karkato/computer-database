package mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import model.Company;
import dto.CompanyDTO;

@Component
public class CompanyDTOMapper {

	private static CompanyDTOMapper mapperCompanyDTO = new CompanyDTOMapper();

	private CompanyDTOMapper() {
	}

	public static CompanyDTOMapper getInstance() {
		return mapperCompanyDTO;
	}

	public Company toCompany(CompanyDTO companyDto) {
		Company company = new Company();
		company.setName(companyDto.getName());
		if (!(companyDto.getId()== null)) {
		company.setId(Long.parseLong(companyDto.getId()));
		}
		return company;
	}

	public CompanyDTO fromCompany(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.id = company.getId() + "";
		companyDto.name = company.getName();
		return companyDto;

	}
	
	public CompanyDTO fromOptionalCompany(Optional<Company> optional) {

		CompanyDTO companyDto = new CompanyDTO();
		

		companyDto.id=optional.get().getId()+"";
		companyDto.name=optional.get().getName();

		return companyDto;

	}

}
