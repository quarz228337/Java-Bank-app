import java.util.Scanner;

public class Menu {
	
	//Instance Vars
	
	Scanner scanner = new Scanner(System.in);
	Bank bank = new Bank();
	boolean exit;
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.runMenu();
		

	}

	public void runMenu() {
		printHeader();
		while(!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice);
		}
	}
	
	private void printHeader() {
		System.out.println("<------------------------------->");
		System.out.println("|         Eugene's bank         |");
		System.out.println("|            Welcome!           |");
		System.out.println("<------------------------------->");
		
	}
	
	private void printMenu() {
		
		System.out.println("Selections: ");
		System.out.println("1. Create a new account.");
		System.out.println("2. Make a deposit.");
		System.out.println("3. Make a withdraw.");
		System.out.println("4. List account balance.");
		System.out.println("5. Quit. ");
		
		
	}

	private void performAction(int choice) {
		switch(choice) {
			case 0:
				System.out.println("Thank you for using my bank!");
				System.exit(0);
				break;
			case 1:
				createAnAccount();
				break;
			case 2:
				makeA_Deposit();
				break; 
			case 3:
				makeA_Withdraw();
			break;
			case 4:
				listBalance();
				break;
			default:
				System.out.println("Unknown error has occured.");
		}
		
	}

	private void listBalance() {
		// TODO Auto-generated method stub
		
	}

	private void createAnAccount() {
		String firstName, lastName, ssn, accountType = "";
		double initialDeposit = 0;

		
		while(true) {
			System.out.print("Enter the type of the account (Checking / Savings): ");
			accountType = scanner.nextLine().toLowerCase();
			
			if(accountType.equals("checking") || accountType.equals("savings")) {
				break;
			}
			else {
				System.out.println("Something went wrong. Try again.");
				continue;
				
			}
		}
		System.out.print("Print your first name: ");
		firstName = scanner.nextLine();
		
		System.out.print("Print your last name: ");
		lastName = scanner.nextLine();
		
		System.out.print("Enter your social security number: ");
		ssn = scanner.nextLine();
		
		
		// Initial deposit
		
		while(true) {
			
			try {
			System.out.println("Enter an initial deposit");
			initialDeposit = Double.parseDouble(scanner.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Deposit must be a number.");
			}
			
			
			// Checking if the user deposited enough money for each type of account
			
			if (accountType.equals("checking")) {
				if(initialDeposit < 100) {
					System.out.println("Minimal deposit to open a CHECKING account is 100$.");
				} else {
					break;
				}
					
				
			} else if (accountType.equals("savings")) {
					if(initialDeposit < 50) {
						System.out.println("Minimal deposit to open a SAVINGS account is 50$.");
				}	else {
					break;
				}
			}
		}
		
		
		// we can create an account now
		Account account;
		if(accountType.equals("checking")) {
			account = new Checking(initialDeposit);
		} else {
			account = new Savings(initialDeposit);
		}
		
		Customer customer = new Customer(firstName, lastName, ssn, account);
		bank.addCustomer(customer);
	}

	private void makeA_Withdraw() {
		// TODO Auto-generated method stub
		
	}

	private void makeA_Deposit() {
		// TODO Auto-generated method stub
		
	}

	private int getInput() {
		
		int choice = -1;
		
	do {
		System.out.print("Your choice is: ");
		try {
			choice = Integer.parseInt(scanner.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Invalid selection. Try again.");
			
		}
		if(choice < 0 || choice > 4) {
			System.out.println("Choice is out of range. Chose again.");
		}
   }while(choice < 0 || choice > 4) 	;
		return choice;
}

	
}
