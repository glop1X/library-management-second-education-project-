import java.time.LocalDate;

public class Book {
    private final int id;
    private static int idCounter = 1;
    private final String name;
    private final String author;
    private final LocalDate date;
    private boolean readStatus;

    public Book(String bookName, String bookAuthor, LocalDate bookDate, boolean bookStatus){
        if(bookName == null || bookName.isEmpty()){
            throw new IllegalArgumentException("Название книги не может быть пустым!");
        }
        if(bookAuthor == null || bookAuthor.isEmpty()){
            throw new IllegalArgumentException("Имя автора не может быть пустым!");
        }
        if(bookDate == null){
            throw new IllegalArgumentException("Дата книги не может быть пустой!");
        }
        this.id = idCounter++;
        this.name = bookName;
        this.author = bookAuthor;
        this.date = bookDate;
        this.readStatus = bookStatus;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public LocalDate getDate(){
        return date;
    }

    public boolean isReadStatus(){
        return readStatus;
    }

    public void setReadStatus(boolean status){
        this.readStatus = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", readStatus=" + readStatus +
                '}';
    }
}
