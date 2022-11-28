package Assignments;
import java.sql.*;
import java.util.*;

public class Food_order_app {

	final static String DB_URL="jdbc:mysql://localhost:3306/assignment_3";
	final static String DB_user="root";
	final static String DB_pass="nicktata";
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		while(true) {
			System.out.println("-----Welcome to FOOD TRUCK----");
			System.out.println("1.REGISTER");
			System.out.println("2.LOGIN");
			System.out.println("3.EXIT");
			System.out.println("SELECT ANY ONE OPTION TO PROCEED");
			int op=sc.nextInt();
			switch(op) {
			case 1 : register();
					break;
			case 2: login();
					break;
			case 3: return;
			}
			}
			

		}
		
		
		public static void register() {
			System.out.println("Enter emailid to register)");
			String email = sc.next();
			System.out.println("Enter password");
			String password = sc.next();
			String sql = "insert into food_register values (?,?)";
			try(Connection conn = DriverManager.getConnection(DB_URL, DB_user, DB_pass);
					PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, email);
				ps.setString(2, password);
			    ps.executeUpdate();
			    System.out.println("user register successfully");
			    
			    
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		
		public static void login() {
			
			try (Connection conn=DriverManager.getConnection(DB_URL,DB_user,DB_pass);
					 PreparedStatement ps=conn.prepareStatement("select * from food_register where email=? and password=?;");)
				 {
					 
				System.out.println("Enter registered userid");
				String emailid = sc.next();
				System.out.println("Enter pass");
				String password = sc.next();
				ps.setString(1, emailid);			 
			    ps.setString(2, password);
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						
			
			
			while(true) {
				System.out.println("HI "+emailid);
				System.out.println("1.Order food");
				System.out.println("2.Delete food from order");
				System.out.println("3.Count total");
				System.out.println("4.Logout");
				System.out.println("Select option to proceed");
				int option = sc.nextInt();
				switch(option) {
				
				case 1:System.out.println("----Menu-----");
					System.out.println("1.PAV BHAJI   RS.120");
					   System.out.println("2.SCHEZWAN RICE   Rs.150");
					   System.out.println("3.PANNER CHILLI   Rs.100");
					   System.out.println("4.COLD COFFEE   RS.30");
					   System.out.println("5.DOSA   RS.70");
					   int order_no = sc.nextInt();
					   place_order(emailid,order_no);
					   break;
				case 2:	System.out.println("1.PAV BHAJI   RS.120");
				   System.out.println("2.SCHEZWAN RICE   Rs.150");
				   System.out.println("3.PANNER CHILLI   Rs.100");
				   System.out.println("4.COLD COFFEE   RS.30");
				   System.out.println("5.DOSA   RS.70");
				   System.out.println("Select option below to cancel the order");

				   int cancel_no = sc.nextInt();
				  cancel_order(emailid,cancel_no);
				   	break;
				case 3:
					total(emailid);
					break;
				case 4:
					System.out.println("Thanks order_nor Visting!!! Enjoy your order_nood!!");
					return;
				
				}
				
			}
		}
			else {
				System.out.println("Invalid credential");
			}
		}
			catch(SQLException e) {System.out.println(e);}
	}
		
		private static void total(String emailid) {
			try(Connection conn = DriverManager.getConnection(DB_URL, DB_user, DB_pass);
					PreparedStatement ps = conn.prepareStatement("select sum(price) as a from food_order where email =?");){
				ps.setString(1,emailid);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
				System.out.println(rs.getInt("a"));
				}
			}catch(SQLException e) {
				System.out.println(e);
			}
			
				
		}
		


		private static void cancel_order(String emailid, int cancel_no) {
			try(Connection conn = DriverManager.getConnection(DB_URL, DB_user, DB_pass);
					PreparedStatement ps = conn.prepareStatement("delete from food_order where email = ? and item = ?)");){
				ps.setString(1, emailid);
				if(cancel_no == 1) 
					ps.setString(2, "PAV BHAJI");
				else if(cancel_no == 2) {ps.setString(2, "SCHEZWAN RICE");}
				else if(cancel_no == 3){ps.setString(2, "PANNER CHILLI");}
					//ps.setInt(3, 100);
				else if(cancel_no == 3){ps.setString(2, "COLD COFFEE");}
					//ps.setInt(3, 30);
				else {ps.setString(2, "DOSA");}
					//ps.setInt(3, 70);
							
				if(ps.executeUpdate()>0) {
					System.out.println("removed successfully");
				}
				else {
					System.out.println("removal failed");
				}
								
			}
			catch(SQLException e) {
				System.out.println(e);
			}
			
			
		}


		public static void place_order(String email_id,int order_no) {
			
			String sql = "insert into food_order(email,item, price) values (?,?,?)";
			try(Connection conn = DriverManager.getConnection(DB_URL, DB_user, DB_pass);
					PreparedStatement ps = conn.prepareStatement(sql)){
				
				ps.setString(1, email_id);
				if(order_no == 1) {
					ps.setString(2, "PAV BHAJI");
					ps.setInt(3, 120);
				}else if(order_no == 2){
					ps.setString(2, "SCHEZWAN RICE");
					ps.setInt(3, 150);
				}else if(order_no == 3){
					ps.setString(2, "PANNER CHILLI");
					ps.setInt(3, 100);
					
				}else if(order_no == 3){
					ps.setString(2, "COLD COFFEE");
					ps.setInt(3, 30);
				}
				
				else {
					ps.setString(2, "DOSA");
					ps.setInt(3, 70);
				}				
				
				if(ps.executeUpdate()>0) {
					System.out.println("Order placed successfully");
				}
				else 
					System.out.println("order failed try again...");
			
				
			}catch(SQLException e) {
				System.out.println(e);
			}

			
		}
		
		
	}//food_order_app end
		

	}

}
