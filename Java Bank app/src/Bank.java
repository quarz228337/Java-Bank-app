import java.util.ArrayList;

public class Bank {
	
	ArrayList<Customer> customers = new ArrayList<Customer>();

	public void addCustomer(Customer customer) {
		
		customers.add(customer);
	}
	
}
