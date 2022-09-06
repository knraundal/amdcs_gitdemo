package banking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class bank {

	static Connection con = Main.getConnection();
	static String sql="";
	
	public void createAccount(String name, int balance) throws SQLException{
		
		try {
		Statement stmt= con.createStatement();
		sql = "insert into customer values((select MAX(acc_number)+1 from customer),'"+ name +"','" + balance +"')";
		stmt.executeUpdate(sql);
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("Account created successfully!");
		ResultSet rs= stmt.executeQuery("Select * from customer where name='"+name+"'");
		
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
		}
		System.out.println("--------------------------------------------------------------------------------------------");
		}
		catch(Exception e) {
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("User name Not Available");
			System.out.println("--------------------------------------------------------------------------------------------");
		}
	}//create account method ends
	
	public void getBalance(int accNo) throws SQLException{
		try {
			Statement stmt = con.createStatement();
			sql="select * from customer where acc_number="+accNo;
			
			if(stmt.executeUpdate(sql)==1) {
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
					System.out.println("--------------------------------------------------------------------------------------------");
				}//while ends
			}//if ends
			
			else
			{
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println("Acount Number not Available");
				System.out.println("--------------------------------------------------------------------------------------------");
			}//else ends
		}//try ends
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}//get balance ends here
	
	public void deposit(int accNod,int credit) {
		try {
			if(credit<0) {
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println("Amount can not be negative");
				System.out.println("--------------------------------------------------------------------------------------------");
			}//if ends
			else {
				Statement stmt = con.createStatement();
				sql="update customer set balance = balance+"+ credit+" where acc_number="+accNod;
				if(stmt.executeUpdate(sql)!=1) {
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("Account number is not Available");
					System.out.println("--------------------------------------------------------------------------------------------");
				}//inner if ends
				else {
					ResultSet rs= stmt.executeQuery("Select * from customer where acc_number='"+accNod+"'");
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println(credit+" Successfully credit to your account");
					while(rs.next()) {
						System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
					}//while end
					System.out.println("--------------------------------------------------------------------------------------------");
				}//inner else ends
			}//else ends here
				
		}//try ends here
		catch(Exception e) {
			e.printStackTrace();
		}//catch ends
	}//deposit ends here
	
	public void withdrawl(int accNow, int debit) {
		try {
			if(debit<0) {
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println("Amount can not be negative");
				System.out.println("--------------------------------------------------------------------------------------------");
			}//if ends
			
			else {
				Statement stmt= con.createStatement();
				sql="Select * from customer where acc_number='"+accNow+"'";
				
				if(stmt.executeUpdate(sql)!=1) {
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("Account is not Available");
					System.out.println("--------------------------------------------------------------------------------------------");
				}//inner if
				ResultSet rs=stmt.executeQuery("Select * from customer where acc_number='"+accNow+"'");
				if(rs.next()) {
					if(rs.getInt(3) < debit) {
						System.out.println("--------------------------------------------------------------------------------------------");
						System.out.println("Insufficient Balance");
						System.out.println("--------------------------------------------------------------------------------------------");
					}//balanve debit if
					
					else {
						sql="Update customer set balance=balance-"+debit+" where acc_number="+accNow;
						
						if(stmt.executeUpdate(sql)==1) {
							System.out.println("--------------------------------------------------------------------------------------------");
							System.out.println(debit+" Successfully debited from your account");
						}//if
						
						ResultSet re = stmt.executeQuery("Select * from customer where acc_number='"+accNow+"'");
						
						while(re.next()) {
							System.out.println(re.getInt(1)+":"+re.getString(2)+":"+re.getInt(3));
						}//while ends
						System.out.println("--------------------------------------------------------------------------------------------");
					}//balance debit else
				}//rsnext if
			}//else ends
		}//try ends 
		catch(Exception e) {
			e.printStackTrace();
		}
	}//withdrawl ends

}//class ends
