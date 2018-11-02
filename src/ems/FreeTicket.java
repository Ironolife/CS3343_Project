package ems;
public class FreeTicket extends Ticket {
	
	private double price;
	
	public VIPTicket(Event event, double price, int seat, double priceMultiplier) {
		super(event, price, seat);
	}
	
	@Override
	public double getPrice() {
		return 0;
	}

}
