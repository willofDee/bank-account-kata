import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {
	private double _balance;
	private double _minBalance;
	private List<String> _operationLogs;
	
	public Account() {
		_balance = 0;
		_minBalance = 0;
		_operationLogs = new ArrayList<>();
	}
	
	public Account(double initialBalance) {
		this();
		_balance = initialBalance;
	}
	
	public Account(double initialBalance, double minimunBalance) {
		this(initialBalance);
		_minBalance = minimunBalance;
	}

	public void deposit(double amount, String date) {
		// deposit money
		if(okParameters(amount, date)) {
			_balance += amount;
			_operationLogs.add(date + ", " + amount + ", " + _balance);
		}
	}
	
	public void withdraw(double amount, String date) {
		// withdraw money
		if(okParameters(amount, date)) {
			if(_balance - amount < _minBalance) {
				System.out.print("not enough money for withdrawal\n");
				return;
			}
			_balance -= amount;
			_operationLogs.add(date + ", -" + amount + ", " + _balance);
		}
	}
	
	public void printStatement() {
		System.out.print("DATE, AMOUNT, BALANCE\n");
		for(String log : _operationLogs) {
			System.out.print(log + "\n");
		}
		System.out.println();
	}
	
	private boolean okParameters(double amount, String date) {
		if(amount < 0) {
			System.out.print("amount " + amount + " is negative\n");
			return false;
		}
		if(!isValidDate(date)) {
			System.out.print("invalid date " + date + "\n");
			return false;
		}
		return true;
	}
	
	private boolean isValidDate(String input) {
	    boolean valid = false;
	    String format = "dd/MM/yyyy";

	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	        String output = dateFormat.format(dateFormat.parse(input));
	        valid = input.equals(output); 
	    } catch (Exception ignore) {}

	    return valid;
	}
}
