package ems;

import java.util.Date;

public class CreditCard {
	
    private String cardNumber;
    private int securityCode;
    private Date expiryDate;

    public CreditCard(String cardNumber, int securityCode, Date expiryDate){
    	
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expiryDate = expiryDate;
        
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public int getSecurityCode() {
        return this.securityCode;
    }
    
    public Date getExpiryDate() {
    	return this.expiryDate;
    }

    public String getCardType(){
    	
        switch(this.cardNumber.charAt(0)){
            case '3':
                return "American Express";
            case '4':
                return "Visa";
            case '5':
                return "Mastercard";
            default:
                return null;
        }
        
    }
    
    public boolean validateSecurityCode() {
    	
    	if(this.getCardType() == "American Express") {
    		return (this.securityCode > 999 && this.securityCode < 10000);
    	}
    	else if(this.getCardType() != null) {
    		return (this.securityCode > 99 && this.securityCode < 1000);
    	}
    	else {
    		return false;
    	}
    	
    }

    public boolean validate(){
    	
    	if(this.getCardType() == null) {
    		return false;
    	}
    	else if(this.cardNumber.length() != 16) {
    		return false;
    	}
    	else if(this.validateSecurityCode() != true) {
    		return false;
    	}
    	else if(this.getExpiryDate().compareTo(new Date()) < 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    	
    }

}
