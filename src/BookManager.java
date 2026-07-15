import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BookManager {
    private final List<Book> books = new ArrayList<>();
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void addBook(String name, String author, LocalDate date, boolean status){
        books.add(new Book(name, author, date, status));
    }

    public void deleteBook(String name, String author){
        if (books.isEmpty()) {
            throw new IllegalArgumentException("Список книг пуст!");
        }
            Book bookToRemove = null;
            Book foundBook = searchBook(name, author);
            for(Book currentBook : books){
                if(foundBook == currentBook){
                    bookToRemove = currentBook;
                    break;
                }
            }
        if(bookToRemove != null){
            books.remove(bookToRemove);
        } else{
            throw new IllegalArgumentException("Такой книги нет!");
        }
    }

    public Book searchBook(String name, String author){
            for(Book currentBook : books){
                String lowerName = currentBook.getName().toLowerCase();
                String lowerAuthor = currentBook.getAuthor().toLowerCase();
                if(Objects.equals(name.toLowerCase(), lowerName) && Objects.equals(author.toLowerCase(), lowerAuthor)){
                    //System.out.println(currentBook);
                    return currentBook;
                }
            }
            return null;
    }

    public void changeStatus(String name, String author, boolean status){
        Book foundBook = searchBook(name, author);
            for(Book currentBook : books){
                if(foundBook == currentBook){
                    currentBook.setReadStatus(status);
                    break;
                }
            }
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books);
    }

    public List<Book> showReadBooks(){
        if (books.isEmpty()) {
            throw new IllegalArgumentException("Список книг пуст!");
        }
        List<Book> readBooks = new ArrayList<>();

            for(Book currentBook : books){
                if(currentBook.isReadStatus()){
                    readBooks.add(currentBook);
                }
            }
        return readBooks;
    }

    public List<Book> showUnreadBooks(){
        if (books.isEmpty()) {
            throw new IllegalArgumentException("Список книг пуст!");
        }
        List<Book> unreadBooks = new ArrayList<>();

        for(Book currentBook : books){
            if(!currentBook.isReadStatus()){
                unreadBooks.add(currentBook);
            }
        }
        return unreadBooks;
    }

}
