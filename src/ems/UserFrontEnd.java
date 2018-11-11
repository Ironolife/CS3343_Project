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
			if(this.user instanceof Guest) {
				EMS.PrintHeader("Only Members can review events!");
			}
			else {
				this.reviewEvent();
			}
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
		
		//Get user event list and filter for upcoming event
		ArrayList<Event> upcomingEvents = new ArrayList<Event>();
		for(Ticket ticket: this.user.getTickets()) {
			Event event = ticket.getEvent();
			if(event.getEndTime().compareTo(new Date()) > 0) {
				upcomingEvents.add(event);
			}
		}
		
		//Print event list
		this.printEventList(upcomingEvents);
		
		//Event selection
		int eventIndex = this.listSelection(upcomingEvents.size(), "Select an Event to view details (0 to exit)");
		System.out.println();
		
		//Display details for selected event
		if(eventIndex > 0) {
			Event event = upcomingEvents.get(eventIndex - 1);
			this.displayEventInfo(event);
		}
		
	}
	
	private void purchaseTicket() {
		
		EMS.PrintHeader("- Purchase Ticket -");
		
		//Get user event list
		ArrayList<Event> myEvents = new ArrayList<Event>();
		for(Ticket ticket: this.user.getTickets()) {
			myEvents.add(ticket.getEvent());
		}
		
		//Get event list and filter for available events
		ArrayList<Event> availableEvents = new ArrayList<Event>();
		for(Event event: backEnd.getEvents()) {
			if(event.getEndTime().compareTo(new Date()) > 0 
					&& !myEvents.contains(event) 
					&& !event.isSoldOut()
					&& (this.user.getAge() > 18 || event.getIsMature() == false)) {
				availableEvents.add(event);
			}
		}
		
		//Print event list
		this.printEventList(availableEvents);
		
		//Event selection
		int eventIndex = this.listSelection(availableEvents.size(), "Select an Event (0 to exit)");
		
		if(eventIndex > 0) {
			
			Event event = availableEvents.get(eventIndex - 1);
			
			//get event ticket counts and price
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
			
			//Read ticket to purchase
			Ticket ticketToPurchase = null;
			while(ticketToPurchase == null) {
				
				System.out.println("Choose ticket type: ");
				System.out.print("1: Normal Ticket, " + normalTicketCount + " left");
				if(normalTicketPrice != -1) {
					System.out.print(", " + Transaction.round((normalTicketPrice * this.user.getDiscount()), 1) + " HKD");
				}
				System.out.println();
				System.out.print("2: VIP Ticket, " + vipTicketCount + " left");
				if(vipTicketPrice != -1) {
					System.out.print(", " + Transaction.round((vipTicketPrice * this.user.getDiscount()), 1) + " HKD");
				}
				System.out.println();
				
				//Read ticket type
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
			
			//Read coupon
			String useCoupon = null;
			while(useCoupon == null) {
				System.out.println("Use coupon? (Y/N): ");
				useCoupon = this.readInput();
				if(!useCoupon.equals("Y") && !useCoupon.equals("N")) {
					EMS.PrintHeader("Invalid Input");
					useCoupon = null;
				}
			}
			
			Coupon couponToUse = null;
			if(useCoupon.equals("Y")) {
				while(couponToUse == null) {
					System.out.println("Coupon Code (0 to cancel): ");
					String code = this.readInput();
					
					if(code.equals("0")) {
						break;
					}
					
					//Get coupon and validate
					for(Coupon coupon: backEnd.getCoupons()) {
						if(coupon.getCode().equals(code) 
								&& coupon.getEvent() == event 
								&& coupon.getExpiryDate().compareTo(new Date()) > 0) {
							couponToUse = coupon;
							break;
						}
					}
					
					if(couponToUse == null) {
						EMS.PrintHeader("Invalid Coupon Code");
					}
				}
			}
			
			//Initialize Transaction
			Transaction transaction = new Transaction(ticketToPurchase, this.user, event.getVendor());
			
			//Apply Coupon discount
			if(couponToUse != null) {
				transaction.useCoupon(couponToUse);
				EMS.PrintHeader("Coupon Applied!");
			}
			
			//Print Transaction price
			if(transaction.getInitialAmount() != transaction.getDiscountedAmount()) {
				System.out.println("Discounted Price: " + transaction.getDiscountedAmount());
			}
			else {
				System.out.println("Price: " + transaction.getInitialAmount());
			}
			
			//Read Payment method
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
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(transaction.getDiscountedAmount() + " received.");
					
					//Complete transaction
					transaction.completeTransaction();
					backEnd.createNewTransaction(transaction);
					
					EMS.PrintHeader("Ticket Purchased!");
				}
				else if(method.equals("2")) {
					
					CreditCard creditCard = this.creditCardPayment();
					
					System.out.println("Payment in progress . . .");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(transaction.getDiscountedAmount() + " deducted from credit card " + creditCard.getCardNumber() + ".");
					
					//Complete transaction
					transaction.completeTransaction();
					backEnd.createNewTransaction(transaction);
					
					EMS.PrintHeader("Ticket Purchased!");
					
				}
				else if(method.equals("3") && this.user instanceof Member) {
					
					Member member = (Member)this.user;
					
					//Validate balance
					if(member.getBalance() < transaction.getDiscountedAmount()) {
						EMS.PrintHeader("Insufficient balance! Please add " + (transaction.getDiscountedAmount() - member.getBalance()) + " to balance: ");
						this.addBalance(transaction.getDiscountedAmount() - member.getBalance());
					}
					
					System.out.println("Payment in progress . . .");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					member.substractBalance(transaction.getDiscountedAmount());
					System.out.println(transaction.getDiscountedAmount() + " deducted from balance. " + member.getBalance() + " left in balance.");
					
					//Complete transaction
					transaction.completeTransaction();
					backEnd.createNewTransaction(transaction);
					EMS.PrintHeader("Ticket Purchased!");
					
				}
				else {
					EMS.PrintHeader("Invalid input!");
					method = "-1";
				}
			}
			
		}
		
	}
	
	private void reviewEvent() {
		
		EMS.PrintHeader("- Review Event -");
		
		//Get attended events list
		ArrayList<Event> attendedEvents = new ArrayList<Event>();
		for(Ticket ticket: this.user.getTickets()) {
			Event event = ticket.getEvent();
			if(ticket.getStatus() > 1) {
				attendedEvents.add(event);
			}
		}
		
		//Filter for unreviewed event
		Iterator<Event> eventIterator = attendedEvents.iterator();
		while(eventIterator.hasNext()) {
			Event event = eventIterator.next();
			for(Review review: event.getReviews()) {
				if(this.user == review.getMember()) {
					eventIterator.remove();
					break;
				}
			}
		}
		
		//Print event list
		this.printEventList(attendedEvents);
		
		//Event selection
		int eventIndex = this.listSelection(attendedEvents.size(), "Select an Event to review (0 to exit)");
		
		if(eventIndex > 0) {
			
			Event event = attendedEvents.get(eventIndex - 1);
			
			//Read rating
			double rating = -1;
			while (rating == -1) {
				rating = this.readDoubleInput("Rating (0 to 5)");
				if(rating > 5) {
					EMS.PrintHeader("Invalid Input");
					rating = -1;
				}
			}
			
			//Read comment
			System.out.println("Comment: ");
			String comment = this.readInput();
			
			//Create review
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
					
					//Print details
					System.out.println("HKID: " + this.user.getHKID());
					System.out.println("Age: " + this.user.getAge());
					System.out.println("Tickets Count: " + this.user.getTickets().size());
				}
				else if(operation.equals("2")) {
					
					EMS.PrintHeader("- Upgrade Account -");
					
					//Read name
					System.out.println("Name: ");
					String name = this.readInput();
					
					//Upgrade account
					this.user = User.upgradeAccount((Guest)this.user, name);
				}
				else {
					EMS.PrintHeader("Invalid Operation");
					operation = "-1";
				}
				
			}
			
		}
		else if(this.user instanceof Member) {
			
			Member member = (Member)this.user;
			
			String operation = "-1";
			while(operation.equals("-1")) {
				
				System.out.println("Choose operations: ");
				System.out.println("1: View Account Details");
				System.out.println("2: Add Balance");
				operation = this.readInput();
				
				if(operation.equals("1")) {
					
					EMS.PrintHeader("- Account Details -");
					
					//Print details
					System.out.println("Name: " + member.getName());
					System.out.println("HKID: " + member.getHKID());
					System.out.println("Age: " + member.getAge());
					System.out.println("Tickets Count: " + member.getTickets().size());
					System.out.println("Balance: " + member.getBalance());
				}
				else if(operation.equals("2")) {
					
					EMS.PrintHeader("- Add Balance -");
					
					double amount = this.readDoubleInput("Amount");
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
		
		//Print individual transaction
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
		
		//Print summary
		System.out.println("----------");
		System.out.println((count - 1) + " transactions, " + total + " spent.");
		System.out.println();
	}
	
	private void printCreditCardList(ArrayList<CreditCard> creditCards) {
		
		int count = 1;
		for(CreditCard creditCard: creditCards) {
			System.out.println(count + ": " + creditCard.getCardNumber());
			count++;
		}
		
	}
	
	private CreditCard creditCardPayment() {
		
		//Get user credit card list
		ArrayList<CreditCard> creditCards = this.user.getCreditCard();
		
		//print credit card list
		this.printCreditCardList(creditCards);
		
		//Credit card selection
		int creditCardIndex = this.listSelection(creditCards.size(), "Select a Credit Card (0 to added a new card)");

		CreditCard creditCard = null;
		if(creditCardIndex == 0) {
			
			//Create Credit Card
			while(creditCard == null) {
				
				//Read card Number
				System.out.println("Card Number: ");
				String cardNumber = this.readInput();
				
				//Read security code
				int securityCode = this.readIntInput("Security Code");
				
				//Read expiry date
				Date expiryDate = null;
				while(expiryDate == null) {
					System.out.println("Expiry Date (MM/YY): ");
					String shortDateString = this.readInput();
					String longDateString = "20" + shortDateString.split("/")[1] + "-" + shortDateString.split("/")[0] + "-01 00:00"; 
					expiryDate = DateUtils.parseDate(longDateString);
				}
				
				creditCard = new CreditCard(cardNumber, securityCode, expiryDate);
				if(creditCard.validate() == false) {
					EMS.PrintHeader("Credit Card validation failed!");
					creditCard = null;
				}
				else {
					EMS.PrintHeader("Validation Success!");
					this.user.addCreditCard(creditCard);
				}
			}
		}
		else {
			creditCard = creditCards.get(creditCardIndex - 1);
			if(creditCard.validate() == true) {
				EMS.PrintHeader("Validation Success!");
			}
			else {
				EMS.PrintHeader("Credit Card validation failed!");
				return this.creditCardPayment();
			}
		}
		return creditCard;
		
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
				System.out.println(amount + " received.");
				
				//Add balance
				member.addBalance(amount);
				
				EMS.PrintHeader("Balance addded!");
			}
			else if(method.equals("2")) {
				
				CreditCard creditCard = this.creditCardPayment();
				
				System.out.println("Payment in progress . . .");
				try {
					Thread.sleep(3000); //simulate credit card payment
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(amount + " deducted from credit card " + creditCard.getCardNumber() + ".");
				
				//Add balance
				member.addBalance(amount);
				
				EMS.PrintHeader("Balance addded!");
			}
			else {
				
				EMS.PrintHeader("Invalid input!");
				method = "-1";
				
			}
		}
		
	}
}
