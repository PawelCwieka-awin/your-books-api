package tuslibros.com.supermarket;

public class Receipt {

    String transactionId;

    public Receipt(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
