public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addFunds(double amount) {
        this.balance += amount;
    }

    public void deductFunds(double amount) {
        this.balance -= amount;
    }
}
