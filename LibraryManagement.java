
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // --- Sample Data (Pre-populate) ---
        // Using the new method: title, author, isbn, quantity
        library.addBook("The Lord of the Rings", "J.R.R. Tolkien", "978-0618260274", 3);
        library.addBook("Pride and Prejudice", "Jane Austen", "978-0141439518", 2);
        library.addBook("1984", "George Orwell", "978-0451524935", 5);
        library.addBook("To Kill a Mockingbird", "Harper Lee", "978-0061120084", 1);
        library.addBook("The Lord of the Rings", "J.R.R. Tolkien", "978-0618260274", 2);
        library.addBook("Dear Life", "Alice Munro", "978-0804168915", 1);


        library.addUser(new User("U001", "Bharat Jaiswal"));
        library.addUser(new User("U002", "Shiva Jaiswal"));
        // --- End Sample Data ---

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Display Available Books");
            System.out.println("2. Display All Users (and their borrowed items)");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Add New Book or Add Copies"); 
            System.out.println("6. Add New User");
            System.out.println("7. Remove Book (entire title and all copies)");
            System.out.println("8. Delete User (returns their books automatically)");
            System.out.println("9. Display All Book Titles");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choiceStr = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    library.displayAllUsers();
                    break;
                case 3: // Issue Book
                    System.out.print("Enter ISBN of book to issue: ");
                    String issueIsbn = scanner.nextLine();
                    System.out.print("Enter User ID to issue to: ");
                    String issueUserId = scanner.nextLine();
                    library.issueBook(issueIsbn, issueUserId);
                    break;
                case 4: // Return Book
                    System.out.print("Enter ISBN of book to return: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Enter User ID returning the book: ");
                    String returnUserId = scanner.nextLine();
                    library.returnBook(returnIsbn, returnUserId);
                    break;
                case 5: // Add New Book or Add Copies
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter quantity of copies to add: ");
                    String quantityStr = scanner.nextLine();
                    try {
                        int quantity = Integer.parseInt(quantityStr);
                        if (quantity > 0) {
                            library.addBook(title, author, isbn, quantity);
                        } else {
                            System.out.println("Quantity must be a positive number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Please enter a number.");
                    }
                    break;
                case 6: // Add New User
                    System.out.print("Enter user ID: ");
                    String newUserId = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    library.addUser(new User(newUserId, userName));
                    break;
                case 7: // Remove Book (entire title)
                    System.out.print("Enter ISBN of book (title) to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 8: // Delete User
                    System.out.print("Enter User ID of user to delete: ");
                    String deleteUserId = scanner.nextLine();
                    library.deleteUser(deleteUserId);
                    break;
                case 9:
                    library.displayAllBooks();
                    break;
                case 0:
                    System.out.println("Exiting Library System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}