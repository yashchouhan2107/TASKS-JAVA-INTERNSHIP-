import java.util.*;

// Book class
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

// User class
class User {
    private int userId;
    private String name;
    private List<Book> issuedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
        book.issue();
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
        book.returnBook();
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    @Override
    public String toString() {
        return "User[" + userId + "] " + name;
    }
}

// Library class
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully.");
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void showAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users in the library.");
            return;
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    public Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public User findUserById(int id) {
        for (User u : users) {
            if (u.getUserId() == id) return u;
        }
        return null;
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book == null || user == null) {
            System.out.println("Invalid book or user ID.");
        } else if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            user.issueBook(book);
            System.out.println("Book issued successfully to " + user.getName());
        }
    }

    public void returnBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book == null || user == null) {
            System.out.println("Invalid book or user ID.");
        } else if (!user.getIssuedBooks().contains(book)) {
            System.out.println("This user has not issued this book.");
        } else {
            user.returnBook(book);
            System.out.println("Book returned successfully.");
        }
    }

    public void showIssuedBooks(int userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("Invalid user ID.");
        } else {
            List<Book> issued = user.getIssuedBooks();
            if (issued.isEmpty()) {
                System.out.println("No books issued by " + user.getName());
            } else {
                System.out.println("Books issued by " + user.getName() + ":");
                for (Book b : issued) {
                    System.out.println(b);
                }
            }
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Sample data
        library.addBook(new Book(1, "Java Basics", "James Gosling"));
        library.addBook(new Book(2, "Python 101", "Guido van Rossum"));
        library.addUser(new User(101, "Yash"));
        library.addUser(new User(102, "Shiva"));

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Show All Books");
            System.out.println("2. Show All Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show User's Issued Books");
            System.out.println("6. Add Book");
            System.out.println("7. Add User");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.showAllBooks();
                    break;
                case 2:
                    library.showAllUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    library.issueBook(bookId, userId);
                    break;
                case 4:
                    System.out.print("Enter Book ID: ");
                    bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    userId = sc.nextInt();
                    library.returnBook(bookId, userId);
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextInt();
                    library.showIssuedBooks(userId);
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    int newBookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(newBookId, title, author));
                    break;
                case 7:
                    System.out.print("Enter User ID: ");
                    int newUserId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    library.addUser(new User(newUserId, name));
                    break;
                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}