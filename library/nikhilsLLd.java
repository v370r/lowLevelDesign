// Library

// - User should be able to search for books
// - User should be able to issue books
// - Fines on late return
// - Multiple payment modes
// - Admin suport

// ==================================

enum BookGenre {
    Science, Fiction, Novel
}

enum PaymentMode {
    UPI, Card, Cash
}

// ==================================

abstract class Person {
    String id;
    String name;
}

class User extends Person {
    List<Book> issuedBooks;
    double findDue;
}

class Admin extends Person {

    public Admin(String id, String name) {
        super(id, name);
    }

    void addBook(Book book, Inventory inventory) {
        inventory.addBook(book);
    }

    void updateBook(Book book, Inventory inventory) {
        inventory.updateBook(book);
    }
}

class Book {
    String bookId;
    String bookName;
    BookGenre genre;
    String publisher;
    String author;
}

// ==================================

class Inventory {
    Map<Book, Integer> booksDb = new HashMap<>();

    void addBook(Book book) {

    }

    void updateBook(Book book) {

    }

    boolean isAvailable(Book book) {

    }

    void reduce(Book book) {

    }

    void increase(Book book) {

    }

    List<Book> getAllBooks() {

    }
}

// ==================================

interface SearchStrategy {
    List<Book> search(List<Book> books, String query);
}

class SearchByName implmenets SearchStrategy {
    List<Book> search(List<Book> books, String query) {

    }
}

class SearchByAuthor implmenets SearchStrategy {
    List<Book> search(List<Book> books, String query) {

    }
}

class SearchByGenre implmenets SearchStrategy {
    BookGenre genre;

    List<Book> search(List<Book> books, String query) {

    }
}

class SearchService {
    List<Book> search(List<Book> books, String query, SearchStrategy searchStrategy) {
        return searchStrategy.search(books, query);
    }
}

// ==================================

interface Payment {
    void pay(double amount);
}

class UPIPayment implement Payment {

}

class CashPayment implement Payment {

}

class CardPayment implement Payment {

}

class PaymentFactory {
    Payment getPaymentMethod(PaymentMode mode, double amount) {
        // not writter because already implemented in most of designs
    }
}

// ==================================

class BorrowService {
    Inventory inventory;

    boolean borrowBook(User user, Book book) {
        if (!inventory.isAvailable(book)) {
            return false;
        }

        inventory.reduce(book);
        user.issuedBooks.add(book);
        return true;
    }
}

interface FineCalculator {
    double calculateFine(Date issueDate, Date returnDate);
}

class SimpleFineCalculator implements FineCalculator {
    int DAILY_FINE = 10;
    int MAX_DAYS = 10;

    @Override
    double calculateFine(Date issueDate, Date returnDate) {

    }
}

class ReturnService {
    Inventory inventory;
    FineCalculator fineCalculator;

    public ReturnService(Inventory inventory, FineCalculator fineCalculator) {

    }

    double returnBook(User user, Book book, Date issueDate, Date returnDate) {
        inventory.increase(book);
        user.issuedBooks.remove(book);
        double fine = fineCalculator.calculateFine(issueDate, returnDate);
        user.findeDue += fine;
        return fine;
    }
}

class LibraryManagementFacade {
    Inventory inventory;
    BorrowService borrowService;
    ReturnService returnService;
    SearchService serachService;
    PaymentFactory paymentFactory;

    List<Book> searchBooks(String query, SearchStrategy searchStrategy) {
        serachService.search(inventory.getAllBooks(), query, searchStrategy);
    }

    boolean issueBook(User user, Book book) {
        borrowService.borrowBook(user, book);
    }

    double returnBook(User user, Book book, Date issueDate, Date returnDate) {
        returnService.returnBook(user, book, issueDate, returnDate);
    }

    void payFine(User user, PaymentMode mode) {
        Payment pay = PaymentFactory.getPaymentMethod(mode);
        pay.pay(user.fineDue);
        user.fineDue = 0;
    }
}

// ============Client Code============

LibraryManagementFacade lms = new LibraryManagementFacade();

User u1 = new User();lms.issueBook(u1,book1);

i wrote this lld,