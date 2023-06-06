
public class Checking extends Account {
		private static String accountType = "checking";

		Checking(double initialDeposit){
			super();
			this.setBalance(initialDeposit);
			this.checkIntereset();
		}
		
	@Override
	public String toString() {
		return "Account type: " + accountType + "\n" +
				"Balance: " + this.getBalance() + "\n" +
				"Interest rate: " + this.getInterest()+
				"                                         Account number: " + this.getAccountNumber();
	}
}
