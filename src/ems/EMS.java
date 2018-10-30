package ems;

import java.util.ArrayList;
import java.util.Scanner;

public class EMS {

	public static void main(String[] args) {

		EMS.PrintHeader("- Event Management System -");

		EMS.AccountManagement();

	}

	public static void AccountManagement() {

		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		String operation = EMS.readInput();

		if (operation.equals("1")) {
			EMS.PrintHeader("- Login -");
			System.out.println("LoginId: ");
			String loginId = EMS.readInput();
			System.out.println("Password: ");
			String password = EMS.readInput();

			BackEnd backEnd = BackEnd.getInstance();
			boolean isValid = false;
			for (Vendor vendor : backEnd.getVendors()) {
				if (vendor.getLoginId().equals(loginId)) {
					if (vendor.validatePassword(password) == true) {
						VendorFrontEnd vendorFrontEnd = new VendorFrontEnd(vendor);
						// TODO initialize vendorFrontEnd
						isValid = true;
					} else {
						EMS.PrintHeader("Invalid Password!");
						EMS.AccountManagement();
					}
					break;
				}
			}
			for (User user : backEnd.getUsers()) {
				if (user.getLoginId().equals(loginId)) {
					if (user.validatePassword(password) == true) {
						UserFrontEnd userFrontEnd = new UserFrontEnd(user);
						// TODO initialize userFrontEnd
						isValid = true;
					} else {
						EMS.PrintHeader("Invalid Password!");
						EMS.AccountManagement();
					}
					break;
				}
			}
			if (isValid == false) {
				EMS.PrintHeader("Invalid Login!");
				EMS.AccountManagement();
			}
		} else if (operation.equals("2")) {
			EMS.PrintHeader("- Register -");
			System.out.println("Account Type: ");
			System.out.println("1: User");
			System.out.println("2: Vendor");
			String accountType = EMS.readInput();
			while (!accountType.equals("1") && !accountType.equals("2")) {
				EMS.PrintHeader("Invalid Account Type!");
				System.out.println("Account Type: ");
				System.out.println("1: User");
				System.out.println("2: Vendor");
				accountType = EMS.readInput();
			}

			System.out.println("LoginId: ");
			String loginId = EMS.readInput();

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
				loginId = EMS.readInput();
				isDuplicateLoginId = loginIds.contains(loginId);
			}

			System.out.println("Password: ");
			String password = EMS.readInput();

			if (accountType.equals("1")) {
				System.out.println("HKID: ");
				String hkID = EMS.readInput();

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
					hkID = EMS.readInput();
					isDuplicateHKID = hkIDs.contains(hkID);
				}

				int age = -1;
				while (age == -1) {
					try {
						System.out.println("Age: ");
						age = Integer.parseInt(EMS.readInput());
						while (age < 0 || age > 122) {
							EMS.PrintHeader("Invalid Age!");
							System.out.println("Age: ");
							age = Integer.parseInt(EMS.readInput());
						}
					} catch (NumberFormatException e) {
						EMS.PrintHeader("Invalid Age!");
						age = -1;
					}
				}

				System.out.println("Please select an account type: ");
				System.out.println("1: Guest");
				System.out.println("2: Member");
				String userAccountType = EMS.readInput();

				while (!userAccountType.equals("1") && !userAccountType.equals("2")) {
					EMS.PrintHeader("Invalid Input");
					System.out.println("Please select an account type: ");
					System.out.println("1: Guest");
					System.out.println("2: Member");
					userAccountType = EMS.readInput();
				}

				if (userAccountType.equals("1")) {
					Guest guest = new Guest(loginId, password, age, hkID);
					backEnd.addUser(guest);
					EMS.PrintHeader("Guest Created!");
					EMS.AccountManagement();
				} else if (userAccountType.equals("2")) {
					System.out.println("Name: ");
					String name = EMS.readInput();
					Member member = new Member(loginId, password, name, age, hkID);
					backEnd.addUser(member);
					EMS.PrintHeader("Member Created!");
					EMS.AccountManagement();
				}
			} else if (accountType.equals("2")) {
				System.out.println("Vendor Name: ");
				String name = EMS.readInput();
				backEnd.createNewVendor(loginId, password, name);
				EMS.PrintHeader("Vendor Created!");
				EMS.AccountManagement();
			}
		} else {
			EMS.PrintHeader("Invalid Input!");
			EMS.AccountManagement();
		}

	}

	public static String readInput() {
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
