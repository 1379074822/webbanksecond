package cn.edu.buct.entity;

public class Account {
    private Integer kid;
    private String customerID;
    private String userName;
    private String passWord;
    private String accountNumber;
    private String balance;
    public Account(Integer kid,String customerID,String userName,String passWord,String accountNumber,String balance){
        this.kid = kid;
        this.userName = userName;
        this.passWord = passWord;
        this.customerID = customerID;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account() { }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setKid(Integer kid)
    {
        this.kid = kid;
    }

    public Integer getKid() {
        return kid;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {

    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "kid=" + kid +
                ", customerID='" + customerID + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
