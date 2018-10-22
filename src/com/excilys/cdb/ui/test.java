package com.excilys.cdb.ui;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.DAO;
import com.excilys.cdb.persistence.DAOFactory;

public class test {


	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {



		Page pages = new Page();
		DAO<Company> companydao = DAOFactory.getCompanyDAO();
		DAO<Computer> computerdao = DAOFactory.getComputerDAO();
		System.out.println("Bienvenue sur l'application CDB : \n");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		int index = 0;
		while (index != 7) {
			System.out.println("Bonjour, veuillez taper l'option souhaitée : \n");
			System.out.println("\t 1 - Afficher liste des ordinateurs");
			System.out.println("\t 2 - Afficher liste des entreprises");
			System.out.println("\t 3 - Afficher les détails d'un ordinateur");
			System.out.println("\t 4 - Ajouter un nouvel ordinateur");
			System.out.println("\t 5 - M-a-J un ordinateur");
			System.out.println("\t 6 - Supprimer un ordinateur");
			System.out.println("\t 7 - Quitter l'application");

			index = sc.nextInt();
			sc.nextLine();
			switch(index) {
			case 1:
				System.out.println("Combien d'elements par page ? ");
				int nbelt= sc.nextInt();
				sc.nextLine();
				int i=1;
				int k=0;
				List<Computer> computers = computerdao.findAll();
				pages.setPage(i);
				pages.setPageSize(nbelt);
				List<Computer> computerPage = pages.getPage(computers);
				while (k < computerPage.size()) {
					System.out.println(computerPage.get(k));
					System.out.println("-------------------------------------------");
					k++;
				}
				System.out.println("\n\t****************************************");

				System.out.println("Pour aller à la page suivante taper  '2' et pour la page précédente taper '1'. taper '0' pour arrêter ! ");
				int pagex= sc.nextInt();
				sc.nextLine();
				while(pagex !=0) {
					if(pagex == 2) {
						pages.setPage(i+=1);
						pages.setPageSize(nbelt);
						List<Computer>computerPage1=pages.getPage(computers);
						int x=0;
						while (x < computerPage1.size()) {
							System.out.println(computerPage1.get(x));
							System.out.println("-------------------------------------------");
							x++;
						}
						System.out.println("\n\t < previous(1)*******************> quit(0) <********************* next(2) >");
						pagex= sc.nextInt();
						sc.nextLine();
					}else if ((pagex == 1) & (i > 0)) {
						pages.setPage(i-=1);
						pages.setPageSize(nbelt);
						List<Computer>computerPage2 = pages.getPage(computers);
						int y=0;
						while (y < computerPage2.size()) {
							System.out.println(computerPage2.get(y));
							System.out.println("-------------------------------------------");
							y++;
						}
						System.out.println("\n\t < previous(1)*******************> quit(0) <********************* next(2) >");
						pagex= sc.nextInt();
						sc.nextLine();
					}
				}
				break;
			case 2:
				System.out.println("Combien d'elements par page ? ");
				int nbelts= sc.nextInt();
				sc.nextLine();
				int j=1;
				List<Company> companies = companydao.findAll();
				pages.setPage(1);
				pages.setPageSize(nbelts);
				List<Company> companyPage = pages.getPage(companies);
				System.out.println(companyPage);
				int e=0;
				while (e< companyPage.size()) {
					System.out.println(companyPage.get(e));
					System.out.println("-------------------------------------------");
					e++;
				}
				System.out.println("\n\t****************************************");

				System.out.println("Pour aller à la page suivante taper  '2' et pour la page précédente taper '1'. taper '0' pour arrêter ! ");
				int pagey= sc.nextInt();
				sc.nextLine();
				while(pagey !=0) {
					if(pagey == 2) {
						int r=0;
						pages.setPage(j+=1);
						pages.setPageSize(nbelts);
						List<Company> companypage1 = pages.getPage(companies);
						while (r<companypage1.size()) {
							System.out.println(companypage1.get(r));
							System.out.println("-------------------------------------------");
							r++;
						}
						System.out.println("\n\t < previous(1)*******************> quit(0) <********************* next(2) >");
						pagey= sc.nextInt();
						sc.nextLine();
					} else if ( pagey == 1 & j > 0){
						
						int t=0;
						pages.setPage(j-=1);
						pages.setPageSize(nbelts);
						List<Company> companypage2 = pages.getPage(companies);
						while (t<companypage2.size()) {
							System.out.println(companypage2.get(t));
							System.out.println("-------------------------------------------");
							t++;
						}
						System.out.println("\n\t < previous(1)*******************> quit(0) <********************* next(2) >");
						pagey= sc.nextInt();
						sc.nextLine();
					}
				}
				break;
			case 3:
				System.out.println("Veuillez introduire l'identifiant du pc souhaité : ");
				Long id = sc.nextLong();
				sc.nextLine();
					System.out.println(computerdao.find(id).toString());
				System.out.println("\n\t****************************************");
				System.out.println();
				break;
			case 4:
				Computer objCreated = new Computer();
				System.out.println("Nom du pc :");
				String nameCreated = sc.nextLine();
				objCreated.setName(nameCreated);
				System.out.println("Date d'introduction du pc :");
				String introDateCreated = sc.nextLine();
				System.out.println("Date of discontinuation :");
				String discDateCreated = sc.nextLine();
				//Date d'introduction
				if(!introDateCreated.equals("")) {
					Date date = Date.valueOf(introDateCreated);
					objCreated.setIntroDate(date.toLocalDate());
				}
				if(!discDateCreated.equals("")) {
					Date date = Date.valueOf(discDateCreated);
					objCreated.setDiscDate(date.toLocalDate());
				}
				System.out.println("Computer's company !");
				String manuf=sc.nextLine();
				sc.nextLine();
				
				Company comp = new Company();
				comp.setName(manuf);
				objCreated.setCompany(comp);
				computerdao.create(objCreated);
				System.out.println("Votre pc est crée !");
				break;

			case 5:
				Computer objUpdated = new Computer();
				System.out.println("Nom du pc :");
				String nameUpdated = sc.nextLine();
				objUpdated.setName(nameUpdated);
				System.out.println("Date d'introduction du pc :");
				String introDateUpdated = sc.nextLine();
				System.out.println("Date of discontinuation :");
				String discDateUpdated = sc.nextLine();
				//Date d'introduction
				if(!introDateUpdated.equals("")) {
					Date date = Date.valueOf(introDateUpdated);
					objUpdated.setIntroDate(date.toLocalDate());
				}
				if(!discDateUpdated.equals("")) {
					Date date = Date.valueOf(discDateUpdated);
					objUpdated.setDiscDate(date.toLocalDate());
				}
				System.out.println("Computer's Company ?");
				String manufUpds=sc.nextLine();
				sc.nextLine();
				Company cpmUpd = new Company();
				cpmUpd.setName(manufUpds);
				objUpdated.setCompany(cpmUpd);
				computerdao.update(objUpdated);
				System.out.println("Votre pc est mise à jour");
				break;
			case 6:
				System.out.println("Veuillez donner l'identifiant du pc a supprimer");
				int idSupp= sc.nextInt();
				if(computerdao.delete(idSupp)) {
					System.out.println("Pc supprimé");
				}else {
					System.out.println("Erreur lors de la suppression");
				}
				break;
			case 7: 
				System.out.println("Bye bye !");
				sc.close();
			default: break;
			}
		}
	}


}
