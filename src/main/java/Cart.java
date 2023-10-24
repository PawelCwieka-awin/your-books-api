import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Book> books = new ArrayList<>();

    private final Catalog catalog = new Catalog();

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public void add(Book book) {
        if (catalog.contains(book) == false) {
            throw new RuntimeException("Book is not published yet");
        }
        books.add(book);
    }

    public boolean containsByIsbn(String isbn) {
        return books.stream().anyMatch(book -> book.getIsbn().equals(isbn));
    }
}
