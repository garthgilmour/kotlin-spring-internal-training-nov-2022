package demos.lifecycle.common;

public interface PaymentEngine {
    public boolean authorize(String cardNo, double amount);
}
