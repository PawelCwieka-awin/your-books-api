import java.util.List;

public class Catalog {

    private final List<String> isbnList;

    public Catalog(List<String> isbnList) {
        this.isbnList = isbnList;
    }

    public boolean contains(Book book) {
        return isbnList.contains(book.getIsbn());
    }

}
