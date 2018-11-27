package ems;

import java.util.ArrayList;
import java.util.Scanner;

public class EMS {
	

	public static void main(String[] args) {

		new EMS();

	}
	
	public EMS() {
		
		EMS.PrintHeader("- Event Management System -");
		
		this.accountManagement();
		
	}

	public void accountManagement() {
		
	BackEnd backEnd = BackEnd.getInstance();

		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		String operation = this.readInput();

		if (operation.equals("1")) {
			
			EMS.PrintHeader("- Login -");
			
			//Read loginId
			System.out.println("LoginId: ");
			String loginId = this.readInput();
			
			//Read password
			System.out.println("Password: ");
			String password = this.readInput();

			//Validate login and initialize frontEnd
			boolean isValid = false;
			for (Vendor vendor : backEnd.getVendors()) {
				if (vendor.getLoginId().equals(loginId)) {
					if (vendor.validatePassword(password) == true) {
						new VendorFrontEnd(vendor);
						isValid = true;
						System.out.println();
						
					} else {
						EMS.PrintHeader("Invalid Password!");
						isValid = true;
						this.accountManagement();
					}
					break;
				}
			}
			for (User user : backEnd.getUsers()) {
				if (user.getLoginId().equals(loginId)) {
					if (user.validatePassword(password) == true) {
						new UserFrontEnd(user).userOperations();
						isValid = true;
						System.out.println();
						
					} else {
						EMS.PrintHeader("Invalid Password!");
						isValid = true;
						this.accountManagement();
					}
					break;
				}
			}
			if (isValid == false) {
				EMS.PrintHeader("Invalid Login!");
				this.accountManagement();
			}
		} else if (operation.equals("2")) {
			
			EMS.PrintHeader("- Register -");
			
			//Read account type
			String accountType = null;
			while (accountType == null) {
				System.out.println("Account Type: ");
				System.out.println("1: User");
				System.out.println("2: Vendor");
				accountType = this.readInput();
				if(!accountType.equals("1") && !accountType.equals("2")) {
					EMS.PrintHeader("Invalid Account Type!");
					accountType = null;
				}
			}

			//Read loginId and check for duplicate
			String loginId = null;
			while (loginId == null) {
				System.out.println("LoginId: ");
				loginId = this.readInput();
				if(backEnd.isDuplicateLoginId(loginId)) {
					EMS.PrintHeader("Duplicate LoginId! Please try another one.");
					loginId = null;
				}
			}
			
			//Read password
			System.out.println("Password: ");
			String password = this.readInput();

			if (accountType.equals("1")) {
				
				//Read HKID and validate
				String hkID = null;
				while (hkID == null) {
					System.out.println("HKID: ");
					hkID = this.readInput();
					if (backEnd.isDuplicateHKID(hkID) == true) {
						EMS.PrintHeader("HKID already registered!");
						hkID = null;
					} 
				}

				//Read age and validate
				int age = -1;
				while (age == -1) {
					try {
						System.out.println("Age: ");
						age = Integer.parseInt(this.readInput());
						if (age < 0 || age > 122) {
							EMS.PrintHeader("Invalid Input!");
							age = -1;
						}
					} catch (NumberFormatException e) {
						EMS.PrintHeader("Invalid Input!");
						age = -1;
					}
				}

				//Read User account type
				String userAccountType = null;
				while (userAccountType == null) {
					System.out.println("Please select an account type: ");
					System.out.println("1: Guest");
					System.out.println("2: Member");
					userAccountType = this.readInput();
					if(!userAccountType.equals("1") && !userAccountType.equals("2")) {
						EMS.PrintHeader("Invalid Input");
						userAccountType = null;
					}
				}

				if (userAccountType.equals("1")) {
					
					//Create guest
					Guest guest = new Guest(loginId, password, age, hkID);
					backEnd.addUser(guest);
					
					EMS.PrintHeader("Guest Created!");
					this.accountManagement();
					
				} else if (userAccountType.equals("2")) {
					
					//Read name
					System.out.println("Name: ");
					String name = this.readInput();
					
					//Create Member
					Member member = new Member(loginId, password, name, age, hkID);
					backEnd.addUser(member);
					
					EMS.PrintHeader("Member Created!");
					this.accountManagement();
					
				}
			} else if (accountType.equals("2")) {
				
				//Read vendor name
				System.out.println("Vendor Name: ");
				String name = this.readInput();
				
				//Create vendor
				Vendor vendor = new Vendor(loginId, password, name);
				backEnd.createNewVendor(vendor);
				
				EMS.PrintHeader("Vendor Created!");
				this.accountManagement();
			}
		} else {
			
			EMS.PrintHeader("Invalid Input!");
			this.accountManagement();
			
		}

	}

	public String readInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}

	public static void PrintHeader(String header) {
		System.out.println();
		System.out.println(header);
		System.out.println();
	}

}
