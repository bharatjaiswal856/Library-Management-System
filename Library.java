
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private final Map<String, Book> books; // ISBN -> Book
    private final Map<String, User> users; // UserID -> User

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }

    // Book Management
    public void addBook(String title, String author, String isbn, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be positive.");
            return;
        }
        Book existingBook = books.get(isbn);
        if (existingBook != null) {
            existingBook.addCopies(quantity);
            System.out.println(quantity + " copies of '" + existingBook.getTitle() +
                    "' added. Total: " + existingBook.getTotalCopies() +
                    ", Available: " + existingBook.getAvailableCopies());
        } else {
            Book newBook = new Book(title, author, isbn, quantity);
            books.put(isbn, newBook);
            System.out.println("New book '" + title + "' added with " + quantity + " copies.");
        }
    }

    public Book findBookByIsbn(String isbn) {
        return books.get(isbn);
    }

    public void displayAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getAvailableCopies() > 0) {
                System.out.println(book); // toString in Book now shows counts
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently available.");
        }
    }

    public void displayAllBooks() {
        System.out.println("\n--- All Books in Library ---");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public boolean removeBook(String isbn) {
        Book bookToRemove = findBookByIsbn(isbn);

        if (bookToRemove == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found. Cannot remove.");
            return false;
        }

        // Only allow removal if all copies are available (none are borrowed)
        if (bookToRemove.getAvailableCopies() < bookToRemove.getTotalCopies()) {
            System.out.println("Error: Book '" + bookToRemove.getTitle() +
                    "' has (" + (bookToRemove.getTotalCopies() - bookToRemove.getAvailableCopies()) +
                    ") copies currently borrowed. Cannot remove entire book entry.");
            return false;
        }

        books.remove(isbn);
        System.out.println("Book '" + bookToRemove.getTitle() + "' (ISBN: " + isbn +
                ") and all its copies removed from the library.");
        return true;
    }


    // User Management
    public void addUser(User user) {
        if (user != null && !users.containsKey(user.getUserId())) {
            users.put(user.getUserId(), user);
            System.out.println("User added: " + user.getName());
        } else if (user != null) {
            System.out.println("Error: User with ID " + user.getUserId() + " already exists.");
        }
    }

    public User findUserById(String userId) {
        return users.get(userId);
    }

    public void displayAllUsers() {
        System.out.println("\n--- Registered Users ---");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User user : users.values()) {
            System.out.println(user);
            if (!user.getBorrowedBooks().isEmpty()) {
                System.out.println("  Borrowed Book Titles (one entry per borrowed copy):");
                for (Book book : user.getBorrowedBooks()) {
                    System.out.println("    - " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
                }
            }
        }
    }

    public void deleteUser(String userId) {
        User userToDelete = findUserById(userId);

        if (userToDelete == null) {
            System.out.println("Error: User with ID " + userId + " not found. Cannot delete.");
            return;
        }

        // Automatically return all books borrowed by this user before deleting them
        if (!userToDelete.getBorrowedBooks().isEmpty()) {
            System.out.println("User '" + userToDelete.getName() + "' has borrowed books. Automatically returning them before deletion:");
            List<Book> booksToReturn = new ArrayList<>(userToDelete.getBorrowedBooks());
            for (Book borrowedBook : booksToReturn) {
                borrowedBook.returnCopy();
                userToDelete.returnBook(borrowedBook);
                System.out.println("  - Auto-returned: " + borrowedBook.getTitle());
            }
        }

        users.remove(userId);
        System.out.println("User '" + userToDelete.getName() + "' (ID: " + userId + ") deleted from the system.");
    }

    // Core Operations
    public void issueBook(String isbn, String userId) {
        Book book = findBookByIsbn(isbn);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found.");
            return;
        }
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        if (book.borrowCopy()) {
            user.borrowBook(book); // Add this book (title) to the user's list of borrowed items
            System.out.println("One copy of '" + book.getTitle() + "' issued to user '" + user.getName() + "'.");
            System.out.println("Remaining available copies of '" + book.getTitle() + "': " + book.getAvailableCopies());
        } else {
            System.out.println("Error: No available copies of '" + book.getTitle() + "' to issue.");
        }
    }

    public void returnBook(String isbn, String userId) {
        Book book = findBookByIsbn(isbn);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found (cannot return).");
            return;
        }
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found (cannot process return).");
            return;
        }

        // Check if the user actually borrowed this specific book title
        if (!user.getBorrowedBooks().contains(book)) {
            System.out.println("Error: User '" + user.getName() + "' did not borrow any copy of '" + book.getTitle() +
                    "' or has already returned all copies of it.");
            return;
        }

        if (book.returnCopy()) {
            user.returnBook(book);
            System.out.println("One copy of '" + book.getTitle() + "' returned by user '" + user.getName() + "'.");
            System.out.println("Now available copies of '" + book.getTitle() + "': " + book.getAvailableCopies());
        } else {
            System.out.println("Error: Could not process return for '" + book.getTitle() + "'. No copies were marked as borrowed by system or max copies already available.");
        }
    }
}