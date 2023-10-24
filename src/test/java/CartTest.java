import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void cartIsCreatedEmpty() {
        Cart cart = new Cart(new Catalog(List.of("123", "456")));
        assertTrue(cart.isEmpty());
    }

    @Test
    public void whenAddedBookThenTheBookWithGivenIsbnIsInTheCart() {
        String isbn = "123";
        Catalog catalog = new Catalog(List.of(isbn));
        Cart cart = new Cart(catalog);
        Book book = new Book(isbn);
        cart.add(book);

        assertTrue(cart.containsByIsbn(isbn));
    }

    @Test
    public void getExceptionWhenAddedBookNotPublishedByLibrus() {
        Cart cart = new Cart(new Catalog(Collections.emptyList()));
        String isbn = "987";
        Book book = new Book(isbn);
        assertThrowsExactly(RuntimeException.class, () -> cart.add(book));
    }

}
