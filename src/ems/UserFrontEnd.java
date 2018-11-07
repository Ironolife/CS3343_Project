package ems;

import java.util.ArrayList;
import java.util.Date;

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
			if(event.getEndTime().compareTo(new Date()) < 0) {
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
				else {
					EMS.PrintHeader("Invalid Operation");
					operation = "-1";
				}
			}
		}
		
	}
	
	private void displayPurchaseHistory() {
		
	}
}
