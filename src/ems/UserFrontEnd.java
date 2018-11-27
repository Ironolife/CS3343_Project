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
	}
	
	public void userOperations() {
		this.baseOperations();
		System.out.println("3: My Upcoming Events");
		System.out.println("4: Purchase Ticket");
		System.out.println("5: Review Event");
		System.out.println("6: My Account");
		System.out.println("7: Purchase History");
		System.out.println("8: Logout");
		String operation = this.readInput();
		
		validateUserInput(operation);
	}
	
	public boolean validateUserInput(String operation) {
		boolean isRecursiveUserOperation = true;
		switch(operation) {
		case "1":
			this.displayEvents();
			break;
		case "2":
			this.searchEvents();
			break;
		case "3":
			this.displayUpcomingEvents();
			break;
		case "4":
			this.purchaseTicket();
			break;
		case "5":
			if(this.user instanceof Guest) {
				EMS.PrintHeader("Only Members can review events!");
			}
			else {
				this.reviewEvent();
			}
			break;
		case "6":
			this.displayAccountOperations();
			break;
		case "7":
			this.displayPurchaseHistory();
			break;
		case "8":
			isRecursiveUserOperation = false;
			break;
		default:
			EMS.PrintHeader("Invalid Operation");
			break;
		}
		
		return isRecursiveUserOperation;	
	}
	
	public void displayUpcomingEvents() {
		
		EMS.PrintHeader("- Upcoming Events -");
		
		
		ArrayList<Event> upcomingEvents = getUpcomingEventList();
		//Print event list
		this.printEventList(upcomingEvents);
		
		//Event selection
		int eventIndex = this.listSelection(upcomingEvents.size(), "Select an Event to view details (0 to exit)");
		System.out.println();
		
		//This line may cause array out of bound exception
		//Display details for selected event
		if(eventIndex > 0) {
			Event event = upcomingEvents.get(eventIndex - 1);
			this.displayEventInfo(event);
		}
		
	}
	
	public ArrayList<Event> getUpcomingEventList() {
		//Get user event list and filter for upcoming event
				ArrayList<Event> upcomingEvents = new ArrayList<Event>();
				for(Ticket ticket: this.user.getTickets()) {
					Event event = ticket.getEvent();
					if(event.getEndTime().compareTo(new Date()) > 0) {
						upcomingEvents.add(event);
					}
				}
				return upcomingEvents;
	}
	
	
	public ArrayList<Event> getAvailableEventList() {
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
		return availableEvents;
	}
	
	public void printTicketDetails(int normalTicketCount, int vipTicketCount, double normalTicketPrice , double vipTicketPrice) {
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
	}
	
	public Ticket validateTicketPurchase(String ticketType, Event event, int normalTicketCount, int vipTicketCount) {	
		Ticket ticketToPurchase = null;
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
		
		return ticketToPurchase;
	}
	
	public String validateCouponOption(String useCoupon) {
		if(!useCoupon.equals("Y") && !useCoupon.equals("N")) {
			EMS.PrintHeader("Invalid Input");
			useCoupon = null;
		}
		return useCoupon;
	}
	
	public Coupon consumeCoupon(Event event) {
		Coupon couponToUse = null;
		while(couponToUse == null) {
			System.out.println("Coupon Code (0 to cancel): ");
			String code = this.readInput();
			
			if(code.equals("0")) {
				return null;
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
		
		return couponToUse;
	}
	
	public void printTransactionDetail(Transaction transaction, Coupon couponToUse) {
		
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
	}
	
	
	public void handlePayment(String method, Transaction transaction) {
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
	
	public void purchaseTicket() {
		EMS.PrintHeader("- Purchase Ticket -");
		//Get user event list
		ArrayList<Event> availableEvents = getAvailableEventList();
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
				printTicketDetails(normalTicketCount, vipTicketCount, normalTicketPrice, vipTicketPrice);
				String ticketType = this.readInput();//Read ticket type
				ticketToPurchase = validateTicketPurchase(ticketType, event, normalTicketCount, vipTicketCount);
				//334 line causes infinite loop
			}
			
			//Read coupon
			String option = null;
			while(option == null) {
				System.out.println("Use coupon? (Y/N): ");
				option = this.readInput();
				validateCouponOption(option);
			}
			
			Coupon couponToUse = null;
			if(option.equals("Y"))
				couponToUse = consumeCoupon(event);
			

			//Initialize Transaction
			Transaction transaction = new Transaction(ticketToPurchase, this.user, event.getVendor());
			printTransactionDetail(transaction, couponToUse);
			
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
				
				handlePayment(method, transaction);
			}
			
		}
		
	}
	
	
	public ArrayList<Event> getAttenededEvent(){
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
		
		return attendedEvents;
	}
	
	public double validateRating() {
		//Read rating
		double rating = -1;
		while (rating == -1) {
			rating = this.readDoubleInput("Rating (0 to 5)");
			if(rating > 5) {
				EMS.PrintHeader("Invalid Input");
				rating = -1;
			}
		}
		return rating;
	}
	
	public Review createReview(Event event) {
		
		
		double rating = validateRating();
		//Read comment
		System.out.println("Comment: ");
		String comment = this.readInput();
		
		//Create review
		Review review = new Review((Member)this.user, rating, comment);
		event.addReview(review);
		BackEnd backEnd = BackEnd.getInstance();
		backEnd.createNewReview(review);
		
		EMS.PrintHeader("Review Submitted!");
		
		return review;
	}
	
	public void reviewEvent() {
		
		EMS.PrintHeader("- Review Event -");
		
		//Get attended events list
		ArrayList<Event> attendedEvents = getAttenededEvent();
		
		//Print event list
		this.printEventList(attendedEvents);
		
		//Event selection
		int eventIndex = this.listSelection(attendedEvents.size(), "Select an Event to review (0 to exit)");
		
		if(eventIndex > 0) {
			Event event = attendedEvents.get(eventIndex - 1);
			createReview(event);
		}
	}
	
	public void displayAccountOperationsAsGuest(){
		String operation = "-1";
		while(operation.equals("-1")) {
			
			System.out.println("Choose operations: ");
			System.out.println("1: View Account Details");
			System.out.println("2: Upgrade to Member");
			operation = this.readInput();
			validateAccountOperationAsGuest(operation);
		}
	}
	
	public String validateAccountOperationAsGuest(String operation) {
		if(operation.equals("1")) {
			
			EMS.PrintHeader("- Account Details -");
			
			//Print details
			System.out.println("HKID: " + this.user.getHKID());
			System.out.println("Age: " + this.user.getAge());
			System.out.println("Tickets Count: " + this.user.getTickets().size());
			System.out.println();
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
		
		return operation;
	}
	
	public void displayAccountOperationsAsUser() {
		String operation = "-1";
		while(operation.equals("-1")) {
			
			System.out.println("Choose operations: ");
			System.out.println("1: View Account Details");
			System.out.println("2: Add Balance");
			operation = this.readInput();
			validateAccountOperationAsUser(operation);
		}
	}
	
	public String validateAccountOperationAsUser(String operation) {
		Member member = (Member)this.user;
		if(operation.equals("1")) {
			
			EMS.PrintHeader("- Account Details -");
			
			//Print details
			System.out.println("Name: " + member.getName());
			System.out.println("HKID: " + member.getHKID());
			System.out.println("Age: " + member.getAge());
			System.out.println("Tickets Count: " + member.getTickets().size());
			System.out.println("Balance: " + member.getBalance());
			System.out.println();
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
		return operation;
	}
	
	
	
	public void displayAccountOperations() {
		
		EMS.PrintHeader("- My Account -");
		
		if(this.user instanceof Guest) {
			displayAccountOperationsAsGuest();
		}
		else if(this.user instanceof Member) {
			displayAccountOperationsAsUser();
		}
		
	}
	
	public void displayPurchaseHistory() {
		
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
	
	public void printCreditCardList() {
		
		int count = 1;
		for(CreditCard creditCard: this.user.getCreditCard()) {
			System.out.println(count + ": " + creditCard.getCardNumber());
			count++;
		}
		
	}
	
	public CreditCard inputCreditCardInfo() {
		CreditCard creditCard = null;
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
			//line 607 result in array out of bound exception
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
		return creditCard;
	}
	
	public CreditCard creditCardPayment() {
		
		//print credit card list
		this.printCreditCardList();
		
		//Credit card selection
		int creditCardIndex = this.listSelection(this.user.getCreditCard().size(), "Select a Credit Card (0 to added a new card)");

		CreditCard creditCard = null;
		if(creditCardIndex == 0) {
			
			//Create Credit Card
			while(creditCard == null) {
				creditCard = inputCreditCardInfo();
			}
		}
		else {
			creditCard = this.user.getCreditCard().get(creditCardIndex - 1);
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
	
	public String validateAddBalanceMethod(String method, double amount) {
		Member member = (Member)this.user;
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
		return method;
	}
	
	
	public void displayPaymentMethod() {
		System.out.println("Choose payment method: ");
		System.out.println("1: Cash");
		System.out.println("2: Credit Card");	
	}
	
	public void addBalance(double amount) {
		
		
		String method = "-1";
		while(method.equals("-1")) {
			displayPaymentMethod();
			method = this.readInput();
			
			method = validateAddBalanceMethod(method, amount);
		}
		
	}
}
