package mapper;

import org.springframework.stereotype.Component;

import model.Company;
import dto.CompanyDTO;;

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
		company.setId((long) Integer.parseInt(companyDto.id));
		company.setName(companyDto.name);
		return company;
	}

	public CompanyDTO fromCompany(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.id = company.getId() + "";
		companyDto.name = company.getName();
		return companyDto;

	}

}
