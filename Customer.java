package Assignments;

import java.sql.*;
import java.util.*;



public class Customer {
	final static String DB_URL="jdbc:mysql://localhost:3306/assignment_2";
	final static String DB_user="root";
	final static String DB_pass="nicktata";
	static Scanner sc=new Scanner(System.in);
	

		public static void main(String[] args) throws SQLException {
			
			
			System.out.println("--------Customer DEtails--------");
			while(true){
				System.out.println("1.VIEW ALL CUSTOMERS");
				System.out.println("2.INSERT NEW CUSTOMER");
				System.out.println("3.DELETE CUSTOMER");
				System.out.println("4.SEARCH CUSTOMER BY ID");
				System.out.println("5.EXIT");
				System.out.println("****SELECT THE OPTION TO PROCEED***");
			String option=sc.next();
			System.out.println("");
			switch(option)
			{
			
			
			case "1":
		    
	 try (Connection conn=DriverManager.getConnection(DB_URL,DB_user,DB_pass);
		 Statement st=conn.createStatement();
		 ResultSet rs =st.executeQuery("select * from customers");)
	 {
		 while(rs.next()) {
		    
		    System.out.println("Customer id:"+rs.getInt("cust_id"));
		    System.out.println("Customer Name:"+rs.getString("cust_name"));
		    System.out.println("Customer Age:"+rs.getInt("cust_age"));
		    System.out.println("Customer Address:"+rs.getString("cust_addr"));
		    System.out.println("Customer income:"+rs.getInt("cust_income"));
		    System.out.println("Customer Job location:"+rs.getString("cust_job_loc"));
		    System.out.println("Customer department:"+rs.getString("cust_dept"));
		    System.out.println("---------------------------");
		    
		    		        
		 }
		 
	 }
	 catch(SQLException e) {
		 e.printStackTrace();
			 }
	 break;

		
			case "2":
		try (Connection conn=DriverManager.getConnection(DB_URL,DB_user,DB_pass);
				 PreparedStatement ps=conn.prepareStatement("insert into customers values(?,?,?,?,?,?,?)");)
			 {
				 System.out.println("Enter customer id");
				 int cid=sc.nextInt();
				 System.out.println("Enter customer name");
				 String cname=sc.next();
				 System.out.println("Enter customer age");
				 int cage=sc.nextInt();
				 System.out.println("Enter customer address");
				 String caddr=sc.next();
				 System.out.println("Enter customer income");
				 int cincome=sc.nextInt();
				 System.out.println("Enter customer job location");
				 String cloc=sc.next();
				 System.out.println("Enter customer department");
				 String cdept=sc.next();
				 
				 ps.setInt(1, cid);
				 ps.setString(2, cname);
				 ps.setInt(3, cage);
				 ps.setString(4, caddr);
				 ps.setInt(5, cincome);
				 ps.setString(6, cloc);
				 ps.setString(7, cdept);
				 ps.executeUpdate();
				 System.out.println("\n----Inserted record select display option to view record----\n");
				 				 		 
			 }
			 catch(SQLException e) {
				 e.printStackTrace();
					 }
		
		break;
		
			case "3":
				try (Connection conn=DriverManager.getConnection(DB_URL,DB_user,DB_pass);
						 PreparedStatement ps=conn.prepareStatement("delete from customers where cust_id=?");)
					 {
					System.out.println("Enter customer id");
					int cid=sc.nextInt();
					
					ps.setInt(1,cid);
					ps.executeUpdate();
					 }
				break;
				
			case "4":
		//search
		try (Connection conn=DriverManager.getConnection(DB_URL,DB_user,DB_pass);
				 PreparedStatement ps=conn.prepareStatement("select * from customers where cust_id=?");)
			 {
				System.out.println("Enter customer id");
				int cid=sc.nextInt();
				
				ps.setInt(1,cid);
				
				ResultSet rs=ps.executeQuery();
				 while(rs.next()) {
				    //System.out.println("Tutorial Author:"+rs.getString("tutorial_author"));
				    System.out.println("Customer id:"+rs.getInt("cust_id"));
				    System.out.println("Customer Name:"+rs.getString("cust_name"));
				    System.out.println("Customer Age:"+rs.getInt("cust_age"));
				    System.out.println("Customer Address:"+rs.getString("cust_addr"));
				    System.out.println("Customer income:"+rs.getInt("cust_income"));
				    System.out.println("Customer Job location:"+rs.getString("cust_job_loc"));
				    System.out.println("Customer department:"+rs.getString("cust_dept"));
				    
				        
				 }
				 
			 }
			 catch(SQLException e) {
				 e.printStackTrace();
					 }
		      break;
			case "5": return;
			           //break;

			}
			}

		}
		
	
}