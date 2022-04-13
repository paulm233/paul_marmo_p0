package dev.marmo.entities;

public class CheckingAccount {

    private int accountNum;
    private double balance;
    private String customerName;
    private String userName;
    private String password;


    public CheckingAccount(){

    }

    public CheckingAccount(int accountNum, double balance, String customerName, String userName, String password){
        this.accountNum = accountNum;
        this.balance = balance;
        this.customerName = customerName;
        this.userName = userName;
        this.password = password;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String
    toString() {
        return "CheckingAccount{" +
                "accountNum=" + accountNum +
                ", balance=" + balance +
                ", customerName='" + customerName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
