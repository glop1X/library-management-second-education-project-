void main() {
    BookManager bookManager = new BookManager();
    Scanner sc = new Scanner(System.in);

    int choice;

    String menu = """
            Выберите функцию из списка:
            1. Добавить книгу
            2. Показать все книги
            3. Найти книгу
            4. Изменить статус прочтения книги
            5. Удалить книгу
            6. Вывести только прочитанные книги
            7. Вывести только непрочитанные книги
            8. Статистика
            0. Выход
            """;

    System.out.println(menu);

    while(true){
        choice = sc.nextInt();
        sc.nextLine();
        if(choice == 0){
            System.out.println("Вы завершили программу!");
            break;
        }
        if(choice == 1){
            System.out.println("Введите название книги:");
            String name = sc.nextLine();
            System.out.println("Введите автора книги:");
            String author = sc.nextLine();
            System.out.println("Введите дату издания книги:");
            String dateInput = sc.nextLine();
            try{
                LocalDate date = LocalDate.parse(dateInput, BookManager.formatter);
                bookManager.addBook(name, author, date, false);
                System.out.println("Книга успешно добавлена!");
            } catch (Exception e) {
                System.out.println("Ошибка формата даты! Введите дату в виде чч.ММ.гггг");
            }
        }

        if(choice == 2){
            System.out.println("Список прочитанных книг:");
            bookManager.getAllBooks().forEach(book -> System.out.println(" - " + book));

        }

        if(choice == 3){
            if(bookManager.getAllBooks().isEmpty()){
                System.out.println("В библиотеке нет книг!");
            } else{
                System.out.println("Для поиска книги в библиотеке введите название книги:");
                String name = sc.nextLine();
                System.out.println("Теперь имя и фамилию автора книги:");
                String author = sc.nextLine();
                if(name.isEmpty() || author.isEmpty()){
                    System.out.println("Название книги или имя автора не может быть пустым");
                }
                Book foundBook = bookManager.searchBook(name, author);
                if(foundBook != null) {
                    System.out.println(foundBook);
                } else{
                    System.out.println("Такой книги нет!");
                }
            }

        }

        if(choice == 4){
            if(bookManager.getAllBooks().isEmpty()){
                System.out.println("В библиотеке нет книг!");
            } else {
                System.out.println("Чтобы отметить книгу прочтенной введите название книги:");
                String name = sc.nextLine();
                System.out.println("Теперь имя и фамилию автора книги:");
                String author = sc.nextLine();
                if (name.isEmpty() || author.isEmpty()) {
                    System.out.println("Название книги или имя автора не может быть пустым");
                }
                if (bookManager.searchBook(name, author) != null){
                    bookManager.changeStatus(name,author, true);
                    System.out.println("Книга помечена, как прочтенная!");
                } else {
                    System.out.println("Такой книги нет!");
                }
            }
        }

        if(choice == 5){
            if(bookManager.getAllBooks().isEmpty()){
                System.out.println("Библиотека пуста!");
            } else{
                try{
                    System.out.println("Чтобы удалить книгу введите название книги:");
                    String name = sc.nextLine();
                    System.out.println("Теперь имя и фамилию автора книги:");
                    String author = sc.nextLine();
                    if (name.isEmpty() || author.isEmpty()) {
                        System.out.println("Название книги или имя автора не может быть пустым");
                    }
                    bookManager.deleteBook(name, author);
                    System.out.println("Книга успешно удалена!");
                } catch (IllegalArgumentException e){
                    String message = e.getMessage();
                    System.err.println("Ошибка: " + message);
                }
            }
        }

        if(choice == 6){
            try{
                if(bookManager.showReadBooks().isEmpty()){
                    System.out.println("Прочитанных книг нет!");
                } else{
                    List<Book> readBooks = bookManager.showReadBooks();

                    System.out.println("Список прочитанных книг:");
                    readBooks.forEach(book -> System.out.println(" - " + book));
                }
            } catch(Exception e){
                String message = e.getMessage();
                System.err.println("Ошибка: " + message);
                }
        }

        if(choice == 7){
            try{
                if(bookManager.showReadBooks().isEmpty()){
                    System.out.println("Непрочитанных книг нет!");
                } else{
                    List<Book> unreadBooks = bookManager.showUnreadBooks();

                    System.out.println("Список непрочитанных книг:");
                    unreadBooks.forEach(book -> System.out.println(" - " + book));
                }
            } catch(Exception e){
                String message = e.getMessage();
                System.err.println("Ошибка: " + message);
            }
        }

        if(choice == 8){
           try{
               BookStats stats = bookManager.getLibrarySummary();
               System.out.println("Статистика по библиотеке:");
               BookStats bookStats = stats;
               System.out.printf("Всего книг: %-20s | Прочтенных: %-15s | Непрочтенных: %4d | Процент прочтенных: %.2f%% %n",
                       bookStats.total(), bookStats.read(), bookStats.unread(), bookStats.percentage());
           } catch(Exception e){
               String message = e.getMessage();
               System.err.println("Ошибка: " + message);
           }
        }
        System.out.println("Выберите следующую функцию:");
    }
}
