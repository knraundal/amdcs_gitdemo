package banking;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends bank {
	
	static Connection con;
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		Main obj = new Main();
		
		String name="";
		int balance;
		int accNo;
		int accNod;
		int accNow;
		int credit;
		int debit;
		getConnection();
		
		System.out.println("---------------Welecome--------------");
		while(true) {
			System.out.println();
			System.out.println("Please Select your choice: ");
			System.out.println("1.Open Account");
			System.out.println("2.Check Balance");
			System.out.println("3.Withdraw Money");
			System.out.println("4.Deposit Money");
			System.out.println("5.Exit");
			
			int ch=sc.nextInt();
			
			switch(ch) {
			case 1:
				System.out.println("Enter your name");
				name = sc.next();
				System.out.println("Enter amount you want to deposit");
				balance = sc.nextInt();
				obj.createAccount(name, balance);
				break;
			case 2:
				System.out.println("For Checking Balance- ");
				System.out.println("Enter your account number");
				accNo = sc.nextInt();
				obj.getBalance(accNo);
				break;
			case 3:
				System.out.println("For withdraw money- ");
				System.out.println("Enter your account number");
				accNow = sc.nextInt();
				System.out.println("Enter amount to be withdraw");
				debit = sc.nextInt();
				obj.withdrawl(accNow, debit);
				break;
			case 4:
				System.out.println("For deposite money- ");
				System.out.println("Enter your account number");
				accNod = sc.nextInt();
				System.out.println("Enter amount to credit");
				credit = sc.nextInt();
				obj.deposit(accNod, credit);
				break;
			case 5:
				System.out.println("Thank you for Banking With us!!!");
				System.exit(0);
			default:
				System.out.println("Incorrect Choice");
				sc.close();
			}//switch
		}//while ends
	}
	
	public static Connection getConnection() //throws ClassNotFoundException,SQLException
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/orcl.iiht.tech";
			String userName ="scott";
			String password ="tiger";
			con=DriverManager.getConnection(url,userName,password);
		}//try ends
		
		catch(Exception e) {
			System.out.println("Connection Failed!");
		}
		return con;
	}//getconnection ends
}//mail class
