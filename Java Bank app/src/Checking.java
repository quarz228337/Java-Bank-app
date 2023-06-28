
public class Checking extends Account {
		private static String accountType = "checking";

		Checking(double initialDeposit){
			super();
			this.setBalance(initialDeposit);
			this.checkIntereset(0);
		}
		
	@Override
	public String toString() {
		return "Account type: " + accountType + "\n" +
				"Balance: " + this.getBalance() + "\n" +
				"Interest rate: " + this.getInterest() + "\n" +
				"Account number: " + this.getAccountNumber();
	}
}
