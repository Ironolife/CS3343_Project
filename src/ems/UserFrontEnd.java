package ems;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class UserFrontEnd extends FrontEnd{
	
	private User user;
	
	public UserFrontEnd(User user) {
		
		this.user = user;
		
		if(user instanceof Guest) {
			EMS.PrintHeader("- Welcome Guest -");
		}
		else if(user instanceof Member) {
			EMS.PrintHeader("- Welcome " + ((Member)user).getName() + " -");
		}
		
		this.userOperations();
		
	}
	
	protected void userOperations() {
		
		this.baseOperations();
		System.out.println("3: My Upcoming Events");
		System.out.println("4: Purchase Ticket");
		System.out.println("5: Review Event");
		System.out.println("6: My Account");
		System.out.println("7: Purchase History");
		System.out.println("8: Logout");
		String operation = this.readInput();
		
		if(operation.equals("1")) {
			this.displayEvents();
			this.userOperations();
		}
		else if(operation.equals("2")) {
			this.searchEvents();
			this.userOperations();
		}
		else if(operation.equals("3")) {
			this.displayUpcomingEvents();
			this.userOperations();
		}
		else if(operation.equals("4")) {
			this.purchaseTicket();
			this.userOperations();
		}
		else if(operation.equals("5")) {
			this.reviewEvent();
			this.userOperations();
		}
		else if(operation.equals("6")) {
			this.displayAccountOperations();
			this.userOperations();
		}
		else if(operation.equals("7")) {
			this.displayPurchaseHistory();;
			this.userOperations();
		}
		else if(operation.equals("8")) {
			
		}
		else {
			EMS.PrintHeader("Invalid Operation");
			this.userOperations();
		}
		
	}
	
	private void displayUpcomingEvents() {
		
		EMS.PrintHeader("- Upcoming Events -");
		
		ArrayList<Ticket> tickets = this.user.getTickets();
		ArrayList<Event> upcomingEvents = new ArrayList<Event>();
		for(Ticket ticket: tickets) {
			Event event = ticket.getEvent();
			if(event.getEndTime().compareTo(new Date()) > 0) {
				upcomingEvents.add(event);
			}
		}
		
		int count = 1;
		for(Event event: upcomingEvents) {
			System.out.println(count + ": " + event.getName());
			count++;
		}
		
		int eventIndex = -1;
		while(eventIndex == -1) {
			try {
				System.out.println("Select an event to view details (0 to exit): ");
				eventIndex = Integer.parseInt(this.readInput());
				while(eventIndex < 0 || eventIndex > upcomingEvents.size()) {
					EMS.PrintHeader("Invalid Input");
					System.out.println("Select an event to view details (0 to exit): ");
					eventIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Input!");
				eventIndex = -1;
			}
		}
		System.out.println();
		if(eventIndex > 0) {
			Event event = upcomingEvents.get(eventIndex - 1);
			this.displayEventInfo(event);
		}
		
	}
	
	private void purchaseTicket() {
		
		EMS.PrintHeader("- Purchase Ticket -");
		
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> availableEvents = new ArrayList<Event>();
		ArrayList<Event> myEvents = new ArrayList<Event>();
		for(Ticket ticket: this.user.getTickets()) {
			myEvents.add(ticket.getEvent());
		}
		for(Event event: backEnd.getEvents()) {
			if(event.getEndTime().compareTo(new Date()) > 0 && !myEvents.contains(event) && !event.isSoldOut()) {
				availableEvents.add(event);
			}
		}
		
		if(this.user.getAge() < 18) {
			Iterator<Event> eventIterator = availableEvents.iterator();
			while(eventIterator.hasNext()) {
				Event event = eventIterator.next();
				if(event.getIsMature() == true) {
					eventIterator.remove();
				}
			}
		}
		
		int eventIndex = -1;
		while (eventIndex == -1) {
			try {
				System.out.println("Please Select an Event: ");
				for(int i=0; i<availableEvents.size(); i++) {
					System.out.println((i+1) + ": " + availableEvents.get(i).getName());
				}
				eventIndex = Integer.parseInt(this.readInput());
				while (eventIndex < 1 || eventIndex > availableEvents.size()) {
					EMS.PrintHeader("Invalid Event!");
					System.out.println("Please Select an Event: ");
					for(int i=0; i<availableEvents.size(); i++) {
						System.out.println((i+1) + ": " + availableEvents.get(i).getName());
					}
					eventIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Event!");
				eventIndex = -1;
			}
		}
		Event event = availableEvents.get(eventIndex - 1);
		
		int normalTicketCount = 0;
		int vipTicketCount = 0;
		double normalTicketPrice = -1;
		double vipTicketPrice = -1;
		
		for(Ticket ticket: event.getTickets()) {
			if(ticket.getStatus() == 0) {
				if(ticket instanceof VIPTicket) {
					vipTicketCount++;
					vipTicketPrice = ticket.getPrice();
				}
				else {
					normalTicketCount++;
					normalTicketPrice = ticket.getPrice();
				}
			}
		}
		
		Ticket ticketToPurchase = null;
		while(ticketToPurchase == null) {
			System.out.println("Choose ticket type: ");
			System.out.print("1: Normal Ticket, " + normalTicketCount + " left");
			if(normalTicketPrice != -1) {
				System.out.print(", " + (normalTicketPrice * this.user.getDiscount()) + " HKD");
			}
			System.out.println();
			System.out.print("2: VIP Ticket, " + vipTicketCount + " left");
			if(vipTicketPrice != -1) {
				System.out.print(", " + (vipTicketPrice * this.user.getDiscount()) + " HKD");
			}
			System.out.println();
			String ticketType = this.readInput();
			
			if(ticketType.equals("1")) {
				if(normalTicketCount > 0) {
					for(Ticket ticket: event.getTickets()) {
						if(ticket.getStatus() == 0 && !(ticket instanceof VIPTicket)) {
							ticketToPurchase = ticket;
							break;
						}
					}
				}
				else {
					EMS.PrintHeader("Normal Ticket sold out!");
				}
			}
			else if(ticketType.equals("2")) {
				if(vipTicketCount > 0) {
					for(Ticket ticket: event.getTickets()) {
						if(ticket.getStatus() == 0 && ticket instanceof VIPTicket) {
							ticketToPurchase = ticket;
							break;
						}
					}
				}
				else {
					EMS.PrintHeader("VIP Ticket sold out!");
				}
			}
			else {
				EMS.PrintHeader("Invalid Ticket Type!");
			}
		}
		
		System.out.println("Use coupon? (Y/N): ");
		String useCoupon = this.readInput();
		while(!useCoupon.equals("Y") && !useCoupon.equals("N")) {
			EMS.PrintHeader("Invalid Input");
			System.out.println("Use coupon? (Y/N): ");
			useCoupon = this.readInput();
		}
		
		Coupon couponToUse = null;
		if(useCoupon.equals("Y")) {
			while(couponToUse == null) {
				System.out.println("Coupon Code: ");
				String code = this.readInput();
				
				for(Coupon coupon: backEnd.getCoupons()) {
					if(coupon.getCode().equals(code) && coupon.getEvent()==event && coupon.getExpiryDate().compareTo(new Date()) > 0) {
						couponToUse = coupon;
						EMS.PrintHeader("Coupon Applied!");
						break;
					}
				}
				
				if(couponToUse == null) {
					EMS.PrintHeader("Invalid Coupon Code");
				}
			}
		}
		
		Transaction transaction = new Transaction(ticketToPurchase, this.user, event.getVendor());
		
		if(couponToUse != null) {
			transaction.useCoupon(couponToUse);
		}
		
		if(transaction.getInitialAmount() != transaction.getDiscountedAmount()) {
			System.out.println("Discounted Price: " + transaction.getDiscountedAmount());
		}
		else {
			System.out.println("Price: " + transaction.getInitialAmount());
		}
		
		String method = "-1";
		while(method.equals("-1")) {
			System.out.println("Choose payment method: ");
			System.out.println("1: Cash");
			System.out.println("2: Credit Card");
			if(this.user instanceof Member) {
				System.out.println("3: Account Balance");
			}
			method = this.readInput();
			
			if(method.equals("1")) {
				System.out.println("Please insert cash . . .");
				try {
					Thread.sleep(3000); //simulate action of inserting cash
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				transaction.completeTransaction();
				backEnd.createNewTransaction(transaction);
				EMS.PrintHeader("Ticket Purchased!");
			}
			else if(method.equals("2")) {
				
				ArrayList<CreditCard> creditCards = this.user.getCreditCard();
				int creditCardIndex = -1;
				while (creditCardIndex == -1) {
					try {
						System.out.println("Please Select a Credit Card (0 to added a new card): ");
						for(int i=0; i<creditCards.size(); i++) {
							System.out.println((i+1) + ": " + creditCards.get(i).getCardNumber());
						}
						creditCardIndex = Integer.parseInt(this.readInput());
						while (creditCardIndex < 0 || creditCardIndex > creditCards.size()) {
							EMS.PrintHeader("Invalid Input!");
							System.out.println("Please Select a Credit Card (0 to added a new card): ");
							for(int i=0; i<creditCards.size(); i++) {
								System.out.println((i+1) + ": " + creditCards.get(i).getCardNumber());
							}
							creditCardIndex = Integer.parseInt(this.readInput());
						}
					} catch (NumberFormatException e) {
						EMS.PrintHeader("Invalid Input!");
						creditCardIndex = -1;
					}
				}
				if(creditCardIndex == 0) {
					CreditCard creditCard = null;
					while(creditCard == null) {
						System.out.println("Card Number: ");
						String cardNumber = this.readInput();
						int securityCode = -1;
						while (securityCode == -1) {
							try {
								System.out.println("Security Code: ");
								securityCode = Integer.parseInt(this.readInput());
								while (securityCode < 0) {
									EMS.PrintHeader("Invalid Security Code!");
									System.out.println("Security Code: ");
									securityCode = Integer.parseInt(this.readInput());
								}
							} catch (NumberFormatException e) {
								EMS.PrintHeader("Invalid Security Code!");
								securityCode = -1;
							}
						}
						Date expiryDate = null;
						while(expiryDate == null) {
							System.out.println("Expiry Date (MM/YY): ");
							String shortDateString = this.readInput();
							String longDateString = "20" + shortDateString.split("/")[1] + "-" + shortDateString.split("/")[0] + "-01 00:00"; 
							expiryDate = DateUtils.parseDate(longDateString);
						}
						
						CreditCard cardToValidate = new CreditCard(cardNumber, securityCode, expiryDate);
						if(cardToValidate.validate() == false) {
							EMS.PrintHeader("Credit Card validation failed!");
						}
						else {
							creditCard = cardToValidate;
							EMS.PrintHeader("Validation Success!");
							this.user.addCreditCard(creditCard);
						}
					}
					System.out.println("Payment in progress . . .");
					try {
						Thread.sleep(3000); //simulate credit card payment
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					transaction.completeTransaction();
					backEnd.createNewTransaction(transaction);
					EMS.PrintHeader("Ticket Purchased!");
				}
				else {
					CreditCard creditCard = creditCards.get(creditCardIndex - 1);
					if(creditCard.validate() == true) {
						System.out.println("Payment in progress . . .");
						try {
							Thread.sleep(3000); //simulate credit card payment
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						transaction.completeTransaction();
						backEnd.createNewTransaction(transaction);
						EMS.PrintHeader("Ticket Purchased!");
					}
					else {
						EMS.PrintHeader("Credit Card validation failed!");
						this.displayAccountOperations();
					}
				}
			}
			else if(method.equals("3") && this.user instanceof Member) {
				Member member = (Member)this.user;
				if(member.getBalance() >= transaction.getDiscountedAmount()) {
					member.substractBalance(transaction.getDiscountedAmount());
					System.out.println(transaction.getDiscountedAmount() + " deducted from balance. " + member.getBalance() + " left in balance.");
					transaction.completeTransaction();
					backEnd.createNewTransaction(transaction);
					EMS.PrintHeader("Ticket Purchased!");
				}
				else {
					EMS.PrintHeader("Insufficient balance! Please add " + (transaction.getDiscountedAmount() - member.getBalance()) + " to balance: ");
					this.addBalance(transaction.getDiscountedAmount() - member.getBalance());
					member.substractBalance(transaction.getDiscountedAmount());
					System.out.println(transaction.getDiscountedAmount() + " deducted from balance. " + member.getBalance() + " left in balance.");
					transaction.completeTransaction();
					backEnd.createNewTransaction(transaction);
					EMS.PrintHeader("Ticket Purchased!");
				}
			}
			else {
				EMS.PrintHeader("Invalid input!");
				method = "-1";
			}
		}
		
	}
	
	private void reviewEvent() {
		
		EMS.PrintHeader("- Review Event -");
		
		ArrayList<Ticket> tickets = this.user.getTickets();
		ArrayList<Event> attendedEvents = new ArrayList<Event>();
		for(Ticket ticket: tickets) {
			Event event = ticket.getEvent();
			if(ticket.getStatus() > 1) {
				attendedEvents.add(event);
			}
		}
		
		int count = 1;
		for(Event event: attendedEvents) {
			System.out.println(count + ": " + event.getName());
			count++;
		}
		
		int eventIndex = -1;
		while(eventIndex == -1) {
			try {
				System.out.println("Select an event to review (0 to exit): ");
				eventIndex = Integer.parseInt(this.readInput());
				while(eventIndex < 0 || eventIndex > attendedEvents.size()) {
					EMS.PrintHeader("Invalid Input");
					System.out.println("Select an event to review (0 to exit): ");
					eventIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Input!");
				eventIndex = -1;
			}
		}
		System.out.println();
		if(eventIndex > 0) {
			Event event = attendedEvents.get(eventIndex - 1);
			
			double rating = -1;
			while (rating == -1) {
				try {
					System.out.println("Rating (0 to 5): ");
					rating = Double.parseDouble(this.readInput());
					while (rating < 0 || rating > 5) {
						EMS.PrintHeader("Invalid Rating!");
						System.out.println("Rating (0 to 5): ");
						rating = Double.parseDouble(this.readInput());
					}
				} catch (NumberFormatException e) {
					EMS.PrintHeader("Invalid Rating!");
					rating = -1;
				}
			}
			
			System.out.println("Comment: ");
			String comment = this.readInput();
			
			Review review = new Review((Member)this.user, rating, comment);
			event.addReview(review);
			BackEnd backEnd = BackEnd.getInstance();
			backEnd.createNewReview(review);
			
			EMS.PrintHeader("Review Submitted!");
		}
		
	}
	
	private void displayAccountOperations() {
		
		EMS.PrintHeader("- My Account -");
		
		if(this.user instanceof Guest) {
			
			String operation = "-1";
			while(operation.equals("-1")) {
				System.out.println("Choose operations: ");
				System.out.println("1: View Account Details");
				System.out.println("2: Upgrade to Member");
				operation = this.readInput();
				
				if(operation.equals("1")) {
					EMS.PrintHeader("- Account Details -");
					System.out.println("HKID: " + this.user.getHKID());
					System.out.println("Age: " + this.user.getAge());
					System.out.println("Tickets Count: " + this.user.getTickets().size());
				}
				else if(operation.equals("2")) {
					EMS.PrintHeader("- Upgrade Account -");
					System.out.println("Name: ");
					String name = this.readInput();
					this.user = User.upgradeAccount((Guest)this.user, name);
				}
				else {
					EMS.PrintHeader("Invalid Operation");
					operation = "-1";
				}
			}
			
		}
		else if(this.user instanceof Member) {
			
			String operation = "-1";
			while(operation.equals("-1")) {
				System.out.println("Choose operations: ");
				System.out.println("1: View Account Details");
				System.out.println("2: Add Balance");
				operation = this.readInput();
				
				if(operation.equals("1")) {
					EMS.PrintHeader("- Account Details -");
					
					Member member = (Member)this.user;
					System.out.println("Name: " + member.getName());
					System.out.println("HKID: " + member.getHKID());
					System.out.println("Age: " + member.getAge());
					System.out.println("Tickets Count: " + member.getTickets().size());
					System.out.println("Balance: " + member.getBalance());
				}
				else if(operation.equals("2")) {
					EMS.PrintHeader("- Add Balance -");

					Member member = (Member)this.user;
					
					double amount = -1;
					while (amount == -1) {
						try {
							System.out.println("Amount: ");
							amount = Double.parseDouble(this.readInput());
							while (amount < 0) {
								EMS.PrintHeader("Invalid Amount!");
								System.out.println("Amount: ");
								amount = Double.parseDouble(this.readInput());
							}
						} catch (NumberFormatException e) {
							EMS.PrintHeader("Invalid Amount!");
							amount = -1;
						}
					}
					this.addBalance(amount);
				}
				else {
					EMS.PrintHeader("Invalid Operation");
					operation = "-1";
				}
			}
		}
		
	}
	
	private void displayPurchaseHistory() {
		
		EMS.PrintHeader("- Purchase History -");
		
		int count = 1;
		double total = 0;
		for(Transaction transaction: this.user.getTransactions()) {
			System.out.print(count + ": ");
			String ticketType;
			if(transaction.getTicket() instanceof VIPTicket) {
				ticketType = "VIP Ticket";
			}
			else {
				ticketType = "Normal Ticket";
			}
			System.out.print("Purchased a " + ticketType);
			System.out.print(" for " + transaction.getTicket().getEvent().getName());
			System.out.print(" at " + transaction.getDiscountedAmount());
			System.out.print(" from " + transaction.getVendor().getName());
			System.out.println(" on " + transaction.getDate() + ".");
			count++;
			total+= transaction.getDiscountedAmount();
		}
		System.out.println("----------");
		System.out.println((count - 1) + " transactions, " + total + " spent.");
		System.out.println();
	}
	
	private void addBalance(double amount) {
		
		Member member = (Member)this.user;
		
		String method = "-1";
		while(method.equals("-1")) {
			System.out.println("Choose payment method: ");
			System.out.println("1: Cash");
			System.out.println("2: Credit Card");
			method = this.readInput();
			
			if(method.equals("1")) {
				System.out.println("Please insert cash . . .");
				try {
					Thread.sleep(3000); //simulate action of inserting cash
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				member.addBalance(amount);
				EMS.PrintHeader("Balance addded!");
			}
			else if(method.equals("2")) {
				
				ArrayList<CreditCard> creditCards = member.getCreditCard();
				int creditCardIndex = -1;
				while (creditCardIndex == -1) {
					try {
						System.out.println("Please Select a Credit Card (0 to added a new card): ");
						for(int i=0; i<creditCards.size(); i++) {
							System.out.println((i+1) + ": " + creditCards.get(i).getCardNumber());
						}
						creditCardIndex = Integer.parseInt(this.readInput());
						while (creditCardIndex < 0 || creditCardIndex > creditCards.size()) {
							EMS.PrintHeader("Invalid Input!");
							System.out.println("Please Select a Credit Card (0 to added a new card): ");
							for(int i=0; i<creditCards.size(); i++) {
								System.out.println((i+1) + ": " + creditCards.get(i).getCardNumber());
							}
							creditCardIndex = Integer.parseInt(this.readInput());
						}
					} catch (NumberFormatException e) {
						EMS.PrintHeader("Invalid Input!");
						creditCardIndex = -1;
					}
				}
				if(creditCardIndex == 0) {
					CreditCard creditCard = null;
					while(creditCard == null) {
						System.out.println("Card Number: ");
						String cardNumber = this.readInput();
						int securityCode = -1;
						while (securityCode == -1) {
							try {
								System.out.println("Security Code: ");
								securityCode = Integer.parseInt(this.readInput());
								while (securityCode < 0) {
									EMS.PrintHeader("Invalid Security Code!");
									System.out.println("Security Code: ");
									securityCode = Integer.parseInt(this.readInput());
								}
							} catch (NumberFormatException e) {
								EMS.PrintHeader("Invalid Security Code!");
								securityCode = -1;
							}
						}
						Date expiryDate = null;
						while(expiryDate == null) {
							System.out.println("Expiry Date (MM/YY): ");
							String shortDateString = this.readInput();
							String longDateString = "20" + shortDateString.split("/")[1] + "-" + shortDateString.split("/")[0] + "-01 00:00"; 
							expiryDate = DateUtils.parseDate(longDateString);
						}
						
						CreditCard cardToValidate = new CreditCard(cardNumber, securityCode, expiryDate);
						if(cardToValidate.validate() == false) {
							EMS.PrintHeader("Credit Card validation failed!");
						}
						else {
							creditCard = cardToValidate;
							EMS.PrintHeader("Validation Success!");
							member.addCreditCard(creditCard);
						}
					}
					System.out.println("Payment in progress . . .");
					try {
						Thread.sleep(3000); //simulate credit card payment
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					member.addBalance(amount);
					EMS.PrintHeader("Balance addded!");
				}
				else {
					CreditCard creditCard = creditCards.get(creditCardIndex - 1);
					if(creditCard.validate() == true) {
						System.out.println("Payment in progress . . .");
						try {
							Thread.sleep(3000); //simulate credit card payment
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						member.addBalance(amount);
						EMS.PrintHeader("Balance addded!");
					}
					else {
						EMS.PrintHeader("Credit Card validation failed!");
						this.displayAccountOperations();
					}
				}
			}
			else {
				EMS.PrintHeader("Invalid input!");
				method = "-1";
			}
		}
		
	}
}
