import java.util.List;

public class Catalog {

    List<String> isbnList = List.of("123", "456", "789");
    public boolean contains(Book book) {
        return isbnList.contains(book.getIsbn());
    }
}
