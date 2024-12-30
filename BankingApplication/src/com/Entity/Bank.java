package com.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.Configuration.Config;
import com.Unimplemented.BankInterface;

public class Bank implements BankInterface{
	
	Scanner sc = new Scanner(System.in);
	@Override
	public void insert() {
		
		try {
			Connection con = Config.connectToDB();
			
			PreparedStatement ps = con.prepareStatement("insert into customer(id,name,balance,accno) values(?,?,?,?);");
			
			System.out.println("Enter Id");
			int i = sc.nextInt();
			
			System.out.println("Enter Name");
			String n = sc.next();
			
			System.out.println("Enter balance");
			double b = sc.nextDouble();
			
			System.out.println("Enter AccountNumber");
			long an = sc.nextLong();
			
			ps.setInt(1, i);
			ps.setString(2, n);
			ps.setDouble(3, b);
			ps.setLong(4, an);
			
			ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		
		try {
			Connection con = Config.connectToDB();
			
			PreparedStatement ps = con.prepareStatement("update customer set name = ? where id = ?;");
			
			System.out.println("Enter updated name");
			String n = sc.next();
			
			System.out.println("Enter id to update");
			int i = sc.nextInt();
			
			ps.setString(1, n);
			ps.setInt(2, i);
			
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete() {
		
		try {
			Connection con = Config.connectToDB();
			
			PreparedStatement ps = con.prepareStatement("delete from customer where id = ?;");
			
			System.out.println("Enter Id to Delete");
			int i = sc.nextInt();
			
			ps.setInt(1, i);
			
			ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void getAllData() {
		
		try {
			Connection con = Config.connectToDB();
			
			Statement s = con.createStatement();
			
			String query = "select * from customer;";
			
			ResultSet set = s.executeQuery(query);
			
			while(set.next()) {
				System.out.println(set.getInt("id"));
				System.out.println(set.getString("name"));
				System.out.println(set.getDouble("balance"));
				System.out.println(set.getLong("accno"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void getSingleData() {
		
		try {
			Connection con = Config.connectToDB();
			
			PreparedStatement ps = con.prepareStatement("select * from customer where id = ?;");
			
			System.out.println("Enter Id to get data");
			int i = sc.nextInt();
			
			ps.setInt(1, i);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getDouble("balance"));
				System.out.println(rs.getLong("accno"));
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void withdraw() {
		
		System.out.println("Enter Id");
		int id = sc.nextInt();
		
		System.out.println("Enter amount to withdraw");
		double amt = sc.nextDouble();
		
		try {
			Connection con = Config.connectToDB();
			
			PreparedStatement ps = con.prepareStatement("select * from customer where id = ?;");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			double b = 0;
			while(rs.next()) {
				b = rs.getDouble(3);
			}
			b = b - amt;
			System.out.println(b);
			ps.close();
			
			PreparedStatement ps1 = con.prepareStatement("update customer set balance = ? where id = ?;");
			ps1.setDouble(1, b);
			ps1.setInt(2, id);
			
			ps1.executeUpdate();
			ps1.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Override
	public void deposite() {
		
		try {
			
			System.out.println("Enter id ");
			int id = sc.nextInt();
			
			System.out.println("Enter amount to deposite");
			double amt = sc.nextDouble();
			
			Connection con = Config.connectToDB();
			
			PreparedStatement ps1 = con.prepareStatement("select * from customer where id = ?;");
			
			ps1.setInt(1, id);
			
			ResultSet rs = ps1.executeQuery();
			
			double b = 0;
			
			while(rs.next()) {
				b = rs.getDouble(3);
			}
			b = b + amt;
			System.out.println((b));
			
			ps1.close();
			
			PreparedStatement ps2 = con.prepareStatement("update customer set balance = ? where id = ?;");
			
			ps2.setDouble(1, b);
			ps2.setInt(2, id);
			
			ps2.executeUpdate();
			ps2.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
