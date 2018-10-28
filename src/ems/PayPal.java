package ems;

import java.util.ArrayList;

public class PayPal extends Payment {
    private String loginMail, password;
    private ArrayList<CreditCard> card;

    public PayPal(double a, String l, String p){
        super(a);
        this.loginMail = l;
        this.password = p;
        this.card = new ArrayList<CreditCard>();
    }

    public String getLogin(){
        return this.loginMail;
    }

    public boolean login(){
        //call api to login paypal
        //login(this.loginMail, this.password);
        boolean status = true; //server return
        return status;
    }

    public double getBalance(){
        //call api get return debit balance
        double bal = 999999.99;
        return bal;
    }

    public void connectCreditCard(CreditCard c){
        this.card.add(c);
    }

    private CreditCard getDefaultCard(){
        //call server api to get default payment creditcard
        if(this.card.size() > 0){
            //get default card
            int index = 0;
            return this.card.get(index);
        }
        return null;
    }

    public boolean buy(){
        boolean serverConn = true; //status of server
        if(this.getBalance() >= this.getAmount()){
            return true;
        }else if(this.card.size() > 0){ //not enough balance but got credit card linked
            CreditCard c = this.getDefaultCard();
            return c.buy();
        }
        return false;
    }
}
