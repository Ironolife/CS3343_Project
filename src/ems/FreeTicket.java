package ems;
public class FreeTicket extends Ticket {
	
	public FreeTicket(Event event, double price, int seat) {
		super(event, price, seat);
	}
	
	@Override
	public double getPrice() {
		return 0;
	}

}
