package ems;

public class CreditCard extends Payment {
    private String cardno;
    private int cvv;

    public CreditCard(String n, int c, double a){
        super(a);
        this.cardno = n;
        this.cvv = c;
    }

    public String getCardno(){
        return this.cardno;
    }

    public int getCvv(){
        return this.cvv;
    }

    public String cardType(){
        switch(this.cardno.charAt(0)){
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

    public boolean validCard(){
        return (this.cardType() != null && this.cardno.length() == 16) ;
    }

    public boolean validCVV(){
        int length = (int)(Math.log10(this.cvv) + 1);
        return (length == 3) ;
    }

    public double getCreditLimit(){
        //call server to check credit limit
        double limit = 99999.99;
        return limit;
    }

    private boolean deductCredit(){
        boolean serverConn = true;
        //deduct credit via api
        //deduct(this.cardno, this.cvv, this.getAmount());
        return serverConn;
    }

    public boolean buy(){
        boolean serverConn = true; //status of server
        if(serverConn && this.validCard() && this.validCVV()){
            if(this.getAmount() <= this.getCreditLimit()){
                return this.deductCredit();
            }
        }
        return false;
    }

}
