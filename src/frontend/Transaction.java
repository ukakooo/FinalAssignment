package frontend;

public abstract class Transaction {
    // Basic DML
    public abstract void insertTransaction();
    public abstract void updateTransaction();
    public abstract void deleteTransaction();

    // Total Calculations
    public abstract void calculateTotalPricePerCustomer();
    public abstract void calculateTotalPricePerGame();
}
