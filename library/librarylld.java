import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

enum Genre {
    FICTION,
    NON_FICTION,
    SCIENCE,
    HISTORY,
    FANTASY
}

enum UserType {
    LIBRARIAN,
    MEMBER
}

class BookISBN {
    String isbn;
    String title;
    String author;
    String subject;
    Genre genre;
    Date publicationDate;
    int availableCopies;
}

class Book {
    int id; // unique
    BookISBN bookISBN;
    int availableCopies;
    String rackLocation;
    int isReservedBy = -1;
}

class BookIssue {
    Instant issueDate;
    Book book;

    public BookIssue(Instant issueDate, Book book) {
        this.issueDate = issueDate;
        this.book = book;
    }

    public Instant getValidTill() {
        if (issueDate == null)
            return null;

        return issueDate.plus(15, ChronoUnit.DAYS);
    }
}

// NoArgsConstructor
// AllArgsCOnstructor
// @Data
abstract class User {

    protected int cardNumber;
    protected String name;
    protected String email;
    protected String phone;
    protected UserType userType;
}

class Inventory {
    private Set<Book> booksDb = new HashSet<>();

    public void addBook(Book book) {
        booksDb.add(book);
    }

    public void updateBook(Book book) {
    }

    public boolean isAvailable(Book book) {
        return booksDb.contains(book);
    }

    public void reduce(Book book) {
        booksDb.remove(book);
    }

    public void increase(Book book) {
        booksDb.add(book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(booksDb);
    }
}

class BookManager {
    private Inventory inventory;

    public BookManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addBook(Book book) {
        inventory.addBook(book);
    }

    public void updateBook(Book book) {
        inventory.updateBook(book);
    }
}

class Librarian extends User {
    private String employeeId;
    private Date hireDate;

    private BookManager bookManager;

    public Librarian(int cardNumber, String name, String email, String phone, String employeeId) {
        this.employeeId = employeeId;
        this.hireDate = new Date();
    }

    public void addBook(Book book) {
        bookManager.addBook(book);
    }

    public void updateBook(Book book) {
        bookManager.updateBook(book);
    }
}

class Member extends User {
    private Date membershipDate;
    private int borrowedBooksCount;
    private List<Book> issuedBooks = new ArrayList<>();
    private double fineDue = 0.0;

    private Inventory inventory;

    public Member(int cardNumber, String name, String email, String phone) {
        this.membershipDate = new Date();
        this.borrowedBooksCount = 0;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void issueBook(Book book) {
        if (canBorrowMore(book)) {
            issuedBooks.add(book);
            borrowedBooksCount++;
        } else {
            throw new IllegalStateException("Cannot borrow more than 10 books.");
        }
    }

    public boolean reserveBook(Book book) {
        boolean isBookAvailable = false;
        for (Book eachBook : inventory.getAllBooks()) {

            if (book.bookISBN == eachBook.bookISBN && eachBook.isReservedBy == -1) {
                isBookAvailable = true;
                eachBook.isReservedBy = this.cardNumber;
                Book updatedBook = eachBook;
                inventory.reduce(book);
                inventory.addBook(updatedBook);
                break;
            }
        }
        return isBookAvailable;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void removeIssuedBook(Book book) {
        issuedBooks.remove(book);
    }

    public boolean canBorrowMore(Book book) {
        if (issuedBooks.size() >= 10) {
            return false;
        }
        if (book.isReservedBy == -1) {
            return true;
        }
        if (this.cardNumber != book.isReservedBy) {
            return false;
        }
        return false;
    }

    public double getFineDue() {
        return fineDue;
    }

    public void addFine(double fine) {
        fineDue += fine;
    }

    public void payFine(int amount) {
        fineDue -= amount;
    }

    public void clearFine() {
        fineDue = 0;
    }

}

class UserManager {
    private List<User> users;
    private int nextCardNumber;

    public UserManager() {
        this.users = new ArrayList<>();
        this.nextCardNumber = 1001;
    }

    public Librarian createLibrarian(String name, String email, String phone, String employeeId) {
        Librarian librarian = new Librarian(nextCardNumber++, name, email, phone, employeeId);
        users.add(librarian);

        return librarian;
    }

    public Member createMember(String name, String email, String phone) {
        Member member = new Member(nextCardNumber++, name, email, phone);
        users.add(member);
        return member;
    }

}

class librarylld {

}
