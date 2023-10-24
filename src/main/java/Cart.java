import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Book> books = new ArrayList<>();

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public void add(Book book) {
        books.add(book);
    }

    public boolean containsByIsbn(Book book) {
        return books.stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()));
    }
}
