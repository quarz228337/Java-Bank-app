import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
	Scanner scanner = new Scanner(System.in);
	Bank bank = new Bank();
	boolean exit;
	boolean valid = true;

	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.runMenu();

	}

	public void runMenu() {
		printHeader();
		while (!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice);
		}
	}

	private void printHeader() {
		System.out.println("<------------------------------->");
		System.out.println("|         Eugene's bank.        |");
		System.out.println("|            Welcome!           |");
		System.out.println("<------------------------------->");

	}
	
	private void displayHeader(String message) {
		int width = message.length() + 10;
		
		System.out.println();

		StringBuilder sb = new StringBuilder();
		sb.append("+");
		
		for(int i = 0; i < width; i++) {
			sb.append("-");
		}
		
		sb.append("+");
		System.out.println(sb.toString());
		System.out.println("|     " + message + "     |");
		System.out.println(sb.toString());
	}

	private void printMenu() {
		displayHeader("Selections");
		
		System.out.println("1. Create a new account.");
		System.out.println("2. Make a deposit.");
		System.out.println("3. Make a withdraw.");
		System.out.println("4. List account balance.");
		System.out.println("5. Quit. ");

	}

	private String askForInfo(String data, List<String> answers) {
			Scanner userInput = new Scanner(System.in);
			String response = "";
			
			Boolean firstRun = true;
			Boolean choices = ((answers == null) || answers.size() == 0) ? false : true;
			
			do {
				if(!firstRun) {
					System.out.println("Invalid selection. Try again.");
				}
				System.out.print(data);
				
				response = userInput.nextLine();
				firstRun = false;
				
				if(!choices){
					break;
				}
				
			} while (!answers.contains(response));
			return response;
		}
	
	private double getDeposit(String accountType) {
		double initialDeposit = 0;
		
		while (true) {
			try {
				System.out.print("Enter an initial deposit: ");
				initialDeposit = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Deposit must be a number.");
			}

			// Checking if the user deposited enough money for each type of account

			if (accountType.equalsIgnoreCase("checking") || accountType.equals("1")) {
				if (initialDeposit < 100) {
					System.out.println("Minimal deposit to open a CHECKING account is 100$.");
				} else {
					break;
				}

			} else if (accountType.equalsIgnoreCase("savings") || accountType.equals("2")) {
				if (initialDeposit < 50) {
					System.out.println("Minimal deposit to open a SAVINGS account is 50$.");
				} else {
					break;
			}
		}
	}
		return initialDeposit;	
}	
	
	private void createAnAccount() throws invalidAccountException {
		displayHeader("Creating an account");
		
		List<String> accountTypes = Arrays.asList("checking", "savings", "Checking", "Savings", "1", "2");
		
		String accountType = askForInfo("Enter type of the account " + "\n" +
										"1) Checking" + "\n" +
										"2) Savings" + "\n" +
										"Account: ", accountTypes);
		String firstName = askForInfo("Print your first name: ", null);
		String lastName = askForInfo("Print your last name: ", null);
		String ssn = askForInfo("Enter your social security number: ", null);
		
		double initialDeposit = getDeposit(accountType);
		
		// We can create an account now
		Account account = null;
		if (accountType.equalsIgnoreCase("checking") || accountType.equals("1")) {
			account = new Checking(initialDeposit);
		} else if (accountType.equalsIgnoreCase("savings") || accountType.equals("2")){
			account = new Savings(initialDeposit);
		} else {
			throw new invalidAccountException();
		}

		Customer customer = new Customer(firstName, lastName, ssn, account);
		bank.addCustomer(customer);
	}

	private double getAmount(String data) {
		double amount = 0;
		
		if(valid) {
			System.out.println(data);
			try {
				amount = Double.parseDouble(scanner.nextLine());
	
			} catch (NumberFormatException e) {
				amount = 0;
			}
		} else {
			return -1;
		}
		return amount;
	}
	
	private void makeA_Withdraw() {
		displayHeader("Making a withdraw");
		
		int account = selectAccount();
		
		double amount = getAmount("Enter the amount of money you'd like to WITHDRAW: ");
		
		
		if (account >= 0) {
			bank.getCustomer(account).getAccount().withdraw(amount);
		}
	}

	private void makeA_Deposit() {	
		displayHeader("Making a deposit");
		
		int account = selectAccount();
		double amount = getAmount("Enter the amount of money you'd like to DEPOSIT: ");
		
		if (account >= 0) {	
			bank.getCustomer(account).getAccount().deposit(amount);
		}
	}

	private void listData() {
		int account = selectAccount();
		
		if (account >= 0) {
			displayHeader("Account data");
			System.out.println(bank.getCustomer(account).getAccount());
		}
	}

	private int selectAccount() {
		displayHeader("Select an account");
		
		ArrayList<Customer> customers = bank.getCustomers();
		int account;
		
		if (customers.size() <= 0) {
			System.out.println("You dont have any customers in your bank.");
			valid = false;
			return -1;
		}

		for (int i = 0; i < customers.size(); i++) {
			System.out.println(i + 1 + ". " + customers.get(i).basicInfo());
		}
		
		System.out.print("Enter your selection: ");
		try {
			account = Integer.parseInt(scanner.nextLine()) - 1;
			
			if(account < 0 || account > customers.size()) {
				System.out.println("Invalid account selection.");
				valid = false;
				account = -1;
			}
		} catch (NumberFormatException e) {
			System.out.println("Something went wrong.");
			account = -1;
		}
		
		return account;
	}

	private int getInput() {
		int choice = -1;

		do {
			System.out.print("Your choice is: ");
			try {
				choice = Integer.parseInt(scanner.nextLine()) - 1;
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection. Try again.");
			}
			
			if (choice < 0 || choice > 4) {
				System.out.println("Choise is out of range. Chose again.");
			}
			
		} while (choice < 0 || choice > 4);
		  return choice;
	}
	
	private void performAction(int choice) {
		switch (choice) {
		case 0:
			try {
				createAnAccount();
			} catch (invalidAccountException e) {
				System.out.println("Account wasnt created succesfully.");
			}
			break;
		case 1:
			makeA_Deposit();
			break;
		case 2:
			makeA_Withdraw();
			break;
		case 3:
			listData();
			break;
		case 4:
			System.out.println("Thanks for using Eugene's Bank.");
			System.exit(0);
			break;
		default:
			System.out.println("Unknown error has occured.");
		}
	}
}
