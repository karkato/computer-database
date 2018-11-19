package com.excilys.cdb.ui;

import java.io.IOException;
import java.sql.SQLException;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.PageNumberException;

public class MainTest {


	/**
	 * Connect to the DB and do some stuff
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws DataBaseException 
	 * @throws PageNumberException 
	 */
	

	public static void main(String[] args) throws IOException, DataBaseException, SQLException, PageNumberException {

		
		/*
		ComputerService cpuService;
		CompanyService cpaService;

		List<Computer> computers;
		List<Company> companies;
		System.out.println("Bienvenue sur l'application CDB : \n");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		int index = 0;
		while (index != 8) {
			System.out.println("Bonjour, veuillez taper l'option souhaitée : \n");
			System.out.println("\t 1 - Afficher liste des ordinateurs");
			System.out.println("\t 2 - Afficher liste des entreprises");
			System.out.println("\t 3 - Afficher les détails d'un ordinateur");
			System.out.println("\t 4 - Ajouter un nouvel ordinateur");
			System.out.println("\t 5 - M-a-J un ordinateur");
			System.out.println("\t 6 - Supprimer un ordinateur");
			System.out.println("\t 7 - Supprimer une companie");
			System.out.println("\t 8 - Quitter l'application");
			index = sc.nextInt();

			sc.nextLine();
			switch(index) {
			case 1:
				sc.nextLine();
				computers = cpuService.findAll("");
				System.out.println(computers);
				System.out.println("\n\t****************************************");

				System.out.println("Pour aller à la page suivante taper  '2' et pour la page précédente taper '1'. taper '0' pour arrêter ! ");
				sc.nextLine();
		
				break;
			case 2:
				sc.nextLine();
				companies = cpaService.findAll();
				System.out.println(companies);
				System.out.println("\n\t****************************************");

				System.out.println("Pour aller à la page suivante taper  '2' et pour la page précédente taper '1'. taper '0' pour arrêter ! ");
				sc.nextLine();
				break;
			case 3:
				System.out.println("Veuillez introduire l'identifiant du pc souhaité : ");
				Long id = sc.nextLong();
				sc.nextLine();
				System.out.println(cpuService.find(id).toString());
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
				try {
					cpuService.create(objCreated);
				} catch (NameException e1) {
					e1.printStackTrace();
				} catch (DataException e1) {
					e1.printStackTrace();
				} catch (DataBaseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
				try {
					cpuService.update(objUpdated);
				} catch (NameException e) {
					e.printStackTrace();
				} catch (DataException e) {
					e.printStackTrace();
				}
				System.out.println("Votre pc est mise à jour");
				break;
			case 6:
				System.out.println("Veuillez donner l'identifiant du pc a supprimer");
				int idSupp= sc.nextInt();
				cpuService.delete((long) idSupp);
				break;
			case 7:
				System.out.println("Suppression d'une compagnie");
				int idSuppC= sc.nextInt();
				cpaService.delete((long) idSuppC);
				break;
			case 8: 
				System.out.println("Bye bye !");
				sc.close();
			default: break;
			}
		}*/
	}


}
