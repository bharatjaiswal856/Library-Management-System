import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<String, Book> books; // ISBN -> Book
    private Map<String, User> users; // UserID -> User

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }


    // Method to add a new book or add copies to an existing one
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
                System.out.println(book);
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
     public boolean deleteUser(String userId) {
        User userToDelete = findUserById(userId);

        if (userToDelete == null) {
            System.out.println("Error: User with ID " + userId + " not found. Cannot delete.");
            return false;
        }

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
        return true;
    }

    public boolean issueBook(String isbn, String userId) {
        Book book = findBookByIsbn(isbn);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found.");
            return false;
        }
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return false;
        }

        if (book.borrowCopy()) {
            user.borrowBook(book);
            System.out.println("One copy of '" + book.getTitle() + "' issued to user '" + user.getName() + "'.");
            System.out.println("Remaining available copies of '" + book.getTitle() + "': " + book.getAvailableCopies());
            return true;
        } else {
            System.out.println("Error: No available copies of '" + book.getTitle() + "' to issue.");
            return false;
        }
    }
    public boolean returnBook(String isbn, String userId) {
        Book book = findBookByIsbn(isbn);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found (cannot return).");
            return false;
        }
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found (cannot process return).");
            return false;
        }
        if (!user.getBorrowedBooks().contains(book)) {
             System.out.println("Error: User '" + user.getName() + "' did not borrow any copy of '" + book.getTitle() +
                                "' or has already returned all copies of it.");
             return false;
        }

        if (book.returnCopy()) {
            user.returnBook(book);
            System.out.println("One copy of '" + book.getTitle() + "' returned by user '" + user.getName() + "'.");
            System.out.println("Now available copies of '" + book.getTitle() + "': " + book.getAvailableCopies());
            return true;
        } else {
            System.out.println("Error: Could not process return for '" + book.getTitle() + "'. System indicates no copies were borrowed by this user for return or an issue with copy count.");
            return false;
        }
    }

    // --- NEW SEARCH METHODS ---

    /**
     * Finds a user by their ID and displays their details and borrowed books.
     * @param userId The ID of the user to find.
     */
    public void findUserAndDisplayDetails(String userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User with ID '" + userId + "' not found.");
            return;
        }

        System.out.println("\n--- User Details ---");
        System.out.println("User ID: " + user.getUserId());
        System.out.println("Name: " + user.getName());

        List<Book> borrowedBooks = user.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println(user.getName() + " has no books currently borrowed.");
        } else {
            System.out.println("Items currently borrowed by " + user.getName() + ": " + borrowedBooks.size());
            Map<String, Integer> titleCounts = new HashMap<>();
            for (Book book : borrowedBooks) {
                titleCounts.put(book.getIsbn(), titleCounts.getOrDefault(book.getIsbn(), 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : titleCounts.entrySet()) {
                Book b = findBookByIsbn(entry.getKey()); // Get book details
                System.out.println("  - " + b.getTitle() + " (ISBN: " + b.getIsbn() + ") - " + entry.getValue() + " copy");
            }
        }
    }

    /**
     * Finds a book by its ISBN and displays its details, availability, and who has borrowed it.
     * @param isbn The ISBN of the book to find.
     */
    public void findBookAndDisplayDetails(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book with ISBN '" + isbn + "' not found.");
            return;
        }

        System.out.println("\n--- Book Details ---");
        System.out.println(book); // Uses Book's toString() which shows Title, Author, ISBN, Available/Total copies

        Map<User, Integer> borrowers = new HashMap<>(); // User -> Number of copies borrowed by this user
        for (User currentUser : users.values()) {
            int copiesBorrowedByThisUser = 0;
            for (Book borrowedBook : currentUser.getBorrowedBooks()) {
                if (borrowedBook.getIsbn().equals(isbn)) {
                    copiesBorrowedByThisUser++;
                }
            }
            if (copiesBorrowedByThisUser > 0) {
                borrowers.put(currentUser, copiesBorrowedByThisUser);
            }
        }

        if (borrowers.isEmpty()) {
            if (book.getAvailableCopies() == book.getTotalCopies()) {
                 System.out.println("This book is available and not currently issued to any user.");
            } else {
                 // This case should ideally not happen if counts are correct.
                 // It means totalCopies > availableCopies but no user has it.
                 System.out.println("This book shows copies are out, but no specific user records found (potential data inconsistency).");
            }
        } else {
            System.out.println("Currently issued to:");
            for (Map.Entry<User, Integer> entry : borrowers.entrySet()) {
                User borrower = entry.getKey();
                int count = entry.getValue();
                System.out.println("  - " + borrower.getName() + " (ID: " + borrower.getUserId() + ") - " + count + " cop(y/ies)");
            }
        }
    }
}