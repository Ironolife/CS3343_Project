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

		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		String operation = this.readInput();

		if (operation.equals("1")) {
			EMS.PrintHeader("- Login -");
			System.out.println("LoginId: ");
			String loginId = this.readInput();
			System.out.println("Password: ");
			String password = this.readInput();

			BackEnd backEnd = BackEnd.getInstance();
			boolean isValid = false;
			for (Vendor vendor : backEnd.getVendors()) {
				if (vendor.getLoginId().equals(loginId)) {
					if (vendor.validatePassword(password) == true) {
						new VendorFrontEnd(vendor);
						isValid = true;
						System.out.println();
						this.accountManagement();
					} else {
						EMS.PrintHeader("Invalid Password!");
						this.accountManagement();
					}
					break;
				}
			}
			for (User user : backEnd.getUsers()) {
				if (user.getLoginId().equals(loginId)) {
					if (user.validatePassword(password) == true) {
						new UserFrontEnd(user);
						isValid = true;
						System.out.println();
						this.accountManagement();
					} else {
						EMS.PrintHeader("Invalid Password!");
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
			System.out.println("Account Type: ");
			System.out.println("1: User");
			System.out.println("2: Vendor");
			String accountType = this.readInput();
			while (!accountType.equals("1") && !accountType.equals("2")) {
				EMS.PrintHeader("Invalid Account Type!");
				System.out.println("Account Type: ");
				System.out.println("1: User");
				System.out.println("2: Vendor");
				accountType = this.readInput();
			}

			System.out.println("LoginId: ");
			String loginId = this.readInput();

			BackEnd backEnd = BackEnd.getInstance();
			ArrayList<String> loginIds = new ArrayList<String>();
			for (Vendor vendor : backEnd.getVendors()) {
				loginIds.add(vendor.getLoginId());
			}
			for (User user : backEnd.getUsers()) {
				loginIds.add(user.getLoginId());
			}

			boolean isDuplicateLoginId = loginIds.contains(loginId);
			while (isDuplicateLoginId == true) {
				EMS.PrintHeader("Duplicate LoginId! Please try another one.");
				System.out.println("LoginId: ");
				loginId = this.readInput();
				isDuplicateLoginId = loginIds.contains(loginId);
			}

			System.out.println("Password: ");
			String password = this.readInput();

			if (accountType.equals("1")) {
				System.out.println("HKID: ");
				String hkID = this.readInput();

				ArrayList<String> hkIDs = new ArrayList<String>();
				for (User user : backEnd.getUsers()) {
					hkIDs.add(user.getHKID());
				}

				boolean isDuplicateHKID = hkIDs.contains(hkID);
				while (User.validateHKID(hkID) == false || isDuplicateHKID == true) {
					if (isDuplicateHKID == true) {
						EMS.PrintHeader("HKID already registered!");
					} else {
						EMS.PrintHeader("Invalid HKID!");
					}
					System.out.println("HKID: ");
					hkID = this.readInput();
					isDuplicateHKID = hkIDs.contains(hkID);
				}

				int age = -1;
				while (age == -1) {
					try {
						System.out.println("Age: ");
						age = Integer.parseInt(this.readInput());
						while (age < 0 || age > 122) {
							EMS.PrintHeader("Invalid Age!");
							System.out.println("Age: ");
							age = Integer.parseInt(this.readInput());
						}
					} catch (NumberFormatException e) {
						EMS.PrintHeader("Invalid Age!");
						age = -1;
					}
				}

				System.out.println("Please select an account type: ");
				System.out.println("1: Guest");
				System.out.println("2: Member");
				String userAccountType = this.readInput();

				while (!userAccountType.equals("1") && !userAccountType.equals("2")) {
					EMS.PrintHeader("Invalid Input");
					System.out.println("Please select an account type: ");
					System.out.println("1: Guest");
					System.out.println("2: Member");
					userAccountType = this.readInput();
				}

				if (userAccountType.equals("1")) {
					Guest guest = new Guest(loginId, password, age, hkID);
					backEnd.addUser(guest);
					EMS.PrintHeader("Guest Created!");
					this.accountManagement();
				} else if (userAccountType.equals("2")) {
					System.out.println("Name: ");
					String name = this.readInput();
					Member member = new Member(loginId, password, name, age, hkID);
					backEnd.addUser(member);
					EMS.PrintHeader("Member Created!");
					this.accountManagement();
				}
			} else if (accountType.equals("2")) {
				System.out.println("Vendor Name: ");
				String name = this.readInput();
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
