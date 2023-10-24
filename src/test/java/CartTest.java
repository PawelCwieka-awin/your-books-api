import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

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


        assertTrue(cart.containsByIsbn(isbn));
    }

    @Test
    public void getExceptionWhenAddedBookNotPublishedByLibrus() {
        Cart cart = new Cart();
        String isbn = "987";
        Book book = new Book(isbn);
        assertThrowsExactly(RuntimeException.class, () -> cart.add(book));
    }

}
