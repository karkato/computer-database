package model;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import persistence.DAO;
import persistence.DAOFactory;

public class test {


	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {



		Page pages = new Page();
		DAO<Companies> companydao = DAOFactory.getCompanyDAO();
		DAO<Computers> computerdao = DAOFactory.getComputerDAO();
		//1List<Companies> companyPage = pages.getPage(companies,1,10);


		/*for(int i=1;i<10;i++) {
			Computers computer = computerdao.find(i);
			System.out.println("\tComputer N:"+ computer.getIdPc()+ "-Name :"+computer.getNamePc());
		}

		System.out.println("\tComputer updated N:"+ computerdao.find(5).getIdPc()+ " | Name :"+computerdao.find(5).getNamePc()+ " | Date :"+computerdao.find(5).getIntroDate());


		System.out.println("\n\t****************************************");

		DAO<Companies> companydao = DAOFactory.getCompanyDAO();


		Companies company = companydao.find(23);//Integer.parseInt(args[0]));
		System.out.println("\tCompany of "+company.getNameComp());


		System.out.println("\n\t****************************************");

		//DAO<Computers> computerdao = DAOFactory.getComputerDAO();

		//System.out.print("\tCompany list : ");
		List<Companies> companies = companydao.findAll();
		List<Computers> computers = computerdao.findAll();
		Page pages = new Page();
		List<Companies> keks = pages.getPage(companies,1,10);
		List<Computers> keks1 = pages.getPage(computers,1,50);
		for(Companies pc : companies) {
		System.out.println("\t"+pc);
		}
		System.out.print("\tCompany list page N: 1 ");
		System.out.println(keks);
		System.out.print("\tComputer list page N: 1 ");
		System.out.println(keks1);*/
		System.out.println("Hello");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int keks = 0;
		while (keks != 7) {

			System.out.println("Bonjour, veuillez taper l'option souhaitée :");
			System.out.println("\t1 - Afficher liste des ordinateurs");
			System.out.println("\t2 - Afficher liste des entreprises");
			System.out.println("\t3 - Afficher les détails d'un ordinateur");
			System.out.println("\t4 - Ajouter un nouvel ordinateur");
			System.out.println("\t5 - M-a-J un ordinateur");
			System.out.println("\t6 - Supprimer un ordinateur");
			System.out.println("\t7 - Quitter l'application");

			keks = sc.nextInt();
			sc.nextLine();
			switch(keks) {
			case 1:
				System.out.println("Combien d'elements par page ? ");
				int nbelt= sc.nextInt();
				sc.nextLine();
				int i=1;
				int k=0;
				List<Computers> computers = computerdao.findAll();
				List<Computers> computerPage = pages.getPage(computers,i,nbelt);
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
						List<Computers>computerPage1=pages.getPage(computers,i+=1,nbelt);
						int x=0;
						while (x < computerPage1.size()) {
							System.out.println(computerPage1.get(x));
							System.out.println("-------------------------------------------");
							x++;
						}
						System.out.println("\n\t < previous(1)**************************************** next(2) >");
						pagex= sc.nextInt();
						sc.nextLine();
					}else if ((pagex == 1) & (i > 0)) {

						List<Computers>computerPage2 = pages.getPage(computers,i-=1,nbelt);
						int y=0;
						while (y < computerPage2.size()) {
							System.out.println(computerPage2.get(y));
							System.out.println("-------------------------------------------");
							y++;
						}
						System.out.println("\n\t < previous(1)**************************************** next(2) >");
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
				List<Companies> companies = companydao.findAll();
				List<Companies> companyPage = pages.getPage(companies,1,nbelts);
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
						List<Companies> companypage1 = pages.getPage(companies,j+=1,nbelts);
						while (r<companypage1.size()) {
						System.out.println(companypage1.get(r));
						System.out.println("-------------------------------------------");
						r++;
						}
						System.out.println("\n\t < previous(1)**************************************** next(2) >");
						pagey= sc.nextInt();
						sc.nextLine();
					}else if ( pagey == 1 & j > 0) {

						int t=0;
						List<Companies> companypage2 = pages.getPage(companies,j-=1,nbelts);
						while (t<companypage2.size()) {
							System.out.println(companypage2.get(t));
							System.out.println("-------------------------------------------");
							t++;
						}
						System.out.println("\n\t < previous(1)**************************************** next(2) >");
						pagey= sc.nextInt();
						sc.nextLine();
					}
				} 
				break;
			case 3:
				System.out.println("Veuillez introduire l'identifiant du pc souhaité : ");
				int id = sc.nextInt();
				sc.nextLine();
				if(computerdao.find(id).getManufacturer()== 0 ||computerdao.find(id).getIntroDate()== null ||computerdao.find(id).getDiscDate()== null ) {
					System.out.println(computerdao.find(id).toString());
					System.out.println("Introduced, Discontinued or Company is empty !");
				}else {
					System.out.println( computerdao.find(id).information());
				}
				System.out.println("\n\t****************************************");
				System.out.println();
				break;
			case 4:
				Computers objCreated = new Computers();
				System.out.println("Nom du pc :");
				String nameCreated = sc.nextLine();
				objCreated.setNamePc(nameCreated);
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
				int manuf=sc.nextInt();
				sc.nextLine();

				objCreated.setManufacturer(manuf);
				computerdao.create(objCreated);
				System.out.println("Votre pc est crée !");
				break;

			case 5:
				Computers objUpdated = new Computers();
				System.out.println("Nom du pc :");
				String nameUpdated = sc.nextLine();
				objUpdated.setNamePc(nameUpdated);
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
				int manufUpd=sc.nextInt();
				sc.nextLine();
				objUpdated.setManufacturer(manufUpd);
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

			default: break;
			}
		}
	}


}
