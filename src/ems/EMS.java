package ems;

import java.io.IOException;
import java.util.Scanner;

public class EMS {
	
	public static void main(String[] args) {
		
		System.out.println("- Event Management System -");
		System.out.println();
		
		EMS.AccountManagement();
		
    }
	
	public static void AccountManagement() {
		
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		
		switch(EMS.readInput()) {
			case "1": {
				System.out.println("LoginId: ");
				String loginId = EMS.readInput();
				System.out.println("Password: ");
				String password = EMS.readInput();
				
				BackEnd backEnd = BackEnd.getInstance();
				boolean isValid = false;
				for(Vendor vendor: backEnd.getVendors()) {
					if(vendor.getLoginId() == loginId) {
						if(vendor.validatePassword(password) == true) {
							VendorFrontEnd vendorFrontEnd = new VendorFrontEnd(vendor);
							//TODO initialize vendorFrontEnd
							isValid = true;
						}
						else {
							EMS.clearScreen();
							System.out.println("Invalid Password");
							EMS.AccountManagement();
						}
						break;
					}
				}
				for(User user: backEnd.getUsers()) {
					if(user.getLoginId() == loginId) {
						if(user.validatePassword(password) == true) {
							UserFrontEnd userFrontEnd = new UserFrontEnd(user);
							//TODO initialize userFrontEnd
							isValid = true;
						}
						else {
							EMS.clearScreen();
							System.out.println("Invalid Password");
							EMS.AccountManagement();
						}
						break;
					}
				}
				if(isValid == false ) {
					EMS.clearScreen();
					System.out.println("Invalid Login");
					EMS.AccountManagement();
				}
				break;
			}
			case "2": {
				break;
			}
			default: {
				EMS.clearScreen();
				System.out.println("Invalid Input!");
				break;
			}
		}
		
	}
	
	public static void clearScreen() {
		
		try {
			Runtime.getRuntime().exec("cls");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String readInput() {
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		return input;
		
	}

}
