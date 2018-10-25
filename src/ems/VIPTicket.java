package ems;
public class VIPTicket extends Ticket {
	
	private double priceMultiplier;
	
	public VIPTicket(Event event, double price, int seat, double priceMultiplier) {
		super(event, price, seat);
		this.priceMultiplier = priceMultiplier;
	}
	
	@Override
	public double getPrice() {
		return super.getPrice() * this.priceMultiplier;
	}

}
