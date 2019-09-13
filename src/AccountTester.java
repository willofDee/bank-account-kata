

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTester {
	private static ByteArrayOutputStream _outContent = new ByteArrayOutputStream();
	private static final PrintStream _originalOut = System.out; // save the default output stream

	@BeforeEach
	public void setUpStreams() { // before each test reset the output stream
		_outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(_outContent));
	}

	@AfterAll
	public static void restoreStreams() { // restore the default stream after tests execution
	    System.setOut(_originalOut);
	}

	@Test
	void noLogTest() {
		Account account = new Account();
		account.printStatement();
		assertEquals("DATE, AMOUNT, BALANCE", _outContent.toString().trim());
	}

	@Test
	void depositTest() {
		Account account = new Account();
		account.deposit(100, "12/09/2019");
		account.printStatement();
		assertEquals("DATE, AMOUNT, BALANCE\n"
				+ "12/09/2019, 100.0, 100.0", _outContent.toString().trim());
	}

	@Test
	void withdrawTest() {
		Account account = new Account();
		account.deposit(100, "12/09/2019");
		account.withdraw(20, "12/09/2019");
		account.printStatement();
		assertEquals("DATE, AMOUNT, BALANCE\n"
				+ "12/09/2019, 100.0, 100.0\n"
				+ "12/09/2019, -20.0, 80.0", _outContent.toString().trim());
	}

	@Test
	void amountFailTest() {
		Account account = new Account();
		account.deposit(-100, "12/09/2019");
		account.printStatement();
		assertEquals("amount -100.0 is negative\n"
				+ "DATE, AMOUNT, BALANCE", _outContent.toString().trim());
	}

	@Test
	void dateFailTest() {
		Account account = new Account();
		account.deposit(100, "29/02/2019");
		account.deposit(150, "INVALID DATE");
		account.printStatement();
		assertEquals("invalid date 29/02/2019\n"
				   + "invalid date INVALID DATE\n"
				   + "DATE, AMOUNT, BALANCE", _outContent.toString().trim());
	}

	@Test
	void withdrawFailTest() {
		Account account = new Account();
		account.deposit(100, "12/09/2019");
		account.withdraw(200, "12/09/2019");
		account.printStatement();
		assertEquals("not enough money for withdrawal\n"
				+ "DATE, AMOUNT, BALANCE\n12/09/2019, 100.0, 100.0", _outContent.toString().trim());
	}

}
