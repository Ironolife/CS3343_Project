package ems;

public class Payment {
	
	private int transactionId;
	private double amount;
	private String payer;
	private String receiver;
	
	public Payment(int transactionId, double amount, String payer, String receiver) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.payer = payer;
		this.receiver = receiver;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
	
}
