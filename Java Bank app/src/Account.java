
public class Account {
	private double balance;
	private double interest;
	private int accountNumber;
	private static int numberOfAccounts = 1000000;
	
	Account(){
		accountNumber = numberOfAccounts++;
	}
	
	
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getInterest() {
		return interest;
	}
	
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void withdraw(double amount) {
		
		if (balance < amount + 5){
			System.out.println("You have deficient balance.");
			return;
		}
		checkIntereset();
		balance -= amount + 5;
		System.out.println("You have withdrawn " + amount + 5 + "$. (5$ fee)");
		System.out.println("Your current ballance is: " + balance + "$.");
	}
	
	public void deposit(double amount) {
		
		if(amount <= 0) {
			System.out.println("You cant deposit this amount of money.");
		}
		
		// here we are getting amount compiled with interest rate
		checkIntereset();
		amount = amount + amount * interest;
		balance += amount;
		System.out.println("You have deposited " + amount + " with an interest rate of "+ interest*100 + "%.");
		System.out.println("Your current balance is: " + balance + "$.");
	}



	public void checkIntereset() {
		
		if (balance > 10000) {
			interest = 0.05;
		}else  {
			interest = 0.02;
		}
		
	}

}
