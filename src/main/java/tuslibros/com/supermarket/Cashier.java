package tuslibros.com.supermarket;

public class Cashier {

    public Receipt checkout(Cart cart, CreditCard creditCard) {
        if (cart.isEmpty()) {
            throw new RuntimeException("You cannot checkout an empty cart");
        }
        return new Receipt(cart.getTotal());
    }
}
