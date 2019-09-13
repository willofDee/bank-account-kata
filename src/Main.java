import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter one of the following commands:");
		Scanner scan = new Scanner(System.in);
		System.out.println();
		
		int choice = 0;		
		Account account = new Account();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String sDate = format.format(date);  
				
		while (choice != 4) {
			System.out.println("1 - deposit");
			System.out.println("2 - withdraw");
			System.out.println("3 - print account statement");
			System.out.println("4 - exit");
			
			try {
		        choice = scan.nextInt();
			    if(choice == 1) {
			    	System.out.println("enter the amount you want to save");
			        double amount = scan.nextDouble();
			        account.deposit(amount, sDate);
			        System.out.println();
			    }
			    else if(choice == 2) {
			    	System.out.println("enter the amount you want to withdraw");
			        double amount = scan.nextDouble();
			        account.withdraw(amount, sDate);
			        System.out.println();
			    }
			    else if(choice == 3) {
			    	account.printStatement();
			    }
			    else if(choice == 4) {
			        //...exit program
			    }
			    else{
			        System.out.println("Enter \"1\", \"2\", \"3\" or \"4\"\n");
			    }
			} catch(InputMismatchException e) {
				System.out.println("input error\n");
				scan.nextLine();
			}
		} 
		scan.close();
	}

}
