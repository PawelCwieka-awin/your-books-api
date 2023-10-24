import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextX {

    @Test
    public void cartIsCreatedEmpty() {
        Cart cart = new Cart();
        assertTrue(cart.isEmpty());
    }

    @Test
    public void whenAddedBookThenTheBookWithGivenIsbnIsInTheCart() {
        Cart cart = new Cart();
        String isbn = "123";
        Book book = new Book(isbn);
        cart.add(book);

        assertTrue(cart.containsByIsbn(book));
    }
}
